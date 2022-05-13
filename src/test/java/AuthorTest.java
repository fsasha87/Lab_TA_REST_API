import io.restassured.response.Response;
import org.testng.annotations.Test;
import entities.Author;
import webservices.AuthorService;
import webservices.Verifier;

public class AuthorTest {
    AuthorService authorService = new AuthorService();
    Verifier verifier = new Verifier();
    Author testAuthor = authorService.generateAuthor();
    int freeID = authorService.generateAuthorId();

    @Test(enabled = false)
    public void showAllAuthors(){
        Response response = authorService.getAllAuthors();
        response.then().log().all();
    }

    @Test
    public void CRUDAndVerifyStatusCode() {
        Response responseCreate = authorService.createAuthor(testAuthor);
        verifier.verifyStatusCodeForPost(responseCreate);
        Response responseGet = authorService.getAuthorByAuthorID(testAuthor.getAuthorId());
        verifier.verifyStatusCodeForGetOrPut(responseGet);
        Response responseUpdate = authorService.updateAuthorByAuthorID(testAuthor.getAuthorId());
        verifier.verifyStatusCodeForGetOrPut(responseUpdate);
        Response responseDelete = authorService.deleteAuthorByAuthorID(testAuthor.getAuthorId());
        verifier.verifyStatusCodeForDelete(responseDelete);
    }

    @Test(description = "negative test")
    public void verifyGenreWithInvalidID() {
        Response response2 = authorService.getAuthorByAuthorID(freeID);
        verifier
                .verifyStatusCode404(response2)
                .verifyError(response2);
    }

}
