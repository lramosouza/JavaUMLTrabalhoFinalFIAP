package com.telegrambotbank.datatype;

import java.io.Serializable;

/**
 * Value Object que representa um Dependente de Conta Bancaria
 * 
 * @author Diogo Brito
 *
 */
public class DependenteVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String NomeDependente;

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
