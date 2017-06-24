package com.telegrambotbank.services.impl;

import java.io.IOException;

import com.telegrambotbank.datatype.DepositoVO;
import com.telegrambotbank.datatype.OperacaoVO;
import com.telegrambotbank.exception.ContaInexistenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;
import com.telegrambotbank.opcoes.helper.DepositoBancarioHelper;
import com.telegrambotbank.services.IDepositoService;

/**
 * Implementação do serviço de depósito
 * 
 * @author user
 *
 */
public class DepositoServiceImpl implements IDepositoService {

	ContaCorrenteServiceImpl contaCorrenteServices = new ContaCorrenteServiceImpl();
	
	/**
	 * Efetua o depósito na conta
	 */
	@Override
	public String depositar(DepositoVO dadosDeposito)
			throws SaldoInsuficienteException, ContaInexistenteException, IOException {

		OperacaoVO dadosOperacaoDebito = DepositoBancarioHelper.montarDadosOperacaoDebito(dadosDeposito);
		
		contaCorrenteServices.debitarContaBancaria(dadosOperacaoDebito);
		
		OperacaoVO dadosOperacaoCredito = DepositoBancarioHelper.montarDadosOperacaoCredito(dadosDeposito);

		contaCorrenteServices.creditarContaBancaria(dadosOperacaoCredito);

		String mensagemRetorno = ArquivoContaCorrenteUtil.obterMensagemSucesso("deposito.sucesso");
		System.out.println(mensagemRetorno);
		
		return mensagemRetorno;
	}

}
