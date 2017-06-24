package com.telegrambotbank.datatype;

import java.io.Serializable;

import com.telegrambrank.annotation.PosicaoDependente;

/**
 * Value Object que representa um Dependente de Conta Bancaria
 * 
 * @author Diogo Brito
 *
 */
public class DependenteVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PosicaoDependente(posicaoInicial = 1, posicaoFinal = 100)
	private String NomeDependente;

	@PosicaoDependente(posicaoInicial = 112, posicaoFinal = 123)
	private String CpfDependente;

	public String getNomeDependente() {
		return NomeDependente;
	}

	public void setNomeDependente(String nomeDependente) {
		NomeDependente = nomeDependente;
	}

	public String getCpfDependente() {
		return CpfDependente;
	}

	public void setCpfDependente(String cpfDependente) {
		CpfDependente = cpfDependente;
	}

}
