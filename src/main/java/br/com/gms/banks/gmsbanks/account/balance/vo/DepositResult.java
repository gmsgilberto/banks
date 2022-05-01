package br.com.gms.banks.gmsbanks.account.balance.vo;

import br.com.gms.banks.gmsbanks.account.balance.AccountBalance;
import lombok.Getter;

@Getter
public class DepositResult implements EventResponse {

	private static final long serialVersionUID = 1L;
	private AccountBalanceVO destination;
	
	public DepositResult(AccountBalance balance) {
		this.destination = new AccountBalanceVO(balance);
	}
	
}
