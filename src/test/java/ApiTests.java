import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import pojo.CollectionOfIsbn;
import pojo.LoginViewModel;

import java.util.*;

import static io.restassured.RestAssured.given;
@Epic("It's API tests")
@Feature("Api tests")
@Tag("api")
public class ApiTests {
    Map<String, String> body = new HashMap<>();
    String baseBookUrl = "https://www.demoqa.com/BookStore/v1/";
    String urlAuth = "https://demoqa.com/Account/v1/Authorized";
    String urlToken = "https://demoqa.com/Account/v1/GenerateToken";
    final String username = "Username10!";
    final String password = "Username10!";
    final String userId = "30eb4e8d-5a34-4b3d-aaa7-c010045c2661";
    Response response;

    private RequestSpecification requestSpecification = getRequestSpecification();

    public static RequestSpecification getRequestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://demoqa.com/BookStore/v1/");
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);
        return requestSpecBuilder.build();
    }

//    public ApiTests() {
//        this.requestSpecification = new RequestSpecBuilder().setBaseUri("https://www.demoqa.com/BookStore/v1/").log(LogDetail.ALL).addFilter(new AllureRestAssured()).build();
//    }
//    @Step("Token")
    @Test
    void generateToken() {
        body.put("userName",username);
        body.put("password",password);
        response = given()
//                .filter(new AllureRestAssured())
//                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .auth().basic(username,password).body(body)
                .post(urlToken);
        Assertions.assertEquals(200,response.statusCode());
    }
//    @Step("Authorization")
    @Test
    void authorization() {
        LoginViewModel userLoginAndPassword = new LoginViewModel();
        userLoginAndPassword.setUserName(username);
        userLoginAndPassword.setPassword(password);
        response = given()
//                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .auth().basic(username,password)
                .body(userLoginAndPassword)
                .when()
                .post(urlAuth);
        Assertions.assertEquals(response.asString(),"true");
    }
    @Step("Get")
    @Test
    void getAllBooks() {
        response = RestAssured.given()
                .log().all()
                .baseUri(baseBookUrl)
                .basePath("Books")
                .get();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void getBookByIsbn() {
        response = RestAssured.given()
                .log().all()
                .baseUri(baseBookUrl)
                .basePath("Book")
                .param("ISBN", getRandomCollectionofIsbn())
                .get();
        Assertions.assertEquals(200, response.statusCode());
    }
    @Test
    void addListOfBooks() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        JsonArray jsonArray = new JsonArray();
        JsonObject collectionOfIsbns = new JsonObject();
        collectionOfIsbns.addProperty("isbn", getRandomCollectionofIsbn());
        jsonArray.add(collectionOfIsbns);
        jsonObject.add("collectionOfIsbns", jsonArray);
        response = RestAssured
                .given()
                .auth().preemptive().basic(username, password)
                .body(jsonObject.toString())
                .log().all()
                .baseUri(baseBookUrl)
                .basePath("Books")
                .contentType(ContentType.JSON)
                .post();
        Assertions.assertEquals(201, response.statusCode());
    }
    @BeforeEach
    @Test
    void deleteBooks() {
            Response responseDelete = given()
                    .contentType(ContentType.JSON)
                    .auth().preemptive().basic(username, password)
                    .baseUri(baseBookUrl)
                    .basePath("Books")
                    .param("UserId", userId)
                    .delete();
        Assertions.assertEquals(204, responseDelete.statusCode());
        Assertions.assertEquals("keep-alive", responseDelete.header("Connection"));
    }
    @Test
    void deleteBook() {
        String postISBN = getRandomCollectionofIsbn();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        JsonArray jsonArray = new JsonArray();
        JsonObject collectionOfIsbns = new JsonObject();
        collectionOfIsbns.addProperty("isbn", postISBN);
        jsonArray.add(collectionOfIsbns);
        jsonObject.add("collectionOfIsbns", jsonArray);
        Response postResponse = RestAssured
                .given()
                .auth().preemptive().basic(username, password)
                .body(jsonObject.toString())
                .log().all()
                .baseUri(baseBookUrl)
                .basePath("Books")
                .contentType(ContentType.JSON)
                .post();

        body.put("userId", userId);
        body.put("isbn", postISBN);
        Response responseDelete = given()
                .contentType(ContentType.JSON)
                .auth().preemptive().basic(username, password)
                .baseUri(baseBookUrl)
                .basePath("Book")
                .body(body).
                delete();
        Assertions.assertEquals(204, responseDelete.statusCode());
        Assertions.assertEquals("keep-alive", responseDelete.header("Connection"));
    }

    @Test
    void updateBooks() {
        String postISBN = getRandomCollectionofIsbn();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        JsonArray jsonArray = new JsonArray();
        JsonObject collectionOfIsbns = new JsonObject();
        collectionOfIsbns.addProperty("isbn", postISBN);
        jsonArray.add(collectionOfIsbns);
        jsonObject.add("collectionOfIsbns", jsonArray);
        Response postResponse = RestAssured
                .given()
                .auth().preemptive().basic(username, password)
                .body(jsonObject.toString())
                .log().all()
                .baseUri(baseBookUrl)
                .basePath("Books")
                .contentType(ContentType.JSON)
                .post();

        body.put("userId", userId);
        String updateISBN = getRandomCollectionofIsbn();
        if (updateISBN != postISBN) {
            body.put("isbn", updateISBN);
        } else {
            body.put("isbn", getRandomCollectionofIsbn());
        }
        response = given()
                .contentType(ContentType.JSON)
                .auth().preemptive().basic(username, password)
                .baseUri(baseBookUrl)
                .basePath("Books/" + postISBN)
                .param("isbn", postISBN)
                .body(body)
                .put();
        Assertions.assertEquals(200, response.statusCode());
    }

        public String getRandomCollectionofIsbn() {
        return CollectionOfIsbn.getRandomIsbn();
    }
}
