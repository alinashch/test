package org.example.controller.book.filter.age;

import io.restassured.http.ContentType;
import org.example.controller.book.BookBaseTest;
import org.example.utils.enums.BookStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.servlet.http.HttpServletResponse.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetFilteredByMinAge extends BookBaseTest {


    @Test
    void getFilterByMinAgeCorrectParam(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        minimumAge =1;
        bookId=20L;
        saveBook(titleString, author, bookId, description, minimumAge);

        var requestSpecification = given()
                .param("minimumAge", minimumAge)
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
    void getFilterByMinAgeCorrectParam2(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        minimumAge =18;
        bookId=20L;
        saveBook(titleString, author, bookId, description, minimumAge);

        var requestSpecification = given()
                .param("minimumAge", minimumAge)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());
        System.out.println(responseJson.get().toString());
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
    void getFilterByMinAgeCorrectParam3() {
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i = 2; i <= 10; i++) {
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        minimumAge =0;
        bookId=20L;
        saveBook(titleString, author, bookId, description, minimumAge);

        var requestSpecification = given()
                .param("minimumAge", minimumAge)
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
    void getFilterByMinAgeInCorrectParam(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        minimumAge =1;
        bookId=20L;
        saveBook(titleString, author, bookId, description, minimumAge);

        int findAge=-1;
        var requestSpecification = given()
                .param("minimumAge", findAge)
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
    void getFilterByMinAgeInCorrectParam2(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        minimumAge =1;
        bookId=20L;
        saveBook(titleString, author, bookId, description, minimumAge);

        int findAge=20;
        var requestSpecification = given()
                .param("minimumAge", findAge)
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
    void getFilterByMinAgeCorrectParamAndPage(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=30; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }


        var requestSpecification = given()
                .param("minimumAge", minimumAge)
                .param("page", 2 )
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(titleString, responseJson.getString("content[0].title"));
        assertEquals(author, responseJson.getString("content[0].author"));
        assertEquals(22, responseJson.getLong("content[0].bookId"));
        assertEquals(description, responseJson.getString("content[0].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[0].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[0].minimumAge"));
        assertEquals(2, responseJson.getInt("currentPage"));
        assertEquals(10, responseJson.getInt("pageSize"));
        assertEquals(3, responseJson.getInt("totalPages"));
        assertEquals(29, responseJson.getInt("totalElements"));

        var requestSpecification2 = given()
                .param("minimumAge", minimumAge)
                .param("page", 2 )
                .contentType(ContentType.JSON);

        //when
        var response2 = requestSpecification2.when()
                .get("/book");
        var responseJson2 = response2.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(titleString, responseJson2.getString("content[0].title"));
        assertEquals(author, responseJson2.getString("content[0].author"));
        assertEquals(22, responseJson2.getLong("content[0].bookId"));
        assertEquals(description, responseJson2.getString("content[0].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson2.getString("content[0].bookStatus"));
        assertEquals(minimumAge, responseJson2.getInt("content[0].minimumAge"));
        assertEquals(2, responseJson2.getInt("currentPage"));
        assertEquals(10, responseJson2.getInt("pageSize"));
        assertEquals(3, responseJson2.getInt("totalPages"));
        assertEquals(29, responseJson2.getInt("totalElements"));
    }

    @Test
    void getFilterByMinAgeCorrectParamAndLimit(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=30; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        bookId=40L;
        saveBook(titleString, author, bookId, description, minimumAge);

        var requestSpecification = given()
                .param("minimumAge", minimumAge)
                .param("limit", 5 )
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();

        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(titleString, responseJson.getString("content[0].title"));
        assertEquals(author, responseJson.getString("content[0].author"));
        assertEquals(2, responseJson.getLong("content[0].bookId"));
        assertEquals(description, responseJson.getString("content[0].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[0].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[0].minimumAge"));
        assertEquals(0, responseJson.getInt("currentPage"));
        assertEquals(5, responseJson.getInt("pageSize"));
        assertEquals(6, responseJson.getInt("totalPages"));
        assertEquals(30, responseJson.getInt("totalElements"));
    }


    @Test
    void getFilterByAuthorCorrectParamPAgeAndLimit(){
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
                .param("minimumAge", minimumAge)
                .param("limit", 3)
                .param("page", 2)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        System.out.println(responseJson.get().toString());
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(titleString, responseJson.getString("content[0].title"));
        assertEquals(author, responseJson.getString("content[0].author"));
        assertEquals(8, responseJson.getLong("content[0].bookId"));
        assertEquals(description, responseJson.getString("content[0].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[0].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[0].minimumAge"));
        assertEquals(2, responseJson.getInt("currentPage"));
        assertEquals(3, responseJson.getInt("pageSize"));
        assertEquals(3, responseJson.getInt("totalPages"));
        assertEquals(9, responseJson.getInt("totalElements"));

        assertEquals(titleString, responseJson.getString("content[2].title"));
        assertEquals(author, responseJson.getString("content[2].author"));
        assertEquals(10, responseJson.getLong("content[2].bookId"));
        assertEquals(description, responseJson.getString("content[2].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[2].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[2].minimumAge"));
        assertEquals(2, responseJson.getInt("currentPage"));
        assertEquals(3, responseJson.getInt("pageSize"));
        assertEquals(3, responseJson.getInt("totalPages"));
        assertEquals(9, responseJson.getInt("totalElements"));
    }
}
