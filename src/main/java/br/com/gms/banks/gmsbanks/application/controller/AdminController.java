package br.com.gms.banks.gmsbanks.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gms.banks.gmsbanks.account.balance.repository.AccountBalanceRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * @author gilberto
 */
@Tag(name = "Admin Services")
@RestController
@AllArgsConstructor
public class AdminController {

	private AccountBalanceRepository repository;

	/**
	 * Reset state
	 * @return
	 */
	@PostMapping(path = "reset")
	public ResponseEntity<String> resetDatabase() {  
		int quantity = this.repository.truncate();
		return ResponseEntity.ok(String.format("Número de Contas Deletadas %s ", quantity));
	}
	

	
	
}
