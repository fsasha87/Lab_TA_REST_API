package webservices;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.restassured.response.Response;
import manager.HttpClient;
import org.apache.log4j.Logger;
import entities.Genre;
import entities.Option;

import java.util.ArrayList;
import java.util.List;

public class GenreService {
    private static final Logger LOG = Logger.getLogger(String.valueOf(GenreService.class));
    Gson gson = new Gson();

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
        LOG.info("Genre is deleted");
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
        return HttpClient.getWithQuery(endpoint, new Option());
    }

    public List<Genre> getGenresListFromResponse (Response response){
        String responseBody = response.getBody().asString();
        return gson.fromJson(responseBody, new TypeToken<ArrayList<Genre>>(){}.getType());//десериализация
    }

    public List<Integer> getGenreIDListFromResponse (List<Genre> genresList){
        List <Integer> genreIDList = new ArrayList<>();
        for (Genre item : genresList) {
            genreIDList.add(item.genreId);
        }
        return genreIDList;
    }

    public Genre getGenreFromResponse (Response response){
        String responseBody = response.getBody().asString();
        return gson.fromJson(responseBody, Genre.class);
    }

    public String getGenreNameFromResponse (Response response){
        String responseBody = response.getBody().asString();
        String genreName = gson.fromJson(responseBody, Genre.class).genreName;
//        System.out.println(genreName);
        return genreName;
    }

    public List<Integer> getAllGenreIDList() {
        Response response = getAllGenres();
        List<Genre> genreList = getGenresListFromResponse(response);
        List<Integer> genreIDList = getGenreIDListFromResponse(genreList);
//        genreID.forEach(System.out::println);
        return genreIDList;
    }

    public int getMaxGenreID() {
        Response response = getAllGenres();
        List<Genre> genreList = getGenresListFromResponse(response);
        List<Integer> genreIDList = getGenreIDListFromResponse(genreList);
        int max = 0;
        if (genreIDList.size() > 0) {
            max = getAllGenreIDList().stream().max(Integer::compare).get();
            max++;
            LOG.info(String.format("MaxIDGende is '%d'", max));
        }
        return max;

    }



}
