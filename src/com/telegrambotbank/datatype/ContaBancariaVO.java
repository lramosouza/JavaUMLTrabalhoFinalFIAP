package com.telegrambotbank.datatype;

import java.io.Serializable;
import java.math.BigDecimal;

import com.telegrambotbank.enumeration.TipoContaCorrenteEnum;
import com.telegrambrank.annotation.PosicaoConta;

/**
 * Value Object que representa uma conta bancária
 * 
 * @author user
 *
 */
public class ContaBancariaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClienteVO cliente;
	
	@PosicaoConta(posicaoInicial = 6, posicaoFinal = 12)
	private String nuContaCorrete;
	
	@PosicaoConta(posicaoInicial = 0, posicaoFinal = 4)
	private String agenciaBancaria;
	
	@PosicaoConta(posicaoInicial = 14, posicaoFinal = 24)
	private BigDecimal saldo;
	
	@PosicaoConta(posicaoInicial = 119, posicaoFinal = 120)
	private TipoContaCorrenteEnum tipo;

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
