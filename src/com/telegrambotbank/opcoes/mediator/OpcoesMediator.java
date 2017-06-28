package com.telegrambotbank.opcoes.mediator;

import java.io.IOException;
import java.math.BigDecimal;

import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.datatype.DependenteVO;
import com.telegrambotbank.datatype.DepositoVO;
import com.telegrambotbank.datatype.EmprestimoVO;
import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.ContaOuAgenciaInvalidaException;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.opcoes.helper.DepositoBancarioHelper;
import com.telegrambotbank.opcoes.helper.ExibirInformacoesContaHelper;
import com.telegrambotbank.services.EmprestimoService;
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
	
	EmprestimoService emprestimoService;
	
	public void setEmprestimoService(EmprestimoService emprestimoService) {
		this.emprestimoService = emprestimoService;
	}

	/**
	 * Método responsáel pelo depósito na conta
	 * @param contaCorrenteDepositante
	 * @param contaCorrenteDestino
	 * @param valorDeposito
	 * @return
	 * @throws SaldoInsuficienteException
	 * @throws ContaOuAgenciaInvalidaException
	 * @throws IOException
	 * @throws ArquivoInvalidoException
	 */
	public String depositar(ContaBancariaVO contaCorrenteDepositante, ContaBancariaVO contaCorrenteDestino, BigDecimal valorDeposito)
			throws SaldoInsuficienteException, ContaOuAgenciaInvalidaException, IOException, ArquivoInvalidoException {
		
		DepositoVO dadosDeposito =  DepositoBancarioHelper.montarDadosDepositoBancario(contaCorrenteDepositante, contaCorrenteDestino, valorDeposito);
		
		return depositoServices.depositar(DepositoBancarioHelper.validarValorDeposito(dadosDeposito));
	}
	
	/**
	 * Método responsável por inclusão de dependentes
	 * @param dependente
	 * @param dadosOperacao
	 * @return String
	 * @throws IOException
	 * @throws GravarArquivoDependenteException
	 */
	public String cadastrarDependente(DependenteVO dependente, LancamentoVO dadosOperacao) throws IOException, GravarArquivoDependenteException {
		return dependenteServices.cadastrarDependente(dependente, dadosOperacao);
	}
	
	/**
	 * Método responsável pela exibição de informações da conta corrente
	 * @param dadosOperacao
	 * @return String
	 * @throws ContaOuAgenciaInvalidaException 
	 * @throws ArquivoInvalidoException 
	 */
	public String exibirInformacoesConta(LancamentoVO dadosOperacao) throws ArquivoInvalidoException, ContaOuAgenciaInvalidaException {
		return ExibirInformacoesContaHelper.buscarDadosConta(dadosOperacao);
	}
	
	/**
	 * Método responsável pela a efetivação do Empréstimo
	 * @param vo
	 * @return String
	 * @throws IOException
	 * @throws ArquivoInvalidoException
	 */
	public String efetivarEmprestimo(EmprestimoVO emprestimoVO, ContaBancariaVO contaBancariaVO) throws IOException, ArquivoInvalidoException {
		return emprestimoService.efetivarEmprestimo(emprestimoVO, contaBancariaVO);
	}

}
