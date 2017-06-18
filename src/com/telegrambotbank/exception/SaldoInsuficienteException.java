package com.telegrambotbank.exception;

/**
 * Exception para saldo insuficiente
 * @author user
 *
 */
public class SaldoInsuficienteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message = "Saldo insuficiente para realizar a operação!";

	public SaldoInsuficienteException(String message) {
		super();
		this.message = message;
	}

	public SaldoInsuficienteException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
