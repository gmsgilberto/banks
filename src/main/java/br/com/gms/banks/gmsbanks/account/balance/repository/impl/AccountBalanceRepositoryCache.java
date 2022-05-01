/**
 * 
 */
package br.com.gms.banks.gmsbanks.account.balance.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.gms.banks.gmsbanks.account.balance.AccountBalance;
import br.com.gms.banks.gmsbanks.account.balance.repository.AccountBalanceRepository;

/**
 * @author gilberto
 */
@Component
public class AccountBalanceRepositoryCache implements AccountBalanceRepository{

	private final Map<Integer, AccountBalance> cache;
	
	public AccountBalanceRepositoryCache() {
		this.cache = new HashMap<>();
	}
	
	@Override
	public int truncate() {
		int previusSize = this.cache.size();
		this.cache.clear();
		return previusSize;
	}
	
	
	@Override
	public AccountBalance findByAccountId(Integer accountId) {
		var balance = this.cache.get(accountId);
		return balance == null ? null : new AccountBalance(balance);
	}
	
	@Override
	public AccountBalance createNewAccountBalance(Integer accountid) {
		var balance = new AccountBalance(accountid);
		this.cache.put(accountid, balance);
		return findByAccountId(accountid);
	}
	
	@Override
	public AccountBalance update(AccountBalance balance) {
		this.cache.put(balance.getId(), balance);
		return findByAccountId(balance.getId());
	}
	
}
