package com.telegrambotbank.exception;

import java.io.IOException;

public class ArquivoInvalidoException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArquivoInvalidoException(String message) {
		super();
	}

	public ArquivoInvalidoException() {
		super();
	}

	public String getMessage() {
		return "Houve uma falha na hora de encontrar o arquivo referente a sua operação :(  Verifique os dados e tente de novo!";
	}

	public void setMessage(String message) {
	}

}
