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
 * Implementação do serviço de depósito
 * 
 * @author user
 *
 */
public class DepositoServiceImpl implements IDepositoService {

	ContaCorrenteServiceImpl contaCorrenteServices = new ContaCorrenteServiceImpl();
	
	/**
	 * Efetua o depósito na conta
	 * @throws ArquivoInvalidoException 
	 */
	@Override
	public String depositar(DepositoVO dadosDeposito)
			throws SaldoInsuficienteException, ContaOuAgenciaInvalidaException, IOException, ArquivoInvalidoException {
	
		LancamentoVO dadosOperacaoDebito = DepositoBancarioHelper.montarDadosOperacaoDebito(dadosDeposito);
		LancamentoVO dadosOperacaoCredito = DepositoBancarioHelper.montarDadosOperacaoCredito(dadosDeposito);
		
		// Antes de debitar ou creditar contas, verifica se o arquivo referente
		// a elas é válido, caso contrário, a conta ou agência não existem
		DepositoBancarioHelper.validarArquivoAgenciaEContaDeposito(dadosOperacaoDebito, dadosOperacaoCredito);
		
		// Usa o serviço de conta corrente para efetuar débito da conta depositante
		try {
			contaCorrenteServices.debitarContaBancaria(dadosOperacaoDebito);
		} catch (GravarArquivoDependenteException e) {
			throw new ArquivoInvalidoException();
		}
		
		
		// Usa o serviço do conta corrente para efetuar crédito na conta de destino do depósito
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
