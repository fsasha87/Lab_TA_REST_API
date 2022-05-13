import io.restassured.response.Response;
import org.testng.annotations.Test;
import entities.Genre;
import webservices.GenreService;
import webservices.Verifier;

public class GenreTests {
    GenreService genreService = new GenreService();
    Verifier verifier = new Verifier();
    int max = genreService.getMaxGenreID();
    Genre testGenre = genreService.generateGenre(max);


//    @Test (enabled = true)
//    public void showAllGenres(){
//        Response response = genreService.getAllGenres();
//        response.then().log().all();
//    }

    @Test
    public void verifyCreatedGenre() {
        Response responseCreate = genreService.createGenre(testGenre);
        verifier
                .verifyStatusCodeForPost(responseCreate)
                .verifyGenresNamesAreTheSame(testGenre, responseCreate)
                .verifyGenresAreTheSame(genreService.getGenreFromResponse(responseCreate), testGenre);
        Response responseDelete = genreService.deleteGenreByGenreID(testGenre.genreId);
        verifier.verifyStatusCodeForDelete(responseDelete);
    }

    @Test
    public void verifyGotGenre() {
        Response responseCreate = genreService.createGenre(testGenre);
        verifier.verifyStatusCodeForPost(responseCreate);
        Response responseGet = genreService.getGenreByGenreID(testGenre.genreId);
        verifier
                .verifyStatusCodeForGetOrPut(responseGet)
                .verifyGenresNamesAreTheSame(testGenre, responseCreate)
                .verifyGenresAreTheSame(genreService.getGenreFromResponse(responseGet), testGenre);
        Response responseDelete = genreService.deleteGenreByGenreID(testGenre.genreId);
        verifier.verifyStatusCodeForDelete(responseDelete);
    }

    @Test
    public void verifyUpdatedGenre() {
        Response responseCreate = genreService.createGenre(testGenre);
        verifier.verifyStatusCodeForPost(responseCreate);
        Response responseUpdate = genreService.updateGenreByGenreID(new Genre(max, "updatedName", "updatedDescription"));
        verifier
                .verifyStatusCodeForGetOrPut(responseUpdate)
                .verifyGenresAreNotTheSame(genreService.getGenreFromResponse(responseUpdate), testGenre);
        Response responseDelete = genreService.deleteGenreByGenreID(testGenre.genreId);
        verifier.verifyStatusCodeForDelete(responseDelete);
    }

    @Test
    public void verifyDeletedGenre() {
        Response responseCreate = genreService.createGenre(testGenre);
        verifier.verifyStatusCodeForPost(responseCreate);
        Response responseDelete = genreService.deleteGenreByGenreID(testGenre.genreId);
        verifier
                .verifyStatusCodeForDelete(responseDelete)
                .verifyDeleteResponseIsEmpty(responseDelete);
    }

    @Test(description = "negative test")
    public void verifyGenreWithInvalidID() {
        Response response2 = genreService.getGenreByGenreID(max + 1);
        verifier
                .verifyStatusCode404(response2)
                .verifyError(response2);
    }
}