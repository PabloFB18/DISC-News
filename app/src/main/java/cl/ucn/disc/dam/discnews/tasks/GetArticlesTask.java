package cl.ucn.disc.dam.discnews.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import cl.ucn.disc.dam.discnews.activities.MainActivity;
import cl.ucn.disc.dam.discnews.controller.NewsController;
import cl.ucn.disc.dam.discnews.model.Article;

/**
 * @author  pablofb on 09-11-17.
 */
public final class GetArticlesTask extends AsyncTask<MainActivity.ArticleAdapter, Void, List<Article>> {

    /**
     *
     */
    private MainActivity.ArticleAdapter articleAdapter;

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param articleAdapters The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected List<Article> doInBackground(MainActivity.ArticleAdapter... articleAdapters) {

        this.articleAdapter = articleAdapters[0];

        final StopWatch stopWatch = StopWatch.createStarted();

        Log.d("","Running in background ..");

        // FIXME: Sera atributo de la clase?
        final NewsController newsController = new NewsController();

        try {
            // Obtener articulo de internet.
            return newsController.getArticles();
        }
        catch (UnknownHostException e) {
            System.out.println("No hay internet");
            return null;
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
            return null;
        }
        finally {
            // Cuanto tiempo demoro?
            Log.d("Time: {}", stopWatch.toString());
        }
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param articles The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(List<Article> articles) {
        this.articleAdapter.setArticles(articles);
        this.articleAdapter.notifyDataSetChanged();
    }
}
