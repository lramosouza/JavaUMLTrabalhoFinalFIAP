package com.telegrambotbank.services;

import java.io.IOException;

import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;

/**
 * Serviços disponíveis para a conta corrente
 * @author user
 *
 */
public interface IContaCorrenteService {
	/**
	 * Credita uma conta bancária
	 * @param dadosOperacao
	 * @return
	 * @throws SaldoInsuficienteException
	 * @throws ArquivoInvalidoException 
	 */
	public String creditarContaBancaria(LancamentoVO dadosOperacao) throws SaldoInsuficienteException, IOException, ArquivoInvalidoException, GravarArquivoDependenteException;
	
	/**
	 * Debita uma conta bancária
	 * @param dadosOperacao
	 * @return
	 * @throws SaldoInsuficienteException
	 * @throws ArquivoInvalidoException 
	 */
	public String debitarContaBancaria(LancamentoVO dadosOperacao) throws SaldoInsuficienteException, IOException, ArquivoInvalidoException, GravarArquivoDependenteException;

}
