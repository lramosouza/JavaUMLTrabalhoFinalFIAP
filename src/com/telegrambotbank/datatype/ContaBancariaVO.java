package com.telegrambotbank.datatype;

import java.io.Serializable;
import java.math.BigDecimal;

import com.telegrambotbank.enumeration.TipoContaCorrenteEnum;

/**
 * Value Object que representa uma conta bancária
 * 
 * @author user
 *
 */
public class ContaBancariaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ClienteVO cliente;

	private String agenciaBancaria;

	private String nuContaCorrete;

	private TipoContaCorrenteEnum tipo;

	private BigDecimal saldo;

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public String getNuContaCorrete() {
		return nuContaCorrete;
	}

	public void setNuContaCorrete(String nuContaCorrete) {
		this.nuContaCorrete = nuContaCorrete;
	}

	public String getAgenciaBancaria() {
		return agenciaBancaria;
	}

	public void setAgenciaBancaria(String agenciaBancaria) {
		this.agenciaBancaria = agenciaBancaria;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public TipoContaCorrenteEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoContaCorrenteEnum tipo) {
		this.tipo = tipo;
	}

}
