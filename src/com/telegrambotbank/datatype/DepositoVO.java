package com.telegrambotbank.datatype;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Value Object que representa um depósito bancário
 * @author user
 *
 */
public class DepositoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal valor;
	private String contaDestino;
	private String agenciaDestino;
	private String contaDepositante;
	private String agenciaDepositante;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public String getAgenciaDestino() {
		return agenciaDestino;
	}

	public void setAgenciaDestino(String agenciaDestino) {
		this.agenciaDestino = agenciaDestino;
	}

	public String getContaDepositante() {
		return contaDepositante;
	}

	public void setContaDepositante(String contaDepositante) {
		this.contaDepositante = contaDepositante;
	}

	public String getAgenciaDepositante() {
		return agenciaDepositante;
	}

	public void setAgenciaDepositante(String agenciaDepositante) {
		this.agenciaDepositante = agenciaDepositante;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
