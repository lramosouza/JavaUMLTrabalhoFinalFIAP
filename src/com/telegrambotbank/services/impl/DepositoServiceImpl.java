package com.telegrambotbank.services.impl;

import java.io.IOException;

import com.telegrambotbank.datatype.DepositoVO;
import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.ContaOuAgenciaInvalidaException;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;
import com.telegrambotbank.opcoes.helper.DepositoBancarioHelper;
import com.telegrambotbank.services.IDepositoService;

/**
 * Implementa��o do servi�o de dep�sito
 * 
 * @author user
 *
 */
public class DepositoServiceImpl implements IDepositoService {

	ContaCorrenteServiceImpl contaCorrenteServices = new ContaCorrenteServiceImpl();
	
	/**
	 * Efetua o dep�sito na conta
	 * @throws ArquivoInvalidoException 
	 */
	@Override
	public String depositar(DepositoVO dadosDeposito)
			throws SaldoInsuficienteException, ContaOuAgenciaInvalidaException, IOException, ArquivoInvalidoException {
	
		LancamentoVO dadosOperacaoDebito = DepositoBancarioHelper.montarDadosOperacaoDebito(dadosDeposito);
		LancamentoVO dadosOperacaoCredito = DepositoBancarioHelper.montarDadosOperacaoCredito(dadosDeposito);
		
		// Antes de debitar ou creditar contas, verifica se o arquivo referente
		// a elas � v�lido, caso contr�rio, a conta ou ag�ncia n�o existem
		DepositoBancarioHelper.validarArquivoAgenciaEContaDeposito(dadosOperacaoDebito, dadosOperacaoCredito);
		
		// Usa o servi�o de conta corrente para efetuar d�bito da conta depositante
		try {
			contaCorrenteServices.debitarContaBancaria(dadosOperacaoDebito);
		} catch (GravarArquivoDependenteException e) {
			throw new ArquivoInvalidoException();
		}
		
		
		// Usa o servi�o do conta corrente para efetuar cr�dito na conta de destino do dep�sito
		try {
			contaCorrenteServices.creditarContaBancaria(dadosOperacaoCredito);
		} catch (GravarArquivoDependenteException e) {
			throw new ArquivoInvalidoException();
		}
		

		String mensagemRetorno = ArquivoContaCorrenteUtil.obterMensagemSucesso("deposito.sucesso");
		
		System.out.println(mensagemRetorno);
		
		return mensagemRetorno;
	}


}
