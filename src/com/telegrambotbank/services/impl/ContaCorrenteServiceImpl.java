package com.telegrambotbank.services.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;

import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.file.helper.ArquivoReaderHelper;
import com.telegrambotbank.file.helper.ArquivoWriterHelper;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;
import com.telegrambotbank.services.IContaCorrenteService;

/**
 * Implementação dos serviços da conta corrente
 * @author user
 *
 */
public class ContaCorrenteServiceImpl implements IContaCorrenteService{
	/**
	 * Efetua crédito em uma conta bancária e atualiza seu saldo
	 * @throws IOException 
	 * @throws ArquivoInvalidoException 
	 * @throws GravarArquivoDependenteException 
	 */
	@Override
	public String creditarContaBancaria(LancamentoVO dadosOperacao) throws SaldoInsuficienteException, IOException, ArquivoInvalidoException, GravarArquivoDependenteException {
		
		Path destino = ArquivoContaCorrenteUtil.obterCaminhoArquivo(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria());
		
		ArquivoReaderHelper arquivoContaCorrenteReader = new ArquivoReaderHelper(destino);
				
		arquivoContaCorrenteReader.visitFile(destino, null);
		
		// Obtem saldo do arquivo a partir do arquivo da conta corrente
		BigDecimal saldo = new BigDecimal(arquivoContaCorrenteReader.getDadosArquivo().substring(13, 23).trim());
		
		// Calcula novo saldo
		BigDecimal saldoAtual = saldo.add(dadosOperacao.getValorLancamento());
		
		// Atualiza saldo da conta corrente
		ArquivoWriterHelper arquivoContaCorrenteWriter = new ArquivoWriterHelper();
		arquivoContaCorrenteWriter.alteraLinha(saldo.toString(), saldoAtual.toString(), destino.toString());
		
		// Obtem mensagem de sucesso
		String mensagemRetorno = ArquivoContaCorrenteUtil.obterMensagemSucesso("deposito.creditar.sucesso");
		
		System.out.println(mensagemRetorno);
		
		// grava o lançamento 
        LancamentoServicesImpl lancamentoServices = new LancamentoServicesImpl();
        
        lancamentoServices.gravarLancamentoArquivo(dadosOperacao);
            
		return mensagemRetorno;
	}
	/**
	 * Efetua o débito de conta bancária e atualiza seu saldo
	 * @throws ArquivoInvalidoException 
	 * @throws GravarArquivoDependenteException 
	 */
	@Override
	public String debitarContaBancaria(LancamentoVO dadosOperacao) throws SaldoInsuficienteException, IOException, ArquivoInvalidoException, GravarArquivoDependenteException {
		
		Path destino = ArquivoContaCorrenteUtil.obterCaminhoArquivo(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria());
		
		ArquivoReaderHelper arquivoContaCorrenteReader = new ArquivoReaderHelper(destino);
				
		arquivoContaCorrenteReader.visitFile(destino, null);
		
		//Obtem saldo do arquivo a partir do arquivo da conta corrente
		BigDecimal saldo = new BigDecimal(arquivoContaCorrenteReader.getDadosArquivo().substring(13, 23).trim());
		
		if (saldo.compareTo(BigDecimal.ZERO) == 0 || saldo.compareTo(dadosOperacao.getValorLancamento()) < 0){
			throw new SaldoInsuficienteException();
		}
		// Calcula novo saldo
		BigDecimal saldoAtual = saldo.subtract(dadosOperacao.getValorLancamento());
		
		//Atualiza saldo da conta corrente
		ArquivoWriterHelper arquivoContaCorrenteWriter = new ArquivoWriterHelper();
		arquivoContaCorrenteWriter.alteraLinha(saldo.toString(), saldoAtual.toString(), destino.toString());
		
		// Obtem mensagem de sucesso
		String mensagemRetorno = ArquivoContaCorrenteUtil.obterMensagemSucesso("deposito.debitar.sucesso");
		
		System.out.println(mensagemRetorno);
		
		// grava o lançamento 
        LancamentoServicesImpl lancamentoServices = new LancamentoServicesImpl();
        
        lancamentoServices.gravarLancamentoArquivo(dadosOperacao);
            
		return mensagemRetorno;
	}


}
