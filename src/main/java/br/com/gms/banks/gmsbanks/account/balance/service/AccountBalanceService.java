/**
 * 
 */
package br.com.gms.banks.gmsbanks.account.balance.service;

import org.springframework.stereotype.Service;

import br.com.gms.banks.gmsbanks.account.balance.AccountBalance;
import br.com.gms.banks.gmsbanks.account.balance.AccountEvent;
import br.com.gms.banks.gmsbanks.account.balance.AccountEventType;
import br.com.gms.banks.gmsbanks.account.balance.event.Transfer;
import br.com.gms.banks.gmsbanks.account.balance.repository.AccountBalanceRepository;
import br.com.gms.banks.gmsbanks.account.balance.vo.TransferResult;
import lombok.AllArgsConstructor;

/**
 * @author gilberto
 */
@AllArgsConstructor
@Service
public class AccountBalanceService {
	
	private AccountBalanceRepository repository;
	
	/**
	 * @param accountid
	 * @param accountEvent
	 * @return
	 */
	public AccountBalance bookEvent(Integer accountid, AccountEvent<?> accountEvent) {
		
		var balance = this.findByAccountId(accountid);
		if(balance == null && accountEvent.getEventType() == AccountEventType.DEPOSIT) {
			balance = this.repository.createNewAccountBalance(accountid);
		}
		if(balance != null) {
			return this.repository.update(balance.bookEvent(accountEvent));
		}
		
		return null;
	}
	
	

	
	/**
	 * @param accountid
	 * @return
	 */
	public AccountBalance findByAccountId(Integer accountid) {
		return this.repository.findByAccountId(accountid);
	}



	/**
	 * @param transfer
	 * @return
	 */
	public TransferResult bookTransfer(Transfer transfer) {
		
		var from = this.findByAccountId(transfer.getAccountid());
		if(from == null) {
			return null;
		}
		
		var to = this.findByAccountId(transfer.getTo());
		if(to == null) {
			to = this.repository.createNewAccountBalance(transfer.getTo());
		}
		
		from = this.repository.update(from.bookEvent(transfer));
		to = this.repository.update(to.bookEvent(transfer));
		
		return new TransferResult(from, to);
		
	}
	
}
