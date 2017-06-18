package com.telegrambotbank.enumeration;

/**
 * Enum para tipo de conta corrente
 * @author user
 *
 */
public enum TipoContaCorrenteEnum {

	CONJUNTA(1), SIMPLES(2);

	private Integer tipoConta;

	TipoContaCorrenteEnum(Integer tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Integer getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(Integer tipoConta) {
		this.tipoConta = tipoConta;
	}

}
