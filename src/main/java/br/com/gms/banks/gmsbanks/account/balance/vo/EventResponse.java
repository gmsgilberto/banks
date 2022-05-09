package br.com.gms.banks.gmsbanks.account.balance.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface EventResponse extends Serializable{

	static ResponseEntity<EventResponse> notFound() {
		return new ResponseEntity<>(new NotFound(), HttpStatus.NOT_FOUND);
	}

	
	class NotFound extends BigDecimal implements EventResponse{
		private static final long serialVersionUID = 1L;
		public NotFound() {
			super("0");
		}
	}
	

}
