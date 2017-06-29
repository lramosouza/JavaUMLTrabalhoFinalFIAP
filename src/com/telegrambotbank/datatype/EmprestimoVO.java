package com.telegrambotbank.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class EmprestimoVO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Integer codEmprestimo;
	
	private BigDecimal vlContratado;
	
	private BigDecimal vlCalculado;
	
	private BigDecimal vlParcela;
	
	private Date dtContracao;
	
	private Integer prazo;

	
	public Integer getCodEmprestimo() {
		return codEmprestimo;
	}

	public void setCodEmprestimo(Integer codEmprestimo) {
		this.codEmprestimo = codEmprestimo;
	}

	public BigDecimal getVlContratado() {
		return vlContratado;
	}

	public void setVlContratado(BigDecimal vlContratado) {
		this.vlContratado = vlContratado;
	}

	public BigDecimal getVlCalculado() {
		return vlCalculado;
	}

	public void setVlCalculado(BigDecimal vlCalculado) {
		this.vlCalculado = vlCalculado;
	}

	public BigDecimal getVlParcela() {
		return vlParcela;
	}

	public void setVlParcela(BigDecimal vlParcela) {
		this.vlParcela = vlParcela;
	}

	public Date getDtContracao() {
		return dtContracao;
	}

	public void setDtContracao(Date dtContracao) {
		this.dtContracao = dtContracao;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}
	
}
