package com.telegrambotbank.datatype;

import java.io.Serializable;

import com.telegrambrank.annotation.PosicaoCliente;

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

	@PosicaoCliente(posicaoInicial = 26, posicaoFinal = 105)
	private String nome;
	
	@PosicaoCliente(posicaoInicial = 122, posicaoFinal = 132)
	private String dataNascimento;
	
	@PosicaoCliente(posicaoInicial = 107, posicaoFinal = 118)
	private String CPF;
	
	@PosicaoCliente(posicaoInicial = 134, posicaoFinal = 144)
	private String email;

	
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}

}
