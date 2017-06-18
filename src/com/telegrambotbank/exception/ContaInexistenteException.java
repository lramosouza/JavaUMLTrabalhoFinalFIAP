package com.telegrambotbank.exception;

/**
 * Exception para conta inexistente
 * @author user
 *
 */
public class ContaInexistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message = "A conta de destino não existe, favor verifique os dados e tente novamente!";

	public ContaInexistenteException(String message) {
		super();
		this.message = message;
	}

	public ContaInexistenteException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
