package com.telegrambotbank.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.telegrambotbank.annotation.PosicaoEmprestimo;

public class EmprestimoVO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@PosicaoEmprestimo(posicaoInicial = 1, posicaoFinal = 14)
	private Integer codEmprestimo;
	
	@PosicaoEmprestimo(posicaoInicial = 16, posicaoFinal = 26)
	private BigDecimal vlContratado;
	
	@PosicaoEmprestimo(posicaoInicial = 28, posicaoFinal = 38)
	private BigDecimal vlCalculado;
	
	@PosicaoEmprestimo(posicaoInicial = 40, posicaoFinal = 50)
	private BigDecimal vlParcela;
	
	@PosicaoEmprestimo(posicaoInicial = 52, posicaoFinal = 62)
	private LocalDate dtContracao;
	
	@PosicaoEmprestimo(posicaoInicial = 64, posicaoFinal = 66)
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

	public LocalDate getDtContracao() {
		return dtContracao;
	}

	public void setDtContracao(LocalDate dtContracao) {
		this.dtContracao = dtContracao;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}
	
}
