/**
 * 
 */
package br.com.gms.banks.gmsbanks.exeptions;

/**
 * @author gilberto
 *
 */
public class LimiteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LimiteException(String message) {
		super(message);
	}
	
}
