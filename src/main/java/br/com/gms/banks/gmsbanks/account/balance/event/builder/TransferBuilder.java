package br.com.gms.banks.gmsbanks.account.balance.event.builder;

import br.com.gms.banks.gmsbanks.account.balance.event.Transfer;
import br.com.gms.banks.gmsbanks.account.balance.vo.AccountEventRequest;

/**
 * @author gilberto
 */
public class TransferBuilder extends EventBuilder<Transfer> {

	@Override
	public Transfer handleBuild(AccountEventRequest request) {
		var from = Integer.valueOf(request.getOrigin());
		var to = Integer.valueOf(request.getDestination());
		return  new Transfer(from,to,request.getAmount());
	}

}
