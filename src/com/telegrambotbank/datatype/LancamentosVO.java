package com.telegrambotbank.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.telegrambrank.annotation.PosicaoLancamentos;

public class LancamentosVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PosicaoLancamentos(posicaoInicial = 1, posicaoFinal = 2)
	private String tipoLancamento;
	
	@PosicaoLancamentos(posicaoInicial = 4, posicaoFinal = 12)
	private BigDecimal valor;
	
	@PosicaoLancamentos(posicaoInicial = 14, posicaoFinal = 24)
	private LocalDate lancamento;

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getLancamento() {
		return lancamento;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}
	
}
