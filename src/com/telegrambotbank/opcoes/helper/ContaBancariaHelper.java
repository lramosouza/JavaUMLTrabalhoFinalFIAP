package com.telegrambotbank.opcoes.helper;

import java.io.IOException;
import java.nio.file.Path;

import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.ContaOuAgenciaInvalidaException;
import com.telegrambotbank.file.helper.ArquivoReaderHelper;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;

public class ContaBancariaHelper {

	public static String buscarDadosConta(LancamentoVO dadosOperacao) throws ArquivoInvalidoException, ContaOuAgenciaInvalidaException {
		String dadosContaCorrente = null;
		String dadosDependente = null;
		
		Path arquivoContaCorrente = ArquivoContaCorrenteUtil.obterCaminhoArquivo(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria());
		
		ArquivoReaderHelper arquivoContaCorrenteReaderContaCorrente = new ArquivoReaderHelper(arquivoContaCorrente);
		
		if (!arquivoContaCorrenteReaderContaCorrente.isArquivoExistente(arquivoContaCorrente)) {
			throw new ContaOuAgenciaInvalidaException();
		}
		
		Path arquivoDependentes = ArquivoContaCorrenteUtil.obterCaminhoArquivo(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria());
		
		ArquivoReaderHelper arquivoContaCorrenteReaderDependentes = new ArquivoReaderHelper(arquivoDependentes);
		
		if (!arquivoContaCorrenteReaderDependentes.isArquivoExistente(arquivoDependentes)) {
			throw new ContaOuAgenciaInvalidaException();
		}
		
		/**
		 * Le o arquivo da conta bancária
		 */
		try {
			arquivoContaCorrenteReaderContaCorrente.visitFile(arquivoContaCorrente, null);
			 dadosContaCorrente = arquivoContaCorrenteReaderContaCorrente.getDadosArquivo();
		} catch (IOException e) {
			throw new ArquivoInvalidoException();
		}
		
		/**
		 * Le o arquivo de dependentes
		 */
		try {
			arquivoContaCorrenteReaderDependentes.visitFile(arquivoDependentes, null);
			dadosContaCorrente = arquivoContaCorrenteReaderContaCorrente.getDadosArquivo();
		} catch (IOException e) {
			throw new ArquivoInvalidoException();
		}
		
		
		dadosContaCorrente.substring(1);

		return null;
	}

}
