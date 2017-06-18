package com.telegrambotbank.services.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;

import com.telegrambotbank.datatype.OperacaoVO;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.file.helper.ArquivoContaCorrenteReaderHelper;
import com.telegrambotbank.file.helper.ArquivoContaCorrenteWriterHelper;
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
	 */
	@Override
	public String creditarContaBancaria(OperacaoVO dadosOperacao) throws SaldoInsuficienteException, IOException {
		
		Path destino = ArquivoContaCorrenteUtil.obterCaminhoArquivo(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria());
		
		ArquivoContaCorrenteReaderHelper arquivoContaCorrenteReader = new ArquivoContaCorrenteReaderHelper(destino);
		arquivoContaCorrenteReader.visitFile(destino, null);
		
		// Obtem saldo do arquivo a partir do arquivo da conta corrente
		BigDecimal saldo = new BigDecimal(arquivoContaCorrenteReader.getDadosArquivo().substring(12, 21).trim());
		
		// Calcula novo saldo
		BigDecimal saldoAtual = saldo.add(dadosOperacao.getValor());
		
		// Atualiza saldo da conta corrente
		ArquivoContaCorrenteWriterHelper arquivoContaCorrenteWriter = new ArquivoContaCorrenteWriterHelper();
		arquivoContaCorrenteWriter.alteraLinha(saldo.toString(), saldoAtual.toString(), destino.toString());
		
		// Obtem mensagem de sucesso
		String mensagemRetorno = ArquivoContaCorrenteUtil.obterMensagemSucesso("deposito.creditar.sucesso");
		
		System.out.println(mensagemRetorno);
		
		return mensagemRetorno;
	}
	/**
	 * Efetua o débito de conta bancária e atualiza seu saldo
	 */
	@Override
	public String debitarContaBancaria(OperacaoVO dadosOperacao) throws SaldoInsuficienteException, IOException {
		
		Path destino = ArquivoContaCorrenteUtil.obterCaminhoArquivo(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria());
		
		ArquivoContaCorrenteReaderHelper arquivoContaCorrenteReader = new ArquivoContaCorrenteReaderHelper(destino);
		arquivoContaCorrenteReader.visitFile(destino, null);
		
		//Obtem saldo do arquivo a partir do arquivo da conta corrente
		BigDecimal saldo = new BigDecimal(arquivoContaCorrenteReader.getDadosArquivo().substring(12, 21).trim());
		
		// Calcula novo saldo
		BigDecimal saldoAtual = saldo.subtract(dadosOperacao.getValor());
		
		//Atualiza saldo da conta corrente
		ArquivoContaCorrenteWriterHelper arquivoContaCorrenteWriter = new ArquivoContaCorrenteWriterHelper();
		arquivoContaCorrenteWriter.alteraLinha(saldo.toString(), saldoAtual.toString(), destino.toString());
		
		// Obtem mensagem de sucesso
		String mensagemRetorno = ArquivoContaCorrenteUtil.obterMensagemSucesso("deposito.debitar.sucesso");
		
		System.out.println(mensagemRetorno);
		
		return mensagemRetorno;
	}


}
