package services;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.restassured.response.Response;
import pojo.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreResponseReader {
    Gson gson = new Gson();
//        //2й способ
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();

    public List<Genre> getGenresListFromResponse (Response response){
        String responseBody = response.getBody().asString();
        return gson.fromJson(responseBody, new TypeToken<ArrayList<Genre>>(){}.getType());
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




}
