package com.telegrambotbank.exception;

public class PrazoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9125548925284978133L;
	
	private String message = "Prazo máximo é até em 36 vezes!";
	
	public PrazoException(String message){
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
