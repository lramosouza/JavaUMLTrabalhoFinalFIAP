package com.telegrambotbank.enumeration;

/**
 * Enum para centralizar as opções disponíveis do BOT
 * 
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
	TARIFAS("/tarifas"),
	INCLUIR_DEPENDENTE("/incluirDependente"),
	EXTRATO("/extrato"),
	EXIBIR_MINHAS_INFORMACOES("/ExibirMinhasInformacoes");

	private String opcaoDesejada;

	OpcoesBotEnum(String opcaoDesejada) {
		this.opcaoDesejada = opcaoDesejada;
	}

	public String getOpcaoDesejada() {
		return opcaoDesejada;
	}

	public void setOpcaoDesejada(String opcaoDesejada) {
		this.opcaoDesejada = opcaoDesejada;
	}

}
