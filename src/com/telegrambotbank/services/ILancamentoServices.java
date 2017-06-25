package com.telegrambotbank.services;

import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.exception.GravarArquivoDependenteException;

/**
 * Contrato de comportamento das operações referentes a lançamentos
 * 
 * @author BRQVotorantim
 *
 */
public interface ILancamentoServices {
	
	/**
	 * Método que efetua a gravação das informações do lançamento em um arquivo
	 * @param dadosOperacao
	 * @return
	 */
	public String gravarLancamentoArquivo(LancamentoVO dadosOperacao) throws GravarArquivoDependenteException;

}
