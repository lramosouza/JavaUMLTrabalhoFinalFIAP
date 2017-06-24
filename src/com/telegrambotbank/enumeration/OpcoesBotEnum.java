package com.telegrambotbank.enumeration;

/**
 * Enum para centralizar as op��es dispon�veis do bot
 * @author user
 *
 */
public enum OpcoesBotEnum {
	
	CRIAR_CONTA("/criarConta"),
	START("/start"),
	DEPOSITAR("/depositar"),
	SACAR("/sacar"),
	EMPRESTIMO("/emprestimo"),
	HELP("/help"),
	TARIFAS("/tarifas");
	
	
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
