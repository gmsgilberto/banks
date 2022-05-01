package br.com.gms.banks.gmsbanks.account.balance;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.gms.banks.gmsbanks.account.balance.vo.EventResponse;
import lombok.Getter;

/**
 * @author gilberto
 */
@Getter
public abstract class AccountEvent<T extends EventResponse> implements Serializable{

	private static final long serialVersionUID = 1L;
	private final AccountEventType eventType;
	private Integer accountid;
	private BigDecimal amount;
	
	protected AccountEvent(AccountEventType eventType, Integer accountid,BigDecimal amount) {
		super();
		this.eventType = eventType;
		this.accountid = accountid;
		this.amount = amount == null ? BigDecimal.ZERO : amount;
	}
	
	
	public abstract T result(AccountBalance accountBalance, AccountBalance... colateral);
	
	public abstract BigDecimal calcBookValue(Integer accountid);
	
}
