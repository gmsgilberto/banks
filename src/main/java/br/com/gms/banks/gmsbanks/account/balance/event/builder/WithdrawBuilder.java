package br.com.gms.banks.gmsbanks.account.balance.event.builder;

import br.com.gms.banks.gmsbanks.account.balance.event.Withdraw;
import br.com.gms.banks.gmsbanks.account.balance.vo.AccountEventRequest;

/**
 * @author gilberto
 */
public class WithdrawBuilder extends EventBuilder<Withdraw> {

	@Override
	protected Withdraw handleBuild(AccountEventRequest request) {
		return  new Withdraw(Integer.valueOf(request.getOrigin()), request.getAmount());
	}

}
