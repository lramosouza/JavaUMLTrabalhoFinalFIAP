package com.telegrambotbank.opcoes.mediator;

import java.io.IOException;
import java.math.BigDecimal;

import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.datatype.DepositoVO;
import com.telegrambotbank.exception.ContaInexistenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.opcoes.util.DepositoBancarioUtil;
import com.telegrambotbank.services.impl.DepositoServiceImpl;

/**
 * Mediator (Controller) respons�vel por direcionar a aplica��o para o servi�o
 * correspondente � op��o desejada
 * 
 * @author user
 *
 */
public class OpcoesMediator {

	DepositoServiceImpl depositoServices = new DepositoServiceImpl();

	public String depositar(ContaBancariaVO contaCorrenteDepositante, ContaBancariaVO contaCorrenteDestino, BigDecimal valorDeposito)
			throws SaldoInsuficienteException, ContaInexistenteException, IOException {
		
		DepositoVO dadosDeposito =  DepositoBancarioUtil.montarDadosDepositoBancario(contaCorrenteDepositante, contaCorrenteDestino, valorDeposito);
		
		return depositoServices.depositar(DepositoBancarioUtil.validarValorDeposito(dadosDeposito));
	}

}
