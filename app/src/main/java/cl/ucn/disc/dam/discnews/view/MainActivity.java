package cl.ucn.disc.dam.discnews.view;

import android.app.ListActivity;
import android.os.Bundle;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import cl.ucn.disc.dam.discnews.R;
import cl.ucn.disc.dam.discnews.controller.NewsController;
import cl.ucn.disc.dam.discnews.model.Article;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instance NewsController
        NewsController newsController = new NewsController();

        // Query for all people contacts using the Contacts.People convenience class.
        // Put a managed wrapper around the retrieved cursor so we don't have to worry about
        // requerying or closing it as the activity changes state.
        try {
            List<Article> articles = newsController.getArticles();
        }
        catch (UnknownHostException e) {
            System.out.println("No hay internet");
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
}
