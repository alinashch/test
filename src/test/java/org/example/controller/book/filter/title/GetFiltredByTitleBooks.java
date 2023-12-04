package org.example.controller.book.filter.title;

import io.restassured.http.ContentType;
import org.example.controller.book.BookBaseTest;
import org.example.utils.enums.BookStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.servlet.http.HttpServletResponse.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetFiltredByTitleBooks extends BookBaseTest {

    @Test
    void getFilterByTitleCorrectParam(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
       for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        bookId = 20L;
        String findTitle="find title";
        saveBook(findTitle, author, bookId, description, minimumAge);

        var requestSpecification = given()
                .param("title", findTitle)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(findTitle, responseJson.getString("content[0].title"));
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
    void getFilterByTitleUnCorrectParam(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        bookId = 20L;
        String findTitle="find title";
        saveBook(findTitle, author, bookId, description, minimumAge);
        String newTitle="new title";

        var requestSpecification = given()
                .param("title", newTitle)
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
    void getFilterByTitleCorrectParamWithNums(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        bookId = 20L;
        String findTitle="find title 123";
        saveBook(findTitle, author, bookId, description, minimumAge);

        var requestSpecification = given()
                .param("title", findTitle)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(findTitle, responseJson.getString("content[0].title"));
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
    void getFilterByTitleCorrectParamWithBig(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        bookId = 20L;
        String findTitle="FIND TITLE ";
        saveBook(findTitle, author, bookId, description, minimumAge);

        var requestSpecification = given()
                .param("title", findTitle)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(findTitle, responseJson.getString("content[0].title"));
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
    void getFilterByTitleCorrectParamWithIncorrectPageNum(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        bookId = 20L;
        String findTitle="find title";
        saveBook(findTitle, author, bookId, description, minimumAge);

        var requestSpecification = given()
                .param("title", findTitle)
                .param("page",3)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());

        assertEquals(3, responseJson.getInt("currentPage"));
        assertEquals(10, responseJson.getInt("pageSize"));
        assertEquals(1, responseJson.getInt("totalPages"));
        assertEquals(1, responseJson.getInt("totalElements"));
    }

    @Test
    void getFilterByTitleCorrectParamSome(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String findTitle="find title ";
        for (int i=20; i<=55; i++){
            bookId = Long.valueOf(i);
            saveBook(findTitle, author, bookId, description, minimumAge);
        }
        var requestSpecification = given()
                .param("title", findTitle)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(findTitle, responseJson.getString("content[0].title"));
        assertEquals(author, responseJson.getString("content[0].author"));
        assertEquals(20, responseJson.getLong("content[0].bookId"));
        assertEquals(description, responseJson.getString("content[0].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[0].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[0].minimumAge"));
        assertEquals(0, responseJson.getInt("currentPage"));
        assertEquals(10, responseJson.getInt("pageSize"));
        assertEquals(4, responseJson.getInt("totalPages"));
        assertEquals(36, responseJson.getInt("totalElements"));

        assertEquals(findTitle, responseJson.getString("content[9].title"));
        assertEquals(author, responseJson.getString("content[9].author"));
        assertEquals(29, responseJson.getLong("content[9].bookId"));
        assertEquals(description, responseJson.getString("content[9].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[9].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[9].minimumAge"));
        assertEquals(0, responseJson.getInt("currentPage"));
        assertEquals(10, responseJson.getInt("pageSize"));
        assertEquals(4, responseJson.getInt("totalPages"));
        assertEquals(36, responseJson.getInt("totalElements"));

    }
    @Test
    void getFilterByTitleCorrectParamSomeAndPage(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String findTitle="find title ";
        for (int i=20; i<=55; i++){
            bookId = Long.valueOf(i);
            saveBook(findTitle, author, bookId, description, minimumAge);
        }
        var requestSpecification = given()
                .param("title", findTitle)
                .contentType(ContentType.JSON);

        var requestSpecification2 = given()
                .param("title", findTitle)
                .param("page",3)
                .contentType(ContentType.JSON);

        //when
        var response2 = requestSpecification2.when()
                .get("/book");
        var responseJson2 = response2.jsonPath();

        assertEquals(findTitle, responseJson2.getString("content[0].title"));
        assertEquals(author, responseJson2.getString("content[0].author"));
        assertEquals(50, responseJson2.getLong("content[0].bookId"));
        assertEquals(description, responseJson2.getString("content[0].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson2.getString("content[0].bookStatus"));
        assertEquals(minimumAge, responseJson2.getInt("content[0].minimumAge"));
        assertEquals(3, responseJson2.getInt("currentPage"));
        assertEquals(10, responseJson2.getInt("pageSize"));
        assertEquals(4, responseJson2.getInt("totalPages"));
        assertEquals(36, responseJson2.getInt("totalElements"));

        assertEquals(findTitle, responseJson2.getString("content[5].title"));
        assertEquals(author, responseJson2.getString("content[5].author"));
        assertEquals(55, responseJson2.getLong("content[5].bookId"));
        assertEquals(description, responseJson2.getString("content[5].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson2.getString("content[5].bookStatus"));
        assertEquals(minimumAge, responseJson2.getInt("content[5].minimumAge"));
        assertEquals(3, responseJson2.getInt("currentPage"));
        assertEquals(10, responseJson2.getInt("pageSize"));
        assertEquals(4, responseJson2.getInt("totalPages"));
        assertEquals(36, responseJson2.getInt("totalElements"));
    }

    @Test
    void getFilterByTitleCorrectParamSomeAndIncorrectLimit(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String findTitle="find title ";
        for (int i=20; i<=55; i++){
            bookId = Long.valueOf(i);
            saveBook(findTitle, author, bookId, description, minimumAge);
        }
        var requestSpecification = given()
                .param("title", findTitle)
                .param("limit", -1)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        assertEquals(SC_INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    void getFilterByTitleCorrectParamSomeAndIncorrectPage(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String findTitle="find title ";
        for (int i=20; i<=55; i++){
            bookId = Long.valueOf(i);
            saveBook(findTitle, author, bookId, description, minimumAge);
        }
        var requestSpecification = given()
                .param("title", findTitle)
                .param("page", -1)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        assertEquals(SC_INTERNAL_SERVER_ERROR, response.getStatusCode());

    }
    @Test
    void getFilterByTitleCorrectParamSomeAndIncorrectPage2(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String findTitle="find title ";
        for (int i=20; i<=55; i++){
            bookId = Long.valueOf(i);
            saveBook(findTitle, author, bookId, description, minimumAge);
        }
        var requestSpecification = given()
                .param("title", findTitle)
                .param("page", "page")
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());

    }

    @Test
    void getFilterByTitleCorrectParamSomeAndIncorrectLimit2(){
        String titleString = "title";
        String author = "auth";
        Long bookId = 1L;
        String description = "desc";
        Integer minimumAge = 3;
        for (int i=2; i<=10; i++){
            bookId = Long.valueOf(i);
            saveBook(titleString, author, bookId, description, minimumAge);
        }
        String findTitle="find title ";
        for (int i=20; i<=55; i++){
            bookId = Long.valueOf(i);
            saveBook(findTitle, author, bookId, description, minimumAge);
        }
        var requestSpecification = given()
                .param("title", findTitle)
                .param("limit", "limiit")
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        assertEquals(SC_BAD_REQUEST, response.getStatusCode());

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
        String find="find title ";
        for (int i=20; i<=55; i++){
            bookId = Long.valueOf(i);
            saveBook(find, author, bookId, description, minimumAge);
        }
        var requestSpecification = given()
                .param("title", find)
                .param("limit", 3)
                .param("page", 2)
                .contentType(ContentType.JSON);

        //when
        var response = requestSpecification.when()
                .get("/book");
        var responseJson = response.jsonPath();
        System.out.println(responseJson.get().toString());
        assertEquals(SC_OK, response.getStatusCode());
        assertEquals(find, responseJson.getString("content[0].title"));
        assertEquals(author, responseJson.getString("content[0].author"));
        assertEquals(26, responseJson.getLong("content[0].bookId"));
        assertEquals(description, responseJson.getString("content[0].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[0].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[0].minimumAge"));
        assertEquals(2, responseJson.getInt("currentPage"));
        assertEquals(3, responseJson.getInt("pageSize"));
        assertEquals(12, responseJson.getInt("totalPages"));
        assertEquals(36, responseJson.getInt("totalElements"));

        assertEquals(find, responseJson.getString("content[2].title"));
        assertEquals(author, responseJson.getString("content[2].author"));
        assertEquals(28, responseJson.getLong("content[2].bookId"));
        assertEquals(description, responseJson.getString("content[2].description"));
        assertEquals(BookStatus.FREE.toString(), responseJson.getString("content[2].bookStatus"));
        assertEquals(minimumAge, responseJson.getInt("content[2].minimumAge"));
        assertEquals(2, responseJson.getInt("currentPage"));
        assertEquals(3, responseJson.getInt("pageSize"));
        assertEquals(12, responseJson.getInt("totalPages"));
        assertEquals(36, responseJson.getInt("totalElements"));
    }
}
