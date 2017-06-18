package com.telegrambotbank.datatype;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Value Object que representa uma operação
 * @author user
 *
 */
public class OperacaoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal valor;
	private String contaBancaria;
	private String agenciaBancaria;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(String contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public String getAgenciaBancaria() {
		return agenciaBancaria;
	}

	public void setAgenciaBancaria(String agenciaBancaria) {
		this.agenciaBancaria = agenciaBancaria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
