package com.telegrambotbank.services;

import java.io.IOException;

import com.telegrambotbank.datatype.DependenteVO;
import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.exception.GravarArquivoDependenteException;

/**
 * Contrato de comportamento dos serviços de dependentes
 * @author BRQVotorantim
 *
 */
public interface DependenteServices {

	public String cadastrarDependente(DependenteVO dependente, LancamentoVO dadosOperacao) throws IOException, GravarArquivoDependenteException;

}
