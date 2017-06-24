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
		return "Campo inválido, excedeu o limite de posições";
	}

	public void setMessage(String message) {
	}

}
