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

	public String getMessage() {
		return "Acho que este campo não foi preenchido corretamente, vamos tentar novamente? :D";
	}

	public void setMessage(String message) {
	}

}
