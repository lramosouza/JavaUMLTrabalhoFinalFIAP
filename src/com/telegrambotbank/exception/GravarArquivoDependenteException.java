package com.telegrambotbank.exception;

public class GravarArquivoDependenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message = "Erro na gravação do arquivo de dependentes, tente novamente!";

	public GravarArquivoDependenteException(String message) {
		super();
		this.message = message;
	}

	public GravarArquivoDependenteException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
