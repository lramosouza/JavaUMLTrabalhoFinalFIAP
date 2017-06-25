package com.telegrambotbank.services.impl;

import java.nio.file.Path;

import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.enumeration.PosicoesCamposEnum;
import com.telegrambotbank.enumeration.StringUtilsEnum;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.file.helper.ArquivoContaCorrenteWriterHelper;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;
import com.telegrambotbank.opcoes.util.Utils;
import com.telegrambotbank.services.ILancamentoServices;

/**
 * Implementação dos serviços do Lancamento
 * @author BRQVotorantim
 *
 */
public class LancamentoServicesImpl implements ILancamentoServices{

	@Override
	public String gravarLancamentoArquivo(LancamentoVO lancamento) throws GravarArquivoDependenteException {
		
		ArquivoContaCorrenteWriterHelper arquivoContaCorrenteWriter = new ArquivoContaCorrenteWriterHelper();
		
		Path caminho = ArquivoContaCorrenteUtil.obterCaminhoArquivoLancamentos(lancamento.getContaBancaria(), lancamento.getAgenciaBancaria());
		
		int nuPossicoesPreencherBlankValor = PosicoesCamposEnum.VALOR_LANCAMENTO.getPosicaoMax() - lancamento.getValorLancamento().toString().length() - 1;

		StringBuffer blanksValor = Utils.completarBlanks(nuPossicoesPreencherBlankValor);
		
		//Montar layout linha lancamento
		StringBuffer layoutLancamentos = new StringBuffer();

		layoutLancamentos.append(lancamento.getTipoLancamento());
		layoutLancamentos.append(StringUtilsEnum.BLANK.getBlank());
		layoutLancamentos.append(lancamento.getValorLancamento()).append(blanksValor);
		layoutLancamentos.append(StringUtilsEnum.BLANK.getBlank());
		layoutLancamentos.append(lancamento.getDataLancamento());
		
		
		try{
			arquivoContaCorrenteWriter.inserirLinha(lancamento, caminho, layoutLancamentos);
		}catch(Exception e){
			throw new GravarArquivoDependenteException();
		}
		
		String mensagemRetorno = ArquivoContaCorrenteUtil.obterMensagemSucesso("lancamento.inserir.sucesso");
		System.out.println(mensagemRetorno);
		
		return mensagemRetorno;
		
	}

}
