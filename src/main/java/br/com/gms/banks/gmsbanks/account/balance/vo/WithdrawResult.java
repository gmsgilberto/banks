package br.com.gms.banks.gmsbanks.account.balance.vo;

import br.com.gms.banks.gmsbanks.account.balance.AccountBalance;
import lombok.Getter;

@Getter
public class WithdrawResult implements EventResponse {

	private static final long serialVersionUID = 1L;
	private AccountBalanceVO origin;
	
	public WithdrawResult(AccountBalance balance) {
		this.origin = new AccountBalanceVO(balance);
	}
	
}
