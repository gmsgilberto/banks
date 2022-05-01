/**
 * 
 */
package br.com.gms.banks.gmsbanks.account.balance;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;

/**
 * @author gilberto
 */
@Getter
public class AccountBalance implements Serializable{

	private static final long serialVersionUID = 1L;


	private Integer id;
	private BigDecimal amount;
	
	public AccountBalance(Integer id) {
		this.id = id;
		this.amount = BigDecimal.ZERO;
	}
	
	public AccountBalance(AccountBalance original) {
		this.id = original.getId();
		this.amount = original.getAmount();
	}

	public synchronized AccountBalance bookEvent(AccountEvent<?> event) {
		this.amount = this.amount.add(event.calcBookValue(this.id));
		return this;
	}
	
}
