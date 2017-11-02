package cl.ucn.disc.dam.discnews;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import cl.ucn.disc.dam.discnews.model.Noticia;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NoticiaTest {

    /**
     * Des-Serializador GSON
     */
    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting() // TODO: Eliminar en modo produccion
            .create();

    @Test
    public void addition_isCorrect() throws Exception {

        // Noticia bajo prueba
        final Noticia noticia = Noticia
                .builder()
                .titulo("Titulo de la noticia")
                .autor("yo")
                .resumen("este es el resumen")
                .build();

        Assertions.assertThat(noticia).isNotNull();
        Assertions.assertThat(noticia.getTitulo()).isNotNull();

        // Serializar JSON
        final String json = gson.toJson(noticia);

        Assertions.assertThat(json).isNotBlank();
        Assertions.assertThat(json).isNotEmpty();

        // Des-serializar
        final Noticia noti = gson.fromJson(json, Noticia.class);

        Assertions.assertThat(noti).isNotNull();
        Assertions.assertThat(noti).isEqualToComparingFieldByField(noticia);

    }
}