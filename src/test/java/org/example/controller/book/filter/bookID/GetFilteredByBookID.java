package org.example.controller.book.filter.bookID;

import io.restassured.http.ContentType;
import org.example.controller.book.BookBaseTest;
import org.example.utils.enums.BookStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetFilteredByBookID extends BookBaseTest {


    @Test
    void getFilterByBookIDCorrectParam(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }

        var requestSpecification = given()
                .param("bookId", bookId)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(titleString, responseJson.getString("content[0].title"));
        assertEquals(author, responseJson.getString("content[0].author"));
        assertEquals(bookId, responseJson.getLong("content[0].bookId"));
        assertEquals(description, responseJson.getString("content[0].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[0].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[0].minimumAge"));
        assertEquals(0, responseJson.getInt("currentPage"));
        assertEquals(10, responseJson.getInt("pageSize"));
        assertEquals(1, responseJson.getInt("totalPages"));
        assertEquals(1, responseJson.getInt("totalElements"));
    }

    @Test
    void getFilterByBookIDInCorrectParam(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        var requestSpecification = given()
                .param("bookId", "qqqq")
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFilterByBookIDCorrectParam2(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=20; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }

        var requestSpecification = given()
                .param("bookId", 20L)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(titleString, responseJson.getString("content[0].title"));
        assertEquals(author, responseJson.getString("content[0].author"));
        assertEquals(20, responseJson.getLong("content[0].bookId"));
        assertEquals(description, responseJson.getString("content[0].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[0].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[0].minimumAge"));
        assertEquals(0, responseJson.getInt("currentPage"));
        assertEquals(10, responseJson.getInt("pageSize"));
        assertEquals(1, responseJson.getInt("totalPages"));
        assertEquals(1, responseJson.getInt("totalElements"));
    }

    @Test
    void getFilterByBookIDInCorrectParam2(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }

        var requestSpecification = given()
                .param("bookId", 40)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());

        assertEquals(0, responseJson.getInt("currentPage"));
        assertEquals(10, responseJson.getInt("pageSize"));
        assertEquals(0, responseJson.getInt("totalPages"));
        assertEquals(0, responseJson.getInt("totalElements"));
    }

    @Test
    void getFilterByBookIDCorrectParam3() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="5";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(titleString, responseJson.getString("content[0].title"));
        assertEquals(author, responseJson.getString("content[0].author"));
        assertEquals(5, responseJson.getLong("content[0].bookId"));
        assertEquals(description, responseJson.getString("content[0].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[0].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[0].minimumAge"));
        assertEquals(0, responseJson.getInt("currentPage"));
        assertEquals(10, responseJson.getInt("pageSize"));
        assertEquals(1, responseJson.getInt("totalPages"));
        assertEquals(1, responseJson.getInt("totalElements"));
    }

    @Test
    void getFilterByBookIDInCorrectParam3() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="&";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFilterByBookIDInCorrectParam4() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="!";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void getFilterByBookIDInCorrectParam5() {
        String titleString = "title";
        String author = "auth";
        Long bookId =0L;
        String description = "desc";
        Integer minimumAge = 3;
        saveBook(titleString, author, bookId, description, minimumAge);


        var requestSpecification = given()
                .param("bookId", bookId)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(titleString, responseJson.getString("content[0].title"));
        assertEquals(author, responseJson.getString("content[0].author"));
        assertEquals(bookId, responseJson.getLong("content[0].bookId"));
        assertEquals(description, responseJson.getString("content[0].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[0].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[0].minimumAge"));
        assertEquals(0, responseJson.getInt("currentPage"));
        assertEquals(10, responseJson.getInt("pageSize"));
        assertEquals(1, responseJson.getInt("totalPages"));
        assertEquals(1, responseJson.getInt("totalElements"));
    }
    @Test
    void getFilterByBookIDInCorrectParam6() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="@";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void getFilterByBookIDInCorrectParam7() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="#";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFilterByBookIDInCorrectParam8() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="$";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFilterByBookIDInCorrectParam9() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="^";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFilterByBookIDInCorrectParam10() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="*";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void getFilterByBookIDInCorrectParam11() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="(";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void getFilterByBookIDInCorrectParam12() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find =")";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFilterByBookIDInCorrectParam13() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="<";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFilterByBookIDInCorrectParam14() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find =">";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFilterByBookIDInCorrectParam15() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="/";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFilterByBookIDInCorrectParam16() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find =",";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFilterByBookIDInCorrectParam17() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find =".";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFilterByBookIDInCorrectParam18() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String find ="?";

        var requestSpecification = given()
                .param("bookId", find)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void getFilterByBookIDCorrectParamAndPAge(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }

        var requestSpecification = given()
                .param("bookId", bookId)
                .param("page", 2)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());

        assertEquals(2, responseJson.getInt("currentPage"));
        assertEquals(10, responseJson.getInt("pageSize"));
        assertEquals(1, responseJson.getInt("totalPages"));
        assertEquals(1, responseJson.getInt("totalElements"));
    }
}
