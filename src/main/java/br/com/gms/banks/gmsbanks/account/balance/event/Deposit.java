package br.com.gms.banks.gmsbanks.account.balance.event;

import java.math.BigDecimal;

import br.com.gms.banks.gmsbanks.account.balance.AccountBalance;
import br.com.gms.banks.gmsbanks.account.balance.AccountEvent;
import br.com.gms.banks.gmsbanks.account.balance.AccountEventType;
import br.com.gms.banks.gmsbanks.account.balance.vo.DepositResult;

/**
 * @author gilberto
 */
public class Deposit extends AccountEvent<DepositResult> {

	private static final long serialVersionUID = 1L;

	public Deposit(Integer accountid, BigDecimal amount) {
		super(AccountEventType.DEPOSIT, accountid, amount);
	}

	@Override
	public BigDecimal calcBookValue(Integer accountid) {
		if(this.getAccountid().equals(accountid)) {
			return getAmount();
		}
		return BigDecimal.ZERO;
		
	}

	@Override
	public DepositResult result(AccountBalance accountBalance, AccountBalance... colateral) {
		return new DepositResult(accountBalance);
	}

}
