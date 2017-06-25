package com.telegrambotbank.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.telegrambotbank.enumeration.TipoLancamentoEnum;

/**
 * Value Object que representa uma operação
 * 
 * @author user
 *
 */
public class LancamentoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal valorLancamento;
	private String contaBancaria;
	private String agenciaBancaria;
	private String tipoLancamento;
	private LocalDate dataLancamento;

	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
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

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
