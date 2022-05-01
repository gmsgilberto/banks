package br.com.gms.banks.gmsbanks.account.balance.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.gms.banks.gmsbanks.account.balance.AccountBalance;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gilberto
 */
@Getter
@Setter
@NoArgsConstructor
public class AccountBalanceVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private BigDecimal balance;

	public AccountBalanceVO(AccountBalance balance) {
		this.id = balance.getId().toString();
		this.balance = balance.getAmount();
	}
	
}
