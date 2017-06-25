package com.telegrambotbank.exception;

/**
 * Exception para conta inexistente
 * @author user
 *
 */
public class ContaOuAgenciaInvalidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message = "O n�mero conta corrente ou ag�ncia banc�ria � inv�lido, verifique os dados e tente novamente!";

	public ContaOuAgenciaInvalidaException(String message) {
		super();
		this.message = message;
	}

	public ContaOuAgenciaInvalidaException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
