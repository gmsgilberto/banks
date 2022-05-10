package br.com.gms.banks.gmsbanks.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.gms.banks.gmsbanks.account.balance.vo.EventResponse;
import br.com.gms.banks.gmsbanks.exeptions.LimiteException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ErrorHandlerAdivice {

	
	@ExceptionHandler(LimiteException.class)
	public ResponseEntity<EventResponse> handleLimiteException(LimiteException e){	
		log.error(e.getMessage(),e);
		return EventResponse.notFound();
	}
	
}
