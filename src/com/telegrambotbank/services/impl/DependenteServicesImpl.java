package com.telegrambotbank.services.impl;

import java.io.IOException;
import java.nio.file.Path;

import com.telegrambotbank.datatype.DependenteVO;
import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.enumeration.PosicoesCamposEnum;
import com.telegrambotbank.enumeration.StringUtilsEnum;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.file.helper.ArquivoContaCorrenteWriterHelper;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;
import com.telegrambotbank.opcoes.util.Utils;

public class DependenteServicesImpl {

	public String cadastrarDependente(DependenteVO dependente, LancamentoVO dadosOperacao) throws IOException, GravarArquivoDependenteException {
		Path destino = ArquivoContaCorrenteUtil.obterCaminhoArquivoDependentes(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria());
		
		
		// Salva dependente no arquivo
		ArquivoContaCorrenteWriterHelper arquivoContaCorrenteWriter = new ArquivoContaCorrenteWriterHelper();

		int nuPossicoesPreencherBlank = PosicoesCamposEnum.NOME_DEPENDENTE.getPosicaoMax() - dependente.getNomeDependente().length() - 1;

		StringBuffer blanksNome = Utils.completarBlanks(nuPossicoesPreencherBlank);

		StringBuffer layoutDependentes = new StringBuffer();

		layoutDependentes.append(dependente.getNomeDependente()).append(blanksNome);

		layoutDependentes.append(StringUtilsEnum.BLANK.getBlank());

		layoutDependentes.append(ArquivoContaCorrenteUtil.getSufixoDependentes());

		arquivoContaCorrenteWriter.gravarNovoArquivo(dependente, destino.toString(), layoutDependentes);
		
		String mensagemRetorno = ArquivoContaCorrenteUtil.obterMensagemSucesso("incluir.dependente.sucesso");
		System.out.println(mensagemRetorno);

		return mensagemRetorno;

	}

}
