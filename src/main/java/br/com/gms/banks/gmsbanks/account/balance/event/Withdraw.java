package br.com.gms.banks.gmsbanks.account.balance.event;

import java.math.BigDecimal;

import br.com.gms.banks.gmsbanks.account.balance.AccountBalance;
import br.com.gms.banks.gmsbanks.account.balance.AccountEvent;
import br.com.gms.banks.gmsbanks.account.balance.AccountEventType;
import br.com.gms.banks.gmsbanks.account.balance.vo.WithdrawResult;

/**
 * @author gilberto
 */
public class Withdraw extends AccountEvent<WithdrawResult> {

	private static final long serialVersionUID = -1L;
	
	public Withdraw(Integer accountid, BigDecimal amount) {
		super(AccountEventType.WITHDRAW, accountid, amount);
	}

	@Override
	public WithdrawResult result(AccountBalance accountBalance, AccountBalance... colateral) {
		return new WithdrawResult(accountBalance);
	}
	
	@Override
	public BigDecimal calcBookValue(Integer accountid) {
		if(this.getAccountid().equals(accountid)) {
			return getAmount().multiply(new BigDecimal(-1));
		}
		return BigDecimal.ZERO;
	}

}
