package br.com.gms.banks.gmsbanks.application.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import br.com.gms.banks.gmsbanks.account.balance.vo.AccountEventRequest;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
class AccountBalanceApiScriptTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void script() {
		
		resetDatabase();
		getBalanceForNonExistingaccount();
		createAccountWithInicialBalance();
		depositIntoExistingAccount();
		getBalanceForExistingAccount();
		withdrawFromNonExistingAccount();
		withdrawFromExistingAccount();
		transferFromExistingAccount();
		transferFromNonExistingAccount();
		
		var response = this.restTemplate.getForEntity(buildUrl("balance?account_id=100"), String.class);
		baseAsserts(response, 200, "0");
		response = this.restTemplate.getForEntity(buildUrl("balance?account_id=300"), String.class);
		baseAsserts(response, 200, "15");
		
		resetDatabase();
		response = this.restTemplate.getForEntity(buildUrl("balance?account_id=100"), String.class);
		baseAsserts(response, 404);

	}


	/**
	 * # Transfer from existing account
	 * POST /event {"type":"transfer", "origin":"100", "amount":15, "destination":"300"}
	 * 201 {"origin": {"id":"100", "balance":0}, "destination": {"id":"300", "balance":15}}
	 */
	private void transferFromExistingAccount() {
		log.info("# Transfer from existing account");
		var request = new AccountEventRequest();
		request.transfer("100","300",new BigDecimal("15"));
		var response = this.restTemplate.postForEntity( buildUrl("event"),  request, String.class);
		baseAsserts(response, 201, "{\"origin\": {\"id\":\"100\", \"balance\":0}, \"destination\": {\"id\":\"300\", \"balance\":15}}");
	}

	/**
	 * # Transfer from non-existing account
	 * POST /event {"type":"transfer", "origin":"200", "amount":15, "destination":"300"}
	 * 404 0
	 */
	private void transferFromNonExistingAccount() {
		log.info("# Transfer from existing account");
		var request = new AccountEventRequest();
		request.transfer("200","300",new BigDecimal("15"));
		var response = this.restTemplate.postForEntity( buildUrl("event"),  request, String.class);
		baseAsserts(response, 404);
	}


	
	/**
	 * # Withdraw from non-existing account
	 * POST /event {"type":"withdraw", "origin":"200", "amount":10}
	 * 404 0
	 * */
	private void withdrawFromNonExistingAccount() {
		log.info("# Withdraw from non-existing account");
		var request = new AccountEventRequest();
		request.withdraw("200", new BigDecimal("10"));
		var response = this.restTemplate.postForEntity( buildUrl("event"),  request, String.class);
		baseAsserts(response,404);
	}
	
	/**
	 * 	# Withdraw from existing account
	 * POST /event {"type":"withdraw", "origin":"100", "amount":5}
	 * 201 {"origin": {"id":"100", "balance":15}}
	 */
	private void withdrawFromExistingAccount() {
		log.info("# Withdraw from existing account");
		var request = new AccountEventRequest();
		request.withdraw("100", new BigDecimal("5"));
		var response = this.restTemplate.postForEntity( buildUrl("event"),  request, String.class);
		baseAsserts(response,201,"{\"origin\": {\"id\":\"100\", \"balance\":15}}");
	}


	/**
	 * # Get balance for existing account
	 * GET /balance?account_id=100
	 * 200 20
	 */
	private void getBalanceForExistingAccount() {
		
		log.info("# Get balance for existing account");
		var response = this.restTemplate.getForEntity(buildUrl("balance?account_id=100"), String.class);
		baseAsserts(response, 200, "20");

	}


	/**
	 * # Deposit into existing account
	 * POST /event {"type":"deposit", "destination":"100", "amount":10}
	 * 201 {"destination": {"id":"100", "balance":20}}
	 */
	private void depositIntoExistingAccount() {
		log.info("# Deposit into existing account");
		var request = new AccountEventRequest();
		request.deposit("100", new BigDecimal("10"));
		var response = this.restTemplate.postForEntity( buildUrl("event"),  request, String.class);
		baseAsserts(response,201, "{\"destination\": {\"id\":\"100\", \"balance\":20}}");

	}


	/**
	 * # Create account with initial balance
	 * POST /event {"type":"deposit", "destination":"100", "amount":10}
	 * 201 
	 */
	private void createAccountWithInicialBalance() {
		
		log.info("# Create account with initial balance");
		var request = new AccountEventRequest();
		request.deposit("100", new BigDecimal("10"));
		
		var response = this.restTemplate.postForEntity( buildUrl("event"),  request, String.class);
		String expected = "{\"destination\": {\"id\":\"100\", \"balance\":10}}";
		baseAsserts(response,201, expected);
		
	}



	private void baseAsserts(ResponseEntity<String> response, int httpStatusCode) {
		baseAsserts(response, httpStatusCode, null);
	}
	
	private void baseAsserts(ResponseEntity<String> response, int httpStatusCode, String jsonresult) {
		Assertions.assertNotNull(response);
		Assertions.assertEquals(httpStatusCode, response.getStatusCodeValue());
		if(jsonresult != null) {
			log.info(response.getBody());
			Assertions.assertNotNull(response.getBody());
			assertEquals(replaceBlanks(jsonresult),replaceBlanks(response.getBody()));
		}
	}


	private void getBalanceForNonExistingaccount() {
		log.info("# Get balance for non-existing account");
		var response = this.restTemplate.getForEntity(buildUrl("balance?account_id=1234"), String.class);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(404, response.getStatusCodeValue());
	}


	private void resetDatabase() {
		log.info("Reset Database");
		var response = this.restTemplate.postForEntity(buildUrl("reset"), null, String.class);
		baseAsserts(response, 200);
	}
	
	
	private final String buildUrl(String endpoint) {
		return String.format("http://localhost:%s/%s", port, endpoint);
	}

	private String replaceBlanks(String value) {
		return value.replace("\n", "").replace(" ", "");
	}


}
