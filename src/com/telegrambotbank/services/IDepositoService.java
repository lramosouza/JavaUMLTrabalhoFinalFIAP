package com.telegrambotbank.services;

import java.io.IOException;

import com.telegrambotbank.datatype.DepositoVO;
import com.telegrambotbank.exception.ContaInexistenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;

/**
 * Serviços disponíveis para depósito
 * @author user
 *
 */
public interface IDepositoService {
	
	/**
	 * Método responsável por efetuar um depósito bancário entre contas
	 * 
	 * @param dadosDeposito
	 * @return
	 * @throws SaldoInsuficienteException
	 */
	public String depositar(DepositoVO dadosDeposito) throws SaldoInsuficienteException, ContaInexistenteException, IOException;
		
}
