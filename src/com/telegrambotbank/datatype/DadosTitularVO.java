package com.telegrambotbank.datatype;

import java.io.Serializable;

/**
 * Value Object que representa os dados do titular da conta e dos dependentes
 * 
 * @author BRQVotorantim
 *
 */
public class DadosTitularVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClienteVO dadosTitular;
	private DependenteVO dadosDependente;
	private ContaBancariaVO contaBancaria;

	public ClienteVO getDadosTitular() {
		return dadosTitular;
	}

	public void setDadosTitular(ClienteVO dadosTitular) {
		this.dadosTitular = dadosTitular;
	}

	public DependenteVO getDadosDependente() {
		return dadosDependente;
	}

	public void setDadosDependente(DependenteVO dadosDependente) {
		this.dadosDependente = dadosDependente;
	}

	public ContaBancariaVO getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancariaVO contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

}
