package br.com.gms.banks.gmsbanks.account.balance.event.builder;

import br.com.gms.banks.gmsbanks.account.balance.AccountEvent;
import br.com.gms.banks.gmsbanks.account.balance.AccountEventType;
import br.com.gms.banks.gmsbanks.account.balance.vo.AccountEventRequest;


/**
 * @author gilberto
 */
public abstract class EventBuilder <T extends AccountEvent<?>>{

	public static AccountEvent<?> build(AccountEventRequest request) {
		
		return AccountEventType
					.get(request.getType())
					.getBuilder()
					.handleBuild(request);
		
	}
	
	protected abstract T handleBuild(AccountEventRequest request);
	
}
