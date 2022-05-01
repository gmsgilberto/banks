package br.com.gms.banks.gmsbanks.account.balance.event.builder;

import br.com.gms.banks.gmsbanks.account.balance.event.Deposit;
import br.com.gms.banks.gmsbanks.account.balance.vo.AccountEventRequest;

/**
 * @author gilberto
 */
public class DeposityBuilder extends EventBuilder<Deposit> {

	@Override
	protected Deposit handleBuild(AccountEventRequest request) {
		return  new Deposit(Integer.valueOf(request.getDestination()), request.getAmount());
	}


}
