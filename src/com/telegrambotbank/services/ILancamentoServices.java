package com.telegrambotbank.services;

import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.exception.GravarArquivoDependenteException;

/**
 * Contrato de comportamento das opera��es referentes a lan�amentos
 * 
 * @author BRQVotorantim
 *
 */
public interface ILancamentoServices {
	
	/**
	 * M�todo que efetua a grava��o das informa��es do lan�amento em um arquivo
	 * @param dadosOperacao
	 * @return
	 */
	public String gravarLancamentoArquivo(LancamentoVO dadosOperacao) throws GravarArquivoDependenteException;

}
