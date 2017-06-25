package com.telegrambotbank.opcoes.helper;

import java.nio.file.Path;

import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.ContaOuAgenciaInvalidaException;
import com.telegrambotbank.file.helper.ArquivoContaCorrenteReaderHelper;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;

public class ExibirInformacoesContaHelper {

	public static String buscarDadosConta(LancamentoVO dadosOperacao) throws ArquivoInvalidoException, ContaOuAgenciaInvalidaException {
		
		Path arquivoContaCorrente = ArquivoContaCorrenteUtil.obterCaminhoArquivo(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria());
		
		ArquivoContaCorrenteReaderHelper arquivoContaCorrenteReaderContaCorrente = new ArquivoContaCorrenteReaderHelper(arquivoContaCorrente);
		
		if (!arquivoContaCorrenteReaderContaCorrente.isArquivoExistente(arquivoContaCorrente)) {
			throw new ContaOuAgenciaInvalidaException();
		}
		
		Path arquivoDependentes = ArquivoContaCorrenteUtil.obterCaminhoArquivo(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria());
		
		ArquivoContaCorrenteReaderHelper arquivoContaCorrenteReaderDependentes = new ArquivoContaCorrenteReaderHelper(arquivoDependentes);
		
		if (!arquivoContaCorrenteReaderDependentes.isArquivoExistente(arquivoContaCorrente)) {
			throw new ContaOuAgenciaInvalidaException();
		}

		return null;
	}

}
