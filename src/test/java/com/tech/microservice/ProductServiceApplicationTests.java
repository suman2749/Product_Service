package com.tech.microservice;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.equalTo;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@DynamicPropertySource
	static void setMongoDbProperties(DynamicPropertyRegistry registry) {
		// Ensure container is started before setting URI
		mongoDBContainer.start();
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		RestAssured.defaultParser = Parser.JSON; // Force JSON parsing
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
            {
                "id": "1",
                "name": "Iphone 14",
                "description": "IPhone 14 Pro Max with 256GB storage",
                "price": 100000.0
            }
            """;

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/products")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", equalTo("Iphone 14"))
				.body("description", equalTo("IPhone 14 Pro Max with 256GB storage"))
				.body("price", equalTo(100000.0f));
	}
}
