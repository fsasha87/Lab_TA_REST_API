package services;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pojo.Error;
import pojo.Genre;

public class Verifier {
    private static final Logger LOG = Logger.getLogger(String.valueOf(Verifier.class));

    public void verifyStatusCodeForGetOrPut(Response response){
        int statusCode = response.getStatusCode();
        LOG.info(String.format("Returned statusCode: '%d'", statusCode));
        Assert.assertEquals(statusCode, 200, "StatusCode incorrect");
    }
    public void verifyStatusCodeForPost(Response response){
        int statusCode = response.getStatusCode();
        LOG.info(String.format("Returned statusCode: '%d'", statusCode));
        Assert.assertEquals(statusCode, 201, "StatusCode incorrect");
    }
    public void verifyStatusCodeForDelete(Response response){
        int statusCode = response.getStatusCode();
        LOG.info(String.format("Returned statusCode: '%d'", statusCode));
        Assert.assertEquals(statusCode, 204, "StatusCode incorrect");
    }

    public void verifyStatusCode404(Response response){
        int statusCode = response.getStatusCode();
        LOG.info(String.format("Returned statusCode: '%d'", statusCode));
        Assert.assertEquals(statusCode, 404, "StatusCode is not 404");
    }

    public void verifyError(Response response){
        String errorBody =  response.getBody().asString();
        Gson gson = new Gson();
        String error = gson.fromJson(errorBody, Error.class).error;
        LOG.info(String.format("pojo.Error is caught: '%s'", error));
        Assert.assertEquals(error, "Not Found", "Other error is shown");
    }

    public void verifyGenresAreTheSame(Genre sendGenre, Genre receivedGenre) {
        LOG.info("Send and received genres are the same");
        Assert.assertEquals(receivedGenre, sendGenre, "Genres are not the same");
    }

    public void verifyGenresAreNotTheSame(Genre createdGenre, Genre receivedGenre) {
        LOG.info("Created and updated genres are different");
        Assert.assertNotEquals(receivedGenre, createdGenre, "pojo.Error. Genres are the same");
    }

    public void verifyDeleteResponseIsEmpty(Response response) {
        LOG.info("Deleted responsebody has no text");
        Assert.assertEquals(response.getBody().asString(), "","responsebody has text");
    }


}
