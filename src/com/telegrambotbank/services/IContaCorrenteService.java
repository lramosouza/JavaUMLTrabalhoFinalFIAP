package com.telegrambotbank.services;

import java.io.IOException;

import com.telegrambotbank.datatype.OperacaoVO;
import com.telegrambotbank.exception.SaldoInsuficienteException;

/**
 * Servi�os dispon�veis para a conta corrente
 * @author user
 *
 */
public interface IContaCorrenteService {
	/**
	 * Credita uma conta banc�ria
	 * @param dadosOperacao
	 * @return
	 * @throws SaldoInsuficienteException
	 */
	public String creditarContaBancaria(OperacaoVO dadosOperacao) throws SaldoInsuficienteException, IOException;
	
	/**
	 * Debita uma conta banc�ria
	 * @param dadosOperacao
	 * @return
	 * @throws SaldoInsuficienteException
	 */
	public String debitarContaBancaria(OperacaoVO dadosOperacao) throws SaldoInsuficienteException, IOException;

}
