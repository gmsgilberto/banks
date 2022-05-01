package br.com.gms.banks.gmsbanks.application.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import br.com.gms.banks.gmsbanks.application.controller.AdminController;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
class AdminControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private AdminController adminController;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void test_direto_resetStateBeforeStartingtest() {
		log.info("# Reset state before starting tests");
		var response = this.adminController.resetDatabase();
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	
	@Test
	void test_rest_resetStateBeforeStartingtest() {
		log.info("# Teste Rest API Reset state before starting tests");
		var response = this.restTemplate.postForEntity(buildUrl("reset"), null, String.class);
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	
	
	private final String buildUrl(String endpoint) {
		return String.format("http://localhost:%s/%s", port, endpoint);
	}
	
}
