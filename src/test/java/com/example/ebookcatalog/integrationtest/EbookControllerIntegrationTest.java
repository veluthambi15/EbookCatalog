package com.example.ebookcatalog.integrationtest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EbookControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@BeforeAll
	public static void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@Test
	@DisplayName("Display All Ebooks Details")
	public void testGetAllEbooks() {
			given()
				.port(port)
			.when()
				.get("/ebooks")
			.then()
				.statusCode(200)
				.body("size()", greaterThanOrEqualTo(0));
	}

	@Test
	@DisplayName("Add new Ebook Details")
	public void testAddEbook() {
				given()
					.port(port)
					.contentType("application/json")
					.body("{\"author\":\"Veluthambi\",\"title\":\"Finnish Language\",\"format\":\"Online & Pdf\"}")
				.when()
					.post("/ebooks")
				.then()
					.statusCode(201);
	}

	@Test
	@DisplayName("Fetch Ebook Details by Id")
	public void testGetEbookById() {
		String ebookId = 
				given()
					.port(port)
					.contentType("application/json")
					.body("{\"author\":\"Veluthambi\",\"title\":\"Finnish Language\",\"format\":\"Pdf\"}")
				.when()
					.post("/ebooks")
				.then()
					.statusCode(201)
					.extract()
					.jsonPath()
					.getString("id");

				given()
					.port(port)
				.when()
					.get("/ebooks/{ebook_id}", ebookId)
				.then()
					.statusCode(200)
					.body("author", equalTo("Veluthambi"))
					.body("title", equalTo("Finnish Language"))
					.body("format", equalTo("Pdf"));
	}
	
    @Test
    @DisplayName("Update Ebook Details Using Id")
    public void testUpdateEbookById() {
          	String ebookId = given()
                    .port(port)
                    .contentType("application/json")
                    .body("{\"author\":\"Veluthambi\",\"title\":\"Finnish Language\",\"format\":\"Pdf\"}")
                 .when()
                    .post("/ebooks")
                 .then()
                    .statusCode(201)
                    .extract()
                    .jsonPath()
                    .getString("id");
                 
                 given()
                    .port(port)
                    .contentType("application/json")
                    .body("{\"author\":\"Praveen\",\"title\":\"English Language\",\"format\":\"Online\"}")
                 .when()
                    .put("/ebooks/{ebook_id}", ebookId)
                 .then()
                    .statusCode(200)
                    .body("author", equalTo("Praveen"))
                    .body("title", equalTo("English Language"))
                    .body("format", equalTo("Online"));
    }

    @Test
    @DisplayName("Delete an Ebook Detail")
    public void testDeleteEbookById() {
             String ebookId = given()
                    .port(port)
                    .contentType("application/json")
                    .body("{\"author\":\"Veluthambi\",\"title\":\"Finnish Language\",\"format\":\"Pdf\"}")
                .when()
                    .post("/ebooks")
                .then()
                    .statusCode(201)
                    .extract()
                    .jsonPath()
                    .getString("id");
                
                given()
                    .port(port)
                .when()
                    .delete("/ebooks/{ebook_id}", ebookId)
                .then()
                    .statusCode(204); 
    }


}

