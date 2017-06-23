package com.telegrambotbank.datatype;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
	
	@Posicao(posicaoInicial = 0, posicaoFinal = 49)
	private String nome;
	
	@Posicao(posicaoInicial = 49, posicaoFinal = 59)
	private String dataNascimento;
	
	@Posicao(posicaoInicial = 59, posicaoFinal = 70)
	private String CPF;
	
	@Posicao(posicaoInicial = 70, posicaoFinal = 115)
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
