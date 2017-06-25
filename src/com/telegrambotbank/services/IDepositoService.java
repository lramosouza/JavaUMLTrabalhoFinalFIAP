package com.telegrambotbank.services;

import java.io.IOException;

import com.telegrambotbank.datatype.DepositoVO;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.ContaOuAgenciaInvalidaException;
import com.telegrambotbank.exception.SaldoInsuficienteException;

/**
 * Servi�os dispon�veis para dep�sito
 * @author user
 *
 */
public interface IDepositoService {
	
	/**
	 * M�todo respons�vel por efetuar um dep�sito banc�rio entre contas
	 * 
	 * @param dadosDeposito
	 * @return
	 * @throws SaldoInsuficienteException
	 * @throws ArquivoInvalidoException 
	 */
	public String depositar(DepositoVO dadosDeposito) throws SaldoInsuficienteException, ContaOuAgenciaInvalidaException, IOException, ArquivoInvalidoException;
		
}
