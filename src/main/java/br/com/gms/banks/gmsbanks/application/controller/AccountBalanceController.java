package br.com.gms.banks.gmsbanks.application.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gms.banks.gmsbanks.account.balance.AccountEventType;
import br.com.gms.banks.gmsbanks.account.balance.event.Transfer;
import br.com.gms.banks.gmsbanks.account.balance.event.builder.EventBuilder;
import br.com.gms.banks.gmsbanks.account.balance.service.AccountBalanceService;
import br.com.gms.banks.gmsbanks.account.balance.vo.AccountEventRequest;
import br.com.gms.banks.gmsbanks.account.balance.vo.EventResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * @author gilberto
 */
@Tag(name = "Account Balance Services")
@RestController
@AllArgsConstructor
public class AccountBalanceController {

	private AccountBalanceService service;
	
	@GetMapping(path = "/balance")
	public ResponseEntity<BigDecimal> getAccountBalance(@RequestParam(name = "account_id", required = true) Integer accountid){
		var balance = this.service.findByAccountId(accountid);
		if(balance == null) {
			return new ResponseEntity<>(BigDecimal.ZERO, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(balance.getAmount());
	}
	
	
	@PostMapping(path = "/event")
	public ResponseEntity<EventResponse> bookEvent(@RequestBody AccountEventRequest request ){
		
		var event = EventBuilder.build(request);
		if(event.getEventType() == AccountEventType.TRANSFER) {
			return handleTransfer((Transfer)event);
		}else {
			var balance = this.service.bookEvent(event.getAccountid(), event);
			if(balance == null) {
				return EventResponse.notFound();
			}
			return ResponseEntity.status(201).body(event.result(balance));
		}
	}


	/**
	 * @param transfer
	 * @return
	 */
	private ResponseEntity<EventResponse> handleTransfer(Transfer transfer) {
		var transferResult = this.service.bookTransfer(transfer);
		if(transferResult == null) {
			return EventResponse.notFound();
		}
		return ResponseEntity.status(201).body(transferResult);
	}


	

}
