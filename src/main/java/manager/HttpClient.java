package manager;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import entities.Option;
import utils.PropertiesReader;

import static io.restassured.RestAssured.given;

public class HttpClient {


    protected static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(PropertiesReader.getHost())
            .setBasePath(PropertiesReader.getPath())
            .addHeader("Content-Type", "application/json")
            .build();

    public static Response get(String endpoint) {
        return given().spec(requestSpec).get(endpoint);
    }

    public static Response post(String endpoint, String body) {
        return given().spec(requestSpec).body(body).post(endpoint);
    }

    public static Response put(String endpoint, String body) {
        return given().spec(requestSpec).body(body).put(endpoint);
    }

    public static Response delete(String endpoint) {
        return given().spec(requestSpec).delete(endpoint);
    }

    public static Response getWithQuery(String endpoint, Option option) {
        return given().queryParam(String.valueOf(option)).spec(requestSpec).log().uri().get(endpoint);
    }


}
