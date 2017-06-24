package com.telegrambotbank.services.impl;

import java.io.IOException;
import java.nio.file.Path;

import com.telegrambotbank.datatype.DependenteVO;
import com.telegrambotbank.datatype.OperacaoVO;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.file.helper.ArquivoContaCorrenteWriterHelper;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;

public class DependenteServicesImpl {

	public String cadastrarDependente(DependenteVO dependente, OperacaoVO dadosOperacao) throws IOException, GravarArquivoDependenteException {
		Path destino = ArquivoContaCorrenteUtil.obterCaminhoArquivoDependentes(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria(), dependente.getCpfDependente());
		
		
		// Salva dependente no arquivo
		ArquivoContaCorrenteWriterHelper arquivoContaCorrenteWriter = new ArquivoContaCorrenteWriterHelper();
		arquivoContaCorrenteWriter.incluirNovoArquivoDependente(dependente, destino.toString());
		
		String mensagemRetorno = ArquivoContaCorrenteUtil.obterMensagemSucesso("incluir.dependente.sucesso");
		System.out.println(mensagemRetorno);

		return mensagemRetorno;

	}

}
