package com.telegrambotbank.enumeration;

/**
 * Enum que centraliza os tipos de lan�amentos dispon�veis para a conta Banc�ria
 * @author BRQVotorantim
 *
 */
public enum TipoLancamentoEnum {
	
	TARIFA("T"),
	CREDITO("C"),
	DEBITO("D");
	
	private String tipoLancamento;
	
	TipoLancamentoEnum(String tipoLancamento){
		this.tipoLancamento = tipoLancamento;
	}

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	
	
}
