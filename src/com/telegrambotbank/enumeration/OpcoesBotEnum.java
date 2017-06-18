package com.telegrambotbank.enumeration;

/**
 * Enum para centralizar as op��es dispon�veis do bot
 * @author user
 *
 */
public enum OpcoesBotEnum {
	
	DEPOSITAR("/depositar");
	
	private String opcaoDesejada;
	
	OpcoesBotEnum(String opcaoDesejada){
		this.opcaoDesejada=opcaoDesejada;
	}

	public String getOpcaoDesejada() {
		return opcaoDesejada;
	}

	public void setOpcaoDesejada(String opcaoDesejada) {
		this.opcaoDesejada = opcaoDesejada;
	}	

}
