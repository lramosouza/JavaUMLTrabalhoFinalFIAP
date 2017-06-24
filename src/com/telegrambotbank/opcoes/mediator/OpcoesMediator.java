package com.telegrambotbank.opcoes.mediator;

import java.io.IOException;
import java.math.BigDecimal;

import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.datatype.DependenteVO;
import com.telegrambotbank.datatype.DepositoVO;
import com.telegrambotbank.datatype.OperacaoVO;
import com.telegrambotbank.exception.ContaInexistenteException;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.opcoes.helper.DepositoBancarioHelper;
import com.telegrambotbank.services.impl.DependenteServicesImpl;
import com.telegrambotbank.services.impl.DepositoServiceImpl;

/**
 * Mediator (Controller) responsável por direcionar a aplicação para o serviço
 * correspondente à opção desejada
 * 
 * @author user
 *
 */
public class OpcoesMediator {

	DepositoServiceImpl depositoServices = new DepositoServiceImpl();
	DependenteServicesImpl dependenteServices = new DependenteServicesImpl();
	
	public String depositar(ContaBancariaVO contaCorrenteDepositante, ContaBancariaVO contaCorrenteDestino, BigDecimal valorDeposito)
			throws SaldoInsuficienteException, ContaInexistenteException, IOException {
		
		DepositoVO dadosDeposito =  DepositoBancarioHelper.montarDadosDepositoBancario(contaCorrenteDepositante, contaCorrenteDestino, valorDeposito);
		
		return depositoServices.depositar(DepositoBancarioHelper.validarValorDeposito(dadosDeposito));
	}

	public String cadastrarDependente(DependenteVO dependente, OperacaoVO dadosOperacao) throws IOException, GravarArquivoDependenteException {
		return dependenteServices.cadastrarDependente(dependente, dadosOperacao);
	}

}
