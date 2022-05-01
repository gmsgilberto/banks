package br.com.gms.banks.gmsbanks.account.balance.vo;

import br.com.gms.banks.gmsbanks.account.balance.AccountBalance;
import lombok.Getter;

@Getter
public class TransferResult implements EventResponse {

	private static final long serialVersionUID = 1L;
	private AccountBalanceVO origin;
	private AccountBalanceVO destination;
	
	public TransferResult(AccountBalance origin, AccountBalance destination) {
		this.origin = new AccountBalanceVO(origin);
		this.destination = new AccountBalanceVO(destination);
	}
	
}
