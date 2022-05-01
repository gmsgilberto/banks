package br.com.gms.banks.gmsbanks.account.balance;

import br.com.gms.banks.gmsbanks.account.balance.event.builder.DeposityBuilder;
import br.com.gms.banks.gmsbanks.account.balance.event.builder.EventBuilder;
import br.com.gms.banks.gmsbanks.account.balance.event.builder.TransferBuilder;
import br.com.gms.banks.gmsbanks.account.balance.event.builder.WithdrawBuilder;
import lombok.Getter;

public enum AccountEventType {

	DEPOSIT("deposit", new DeposityBuilder()),
	WITHDRAW("withdraw", new WithdrawBuilder()),
	TRANSFER("transfer", new TransferBuilder());
	
	
	
	@Getter
	private final String title;
	
	@Getter
	private final EventBuilder<?> builder;
	
	AccountEventType(final String title, final EventBuilder<?> builder){
		this.title = title;
		this.builder = builder;
	}
	
	@Override
	public String toString() {
		return this.title;
	}
	
	public static AccountEventType get(String title) {
		for(AccountEventType eventType : values()) {
			if(eventType.title.equalsIgnoreCase(title)) {
				return eventType;
			}
		}
		throw new IllegalArgumentException(String.format("Evento nao tratado pelo sistema: %s" ,title));
	}
	
}
