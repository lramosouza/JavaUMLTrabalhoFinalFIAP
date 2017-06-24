package com.telegrambotbank.exception;

public class CampoInvalidoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CampoInvalidoException(String message) {
		super();
	}

	public CampoInvalidoException() {
		super();
	}

	public String getMessage(int positions) {
		return "Campo inv�lido, excedeu o limite de posi��es";
	}

	public void setMessage(String message) {
	}

}
