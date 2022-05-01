/**
 * 
 */
package br.com.gms.banks.gmsbanks.account.balance.repository;

import br.com.gms.banks.gmsbanks.account.balance.AccountBalance;

/**
 * @author gilberto
 */
public interface AccountBalanceRepository {

	/**
	 * Reset database
	 * @return
	 */
	int truncate();
	
	
	/**
	 * @param accountId
	 * @return
	 */
	AccountBalance findByAccountId(Integer accountId);


	/**
	 * @param accountid
	 * @return
	 */
	AccountBalance createNewAccountBalance(Integer accountid);


	AccountBalance update(AccountBalance balance);
	
}
