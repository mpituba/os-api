package br.com.mpituba.osapi.api.controllers;

import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;


import br.com.mpituba.osapi.domain.model.Estado;
import br.com.mpituba.osapi.domain.repository.EstadoRepository;
import br.com.mpituba.osapi.util.DatabaseCleaner;
import br.com.mpituba.osapi.util.GetJsonOnFile;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.Matchers.hasSize;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class EstadoControllerIT {

	@LocalServerPort
	private int port;
	
	private int numberOfRecordsOnInitialization;
	
	private String jsonPostRequestBodyBahia;
	
	private String jsonNoNomeEstadoProperty;
	
	private String jsonEstadoPutRequestBody;
	
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	@BeforeEach
	public void testConfiguration() {
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/estados";
		
		databaseCleaner.clearTables();
		
		initializeData();
			
	}
	
	
	@Test
	public void givenInitializedDataWhenGetThenCountRecordsOnDatabase() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(numberOfRecordsOnInitialization));
			
	}
	
	
	@Test
	public void givenAnValidIdWhenGetOnEstadosThenGet200StatusResponse() {
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.get("/1")
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	
	@Test
	public void givenValidPostRequestBodyWhenPostOnEstadosThenReceive201StatusReponse() {
		
		jsonPostRequestBodyBahia = GetJsonOnFile.filePathAndName(
				"/json/estado-controller-it/estado-bahia-request-body.json");
		
		given()
			.body(jsonPostRequestBodyBahia)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post("/estados")
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
	}
	
	
	@Test
	public void givenNoNomeEstadoPropertyWhenPostOnEstadosThenHandleBadRequest() {
		
		jsonNoNomeEstadoProperty = GetJsonOnFile.filePathAndName(
				"/json/estado-controller-it/no-nome-estado-property-request-body.json");
		
		given()
			.body(jsonNoNomeEstadoProperty)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post("/estados")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
		
	}
	
	
	
	@Test
	public void givenAnEstadoDataToUpdateWhenPutOnEstadosEstadoIDThenCompareResponseBodyAndStatusResponse() {
	
		jsonEstadoPutRequestBody = GetJsonOnFile.filePathAndName(
				"/json/estado-controller-it/estado-put-request-body.json");
				
		given()
			.body(jsonEstadoPutRequestBody)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/1")
		.then()
			.body("nome", containsString("Amazonas"))
				.and()
			.body("uf", containsString("AM"))
				.and()
			.body("id", equalToObject((Number) 1))
				.and()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void givenAnEstadoToDeleteWithIdOneWhenDeleteThenTryToRemoveSameRecordThreeTimesWithTwoMessages() {
		
		//First Time
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.delete("/1")
		.then()
			.statusCode(204);

		//Second Time
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.delete("/1")
		.then()
			.statusCode(404)
		.and()
			.body("title", containsString("Resource not found"));
		
		//Third time
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.delete("/1")
		.then()
			.statusCode(404)
		.and()
			.body("title", containsString("Resource not found"));
	}
	
	//TODO DELETE 204 No Content || "title": 404 Resource not found
	
	//TODO Handles: 404 Not found, 200 Ok, 400 Bad Request.
	
	
	
	
	private void initializeData() {
		
		//Insert fist record
		Estado estado1 = new Estado();
		
		estado1.setId(1L);
		estado1.setNome("Rio de Janeiro");
		estado1.setUf("RJ");
		
		estadoRepository.save(estado1);
		
		//Insert second record
		Estado estado2 = new Estado();
		
		estado2.setId(2L);
		estado2.setNome("São Paulo");
		estado2.setUf("SP");
		
		estadoRepository.save(estado2);
		
		numberOfRecordsOnInitialization = (int) estadoRepository.count();
				
	}
	
}
