package org.example.controller.book;

import io.restassured.http.ContentType;
import org.example.entity.Book;
import org.example.model.BookModel;
import org.example.model.create.BookCreateModel;
import org.example.utils.enums.BookStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveNewBookBookTests extends BookBaseTest {

    @Test
    void saveNewBookWithCorrectOneParams() {
        String title = "title";
        String author = "auth";
        Long bookId = 11L;
        String description = "desc";
        Integer minimumAge = 3;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();

        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }

    @Test
    void saveNewBookWithUnCorrectOneParams() {
        saveNewBookWithCorrectOneParams();
        String title = "title";
        String author = "auth";
        Long bookId = 11L;
        String description = "desc";
        Integer minimumAge = 3;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");

        //error bookId repeat
        assertEquals(SC_INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void saveNewBookWithCorrectOneParamsWithNums() {
        String title = "title1";
        String author = "auth1";
        Long bookId = 11L;
        String description = "desc1";
        Integer minimumAge = 3;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();

        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }

    @Test
    void saveNewBookWithCorrectOneParamsWithBig() {
        String title = "TITLE";
        String author = "AUTH";
        Long bookId = 11L;
        String description = "DESC";
        Integer minimumAge = 3;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();

        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }

    @Test
    void saveNewBookWithCorrectSomeParams() {
        String title = "title title title";
        String author = "auth auth auth";
        Long bookId = 11L;
        String description = "desc desc desc";
        Integer minimumAge = 12;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }



    @Test
    void saveNewBookWithUnCorrectAgeMinus() {
        String title = "title";
        String author = "auth";
        Long bookId = 11L;
        String description = "desc";
        Integer minimumAge = -1;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");

        assertEquals(SC_INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void saveNewBookWithUnCorrectAgeMore18() {
        String title = "title";
        String author = "auth";
        Long bookId = 11L;
        String description = "desc";
        Integer minimumAge = 19;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");

        assertEquals(SC_INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void saveNewBookWithCorrectAge0() {
        String title = "title";
        String author = "auth";
        Long bookId = 11L;
        String description = "desc";
        Integer minimumAge = 0;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();


        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }

    @Test
    void saveNewBookWithCorrectAge18() {
        String title = "title";
        String author = "auth";
        Long bookId = 11L;
        String description = "desc";
        Integer minimumAge = 18;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }

    @Test
    void saveNewBookWithCorrectOnRus() {
        String title = "заголовок";
        String author = "автор";
        Long bookId = 11L;
        String description = "описание";
        Integer minimumAge = 16;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }

    @Test
    void saveNewBookWithSpecificSymbols1() {
        String title = "title&";
        String author = "auth&";
        Long bookId = 11L;
        String description = "desc&";
        Integer minimumAge = 12;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }

    @Test
    void saveNewBookWithSpecificSymbols2() {
        String title = "title*";
        String author = "auth*";
        Long bookId = 11L;
        String description = "desc*";
        Integer minimumAge = 12;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }

    @Test
    void saveNewBookWithSpecificSymbols3() {
        String title = "title!";
        String author = "auth!";
        Long bookId = 11L;
        String description = "desc!";
        Integer minimumAge = 12;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }

    @Test
    void saveNewBookWithSpecificSymbols4() {
        String title = "title?";
        String author = "auth?";
        Long bookId = 11L;
        String description = "desc?";
        Integer minimumAge = 12;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }

    @Test
    void saveNewBookWithSpecificSymbols5() {
        String title = "title#";
        String author = "auth#";
        Long bookId = 11L;
        String description = "desc#";
        Integer minimumAge = 12;

        BookCreateModel book = BookCreateModel
                .builder()
                .bookId(bookId)
                .minimumAge(minimumAge)
                .author(author)
                .description(description)
                .title(title)
                .build();
        var requestSpecification = given()
                .body(book)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .post("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(title, responseJson.getString("data.title"));
        assertEquals(author, responseJson.getString("data.author"));
        assertEquals(bookId, responseJson.getLong("data.bookId"));
        assertEquals(description, responseJson.getString("data.description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("data.bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("data.minimumAge"));
    }
}
