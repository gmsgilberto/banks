package br.com.gms.banks.gmsbanks.account.balance.event;

import java.math.BigDecimal;

import br.com.gms.banks.gmsbanks.account.balance.AccountBalance;
import br.com.gms.banks.gmsbanks.account.balance.AccountEvent;
import br.com.gms.banks.gmsbanks.account.balance.AccountEventType;
import br.com.gms.banks.gmsbanks.account.balance.vo.TransferResult;
import lombok.Getter;

/**
 * @author gilberto
 */
public class Transfer extends AccountEvent<TransferResult> {

	private static final long serialVersionUID = -1L;
	
	@Getter
	private Integer to;
	
	public Transfer(Integer from, Integer to, BigDecimal amount) {
		super(AccountEventType.TRANSFER, from, amount);
		this.to = to;
	}
	
	@Override
	public TransferResult result(AccountBalance accountBalance, AccountBalance... colateral) {
		return null;
	}
	
	@Override
	public BigDecimal calcBookValue(Integer accountid) {
		if(this.getAccountid().equals(accountid)) {
			return getAmount().multiply(new BigDecimal(-1));
		}
		if(this.getTo().equals(accountid)) {
			return getAmount();
		}
		return BigDecimal.ZERO;
	}

}
