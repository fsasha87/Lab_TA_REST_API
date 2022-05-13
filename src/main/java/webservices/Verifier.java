package webservices;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import entities.Error;
import entities.Genre;

public class Verifier {
    private static final Logger LOG = Logger.getLogger(String.valueOf(Verifier.class));
    Gson gson = new Gson();

    public Verifier verifyStatusCodeForGetOrPut(Response response) {
        int statusCode = response.getStatusCode();
        LOG.info(String.format("Returned statusCode: '%d'", statusCode));
        Assert.assertEquals(statusCode, 200, "StatusCode incorrect");
        return this;
    }

    public Verifier verifyStatusCodeForPost(Response response) {
        int statusCode = response.getStatusCode();
        LOG.info(String.format("Returned statusCode: '%d'", statusCode));
        Assert.assertEquals(statusCode, 201, "StatusCode incorrect");
        return this;
    }

    public Verifier verifyStatusCodeForDelete(Response response) {
        int statusCode = response.getStatusCode();
        LOG.info(String.format("Returned statusCode: '%d'", statusCode));
        Assert.assertEquals(statusCode, 204, "StatusCode incorrect");
        return this;
    }

    public Verifier verifyStatusCode404(Response response) {
        int statusCode = response.getStatusCode();
        LOG.info(String.format("Returned statusCode: '%d'", statusCode));
        Assert.assertEquals(statusCode, 404, "StatusCode is not 404");
        return this;
    }

    public Verifier verifyError(Response response) {
        String errorBody = response.getBody().asString();
        String error = gson.fromJson(errorBody, Error.class).error;
        LOG.info(String.format("pojo.Error is caught: '%s'", error));
        Assert.assertEquals(error, "Not Found", "Other error is shown");
        return this;
    }

    public Verifier verifyGenresAreTheSame(Genre sendGenre, Genre receivedGenre) {
        LOG.info("Send and received genres are the same");
        Assert.assertEquals(receivedGenre, sendGenre, "Genres are not the same");
        return this;
    }

    public Verifier verifyGenresAreNotTheSame(Genre createdGenre, Genre receivedGenre) {
        LOG.info("Created and updated genres are different");
        Assert.assertNotEquals(receivedGenre, createdGenre, "pojo.Error. Genres are the same");
        return this;
    }

    public Verifier verifyDeleteResponseIsEmpty(Response response) {
        LOG.info("Deleted responsebody has no text");
        Assert.assertEquals(response.getBody().asString(), "", "responsebody has text");
        return this;
    }

    public Verifier verifyGenresNamesAreTheSame(Genre genre, Response response) {
        Assert.assertEquals(genre.genreName, new GenreService().getGenreNameFromResponse(response), "Genre's names are not equals");
        return this;
    }


}
