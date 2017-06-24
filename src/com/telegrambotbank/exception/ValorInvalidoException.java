package com.telegrambotbank.exception;

public class ValorInvalidoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message = "Valor Inválido!";

	public ValorInvalidoException(String message) {
		super();
		this.message = message;
	}

	public ValorInvalidoException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
