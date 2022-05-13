package webservices;

import io.restassured.response.Response;
import manager.HttpClient;
import org.apache.log4j.Logger;
import entities.Author;
import entities.Option;

public class AuthorService {
    private static final Logger LOG = Logger.getLogger(String.valueOf(AuthorService.class));

    public int generateAuthorId () {
        int id = 0;
        Response response = null;
        do {
            id = (int) (Math.random() * 10000);
            response = new AuthorService().getAuthorByAuthorID(id);
        } while (response.statusCode() == 200);
        LOG.info(String.format("AuthorID '%d' is successful!", id));
        return id;
    }

    public Author generateAuthor() {
        Author author = Author.builder()
                .authorId(generateAuthorId())
                .authorName(Author.AuthorName.builder()
                        .first("Sasha")
                        .second("Testman")
                        .build())
                .nationality("Ukrainer")
                .birth(Author.Birth.builder()
                        .date("1950-01-01")
                        .country("Ukraine")
                        .city("Kiev")
                        .build())
                .authorDescription("The best author is ever known")
                .build();
        return author;
    }

    public Response getAllAuthors() {
        String endpoint = "authors";
        return HttpClient.getWithQuery(endpoint, new Option());
    }

    public Response getAuthorByAuthorID(int authorId) {
        String endpoint = "author/" + authorId;
        LOG.info("Author is got");
        return HttpClient.get(endpoint);
    }

    public Response createAuthor(Author author) {
        String endpoint = "author";
        LOG.info("Author is created");
        return HttpClient.post(endpoint, author.toString());
    }

    public Response deleteAuthorByAuthorID(int authorId) {
        String endpoint = "author/" + authorId;
        LOG.info("Author is deleted");
        return HttpClient.delete(endpoint);
    }

    public Response updateAuthorByAuthorID(int authorId) {
        Author author2 = Author.builder()
                .authorId(authorId)
                .authorName(Author.AuthorName.builder()
                        .first("updatedName")
                        .second("updatedSecondName")
                        .build())
                .nationality("updatedNationality")
                .birth(Author.Birth.builder()
                        .date("1960-01-12")
                        .country("UAS")
                        .city("NY-city")
                        .build())
                .authorDescription("updatedAuthorDescription")
                .build();
        String endpoint = "author";
        LOG.info("Author is updated");
        return HttpClient.put(endpoint, author2.toString());
    }




}
