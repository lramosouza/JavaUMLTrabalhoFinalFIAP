package com.telegrambotbank.enumeration;

/**
 * Enum responsável por centralizar as informações de tamanho mínimo e máximo de um campo no arquivo
 * @author BRQVotorantim
 *
 */
public enum PosicoesCamposEnum {
	
	// Dependentes
	NOME_DEPENDENTE(0, 100), 
	CPF_DEPENDENTE(11, 11),
	
	// Conta Corrente
	NU_CONTA_CORRENTE(6,6),
	AGENCIA_CONTA_CORRENTE(0,4),
	
	// Valor Deposito
	VALOR_DEPOSITO(0, 10),
	
	// Lancamentos
	TIPO_LANCAMENTO(1, 1),
	VALOR_LANCAMENTO(0, 10),
	DATA_LANCAMENTO(10, 10),
	
	// Emprestimo
	VALOR_CONTRATADO(0,10),
	PRAZO(0,2);
	
	private Integer posicaoMin;
	private Integer posicaoMax;

	PosicoesCamposEnum(Integer posicaoMin, Integer posicaoMax) {
		this.posicaoMin = posicaoMin;
		this.posicaoMax = posicaoMax;
	}

	public Integer getPosicaoMin() {
		return posicaoMin;
	}

	public void setPosicaoMin(Integer posicaoMin) {
		this.posicaoMin = posicaoMin;
	}

	public Integer getPosicaoMax() {
		return posicaoMax;
	}

	public void setPosicaoMax(Integer posicaoMax) {
		this.posicaoMax = posicaoMax;
	}	

}
