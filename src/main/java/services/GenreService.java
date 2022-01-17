package services;

import io.restassured.response.Response;
import manager.HttpClient;
import org.apache.log4j.Logger;
import pojo.Genre;

public class GenreService {
    private static final Logger LOG = Logger.getLogger(String.valueOf(GenreService.class));

    public Genre generateGenre(int genreId){
        Genre genre = new Genre();
        genre.genreId = genreId;
        genre.genreName = "TestSmthName";
        genre.genreDescription = "TestSmthDecr";
        return genre;
    }

    public Response getGenreByGenreID(int genreId) {
        String endpoint = "genre/" + genreId;
        LOG.info("Genre is got");
        return HttpClient.get(endpoint);
    }

    public Response createGenre(Genre genre) {
        String endpoint = "genre";
        LOG.info("Genre is created");
        return HttpClient.post(endpoint, genre.toString());
    }

    public Response deleteGenreByGenreID(int genreId) {
        String endpoint = "genre/" + genreId;
//        LOG.info("Genre is deleted");
        return HttpClient.delete(endpoint);
    }

    public Response updateGenreByGenreID(Genre genre) {
        String endpoint = "genre";
        LOG.info("Genre is updated");
        return HttpClient.put(endpoint, genre.toString());
    }

    public Response getAllGenres() {
        String endpoint = "genres";
        LOG.info("Genres are got");
        return HttpClient.get(endpoint);
    }



}
