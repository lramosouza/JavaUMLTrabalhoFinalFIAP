package com.telegrambotbank.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Value Object que representa um cliente
 * 
 * @author user
 *
 */
public class ClienteVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private Date dataNascimento;
	private BigDecimal CPF;
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public BigDecimal getCPF() {
		return CPF;
	}

	public void setCPF(BigDecimal cPF) {
		CPF = cPF;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
