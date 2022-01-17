import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo.Genre;
import services.GenreResponseReader;
import services.GenreService;
import services.Verifier;

import java.util.List;

public class GenreTests {
    GenreService genreService = new GenreService();
    GenreResponseReader genreResponseReader = new GenreResponseReader();
    Verifier verifier = new Verifier();
    int max = getMaxGenreID();
    Genre testGenre = genreService.generateGenre(max);
    private static final Logger LOG = Logger.getLogger(String.valueOf(GenreTests.class));

    public int getMaxGenreID(){
        Response response = genreService.getAllGenres();
        List<Genre> genreList = genreResponseReader.getGenresListFromResponse(response);
        List<Integer> genreIDList = genreResponseReader.getGenreIDListFromResponse(genreList);
        int max = genreIDList.stream().max(Integer::compare).get();
        max++;
        LOG.info(String.format("MaxIDGende is '%d'", max));
        return max;
    }

//    @Test (enabled = true)
//    public void showAllGenres(){
//        Response response = genreService.getAllGenres();
//        response.then().log().all();
//    }

//    @BeforeClass (enabled = true)
//    public void deleteGenreTest(){
//        Response response = genreService.deleteGenreByGenreID(max);
//    }

    @Test
    public void verifyCreatedGenre(){
        Response responseCreate = genreService.createGenre(testGenre);
        verifier.verifyStatusCodeForPost(responseCreate);
        verifier.verifyGenresAreTheSame(genreResponseReader.getGenreFromResponse(responseCreate), testGenre);
        Response responseDelete = genreService.deleteGenreByGenreID(testGenre.genreId);
        verifier.verifyStatusCodeForDelete(responseDelete);
    }

    @Test
    public void verifyGotGenre(){
        Response responseCreate = genreService.createGenre(testGenre);
        verifier.verifyStatusCodeForPost(responseCreate);
        Response responseGet = genreService.getGenreByGenreID(testGenre.genreId);
        verifier.verifyStatusCodeForGetOrPut(responseGet);
        verifier.verifyGenresAreTheSame(genreResponseReader.getGenreFromResponse(responseGet), testGenre);
        Response responseDelete = genreService.deleteGenreByGenreID(testGenre.genreId);
        verifier.verifyStatusCodeForDelete(responseDelete);
    }

    @Test
    public void verifyUpdatedGenre(){
        Response responseCreate = genreService.createGenre(testGenre);
        verifier.verifyStatusCodeForPost(responseCreate);
        Response responseUpdate = genreService.updateGenreByGenreID(new Genre(max, "updatedName", "updatedDescription"));
        verifier.verifyStatusCodeForGetOrPut(responseUpdate);
        verifier.verifyGenresAreNotTheSame(genreResponseReader.getGenreFromResponse(responseUpdate), testGenre);
        Response responseDelete = genreService.deleteGenreByGenreID(testGenre.genreId);
        verifier.verifyStatusCodeForDelete(responseDelete);
    }


    @Test
    public void verifyDeletedGenre(){
        Response responseCreate = genreService.createGenre(testGenre);
        verifier.verifyStatusCodeForPost(responseCreate);
        Response responseDelete = genreService.deleteGenreByGenreID(testGenre.genreId);
        verifier.verifyStatusCodeForDelete(responseDelete);
        verifier.verifyDeleteResponseIsEmpty(responseDelete);
    }

    @Test(description = "negative test")
    public void verifyGenreWithInvalidID(){
        Response response2 = genreService.getGenreByGenreID(max+1);
        verifier.verifyStatusCode404(response2);
        verifier.verifyError(response2);
    }

    @Test
    public void verifysmth (){
        Response responseCreate = genreService.createGenre(testGenre);
        verifier.verifyStatusCodeForPost(responseCreate);
        Response responseGet = genreService.getGenreByGenreID(testGenre.genreId);
        verifier.verifyStatusCodeForGetOrPut(responseGet);
        String nameFromResponse = genreResponseReader.getGenreNameFromResponse(responseGet);
        Assert.assertEquals(testGenre.genreName, nameFromResponse, "names are not equals");
    }

    @Test
    public void getAllGenreID(){
        Response response = genreService.getAllGenres();
        List<Genre> genreList = genreResponseReader.getGenresListFromResponse(response);
        List<Integer> genreID = genreResponseReader.getGenreIDListFromResponse(genreList);
        genreID.forEach(System.out::println);

    }

}
