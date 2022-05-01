/**
 * 
 */
package br.com.gms.banks.gmsbanks.account.balance.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.gms.banks.gmsbanks.account.balance.AccountEventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gilberto
 */
@Getter
@Setter
@NoArgsConstructor
public class AccountEventRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String type;
	private String destination;
	private String origin;
	private BigDecimal amount;
	
	public void deposit(String destination, BigDecimal amount) {
		this.type = AccountEventType.DEPOSIT.getTitle();
		this.amount = amount;
		this.destination = destination;
	}

	public void withdraw(String origin, BigDecimal amount) {
		this.type = AccountEventType.WITHDRAW.getTitle();
		this.amount = amount;
		this.origin = origin;
	}
	
	public void transfer(String from, String to, BigDecimal amount) {
		this.type = AccountEventType.TRANSFER.getTitle();
		this.amount = amount;
		this.origin = from;
		this.destination = to;
	}
	
	
	
	
	
}
