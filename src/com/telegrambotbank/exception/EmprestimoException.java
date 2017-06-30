package com.telegrambotbank.exception;

public class EmprestimoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3832398976700944607L;
	
	private String message;
	
	public EmprestimoException(String message){
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
