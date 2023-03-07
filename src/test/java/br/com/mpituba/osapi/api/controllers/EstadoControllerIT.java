package br.com.mpituba.osapi.api.controllers;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import br.com.mpituba.osapi.domain.model.Estado;
import br.com.mpituba.osapi.domain.repository.EstadoRepository;
import br.com.mpituba.osapi.util.DatabaseCleaner;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class EstadoControllerIT {

	@LocalServerPort
	private int port;
	
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
	public void afterInitializeMustContainTwoRecords() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(2));
			
	}
	
	
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
				
	}
	
}
