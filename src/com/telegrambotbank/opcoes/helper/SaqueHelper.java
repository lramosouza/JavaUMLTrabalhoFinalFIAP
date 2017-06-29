package com.telegrambotbank.opcoes.helper;

import java.io.IOException;

import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.enumeration.TarifaEnum;
import com.telegrambotbank.enumeration.TipoLancamentoEnum;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;
import com.telegrambotbank.services.ContaCorrenteServiceImpl;
import com.telegrambotbank.services.LancamentoServicesImpl;

public class SaqueHelper {

	public static String sacarDinheiro(LancamentoVO dadosOperacao) throws ArquivoInvalidoException, SaldoInsuficienteException, IOException, GravarArquivoDependenteException {
		
		ContaCorrenteServiceImpl contaCorrenteServices = new ContaCorrenteServiceImpl();
		LancamentoServicesImpl lancamentoServices = new LancamentoServicesImpl();
		contaCorrenteServices.debitarContaBancaria(dadosOperacao);
		
	// grava o lançamento  de tarifa
		dadosOperacao.setValorTarifa(TarifaEnum.SAQUE.getTarifa());
		dadosOperacao.setTipoLancamento(TipoLancamentoEnum.TARIFA.getTipoLancamento());
        lancamentoServices.gravarLancamentoArquivo(dadosOperacao);
		
		return ArquivoContaCorrenteUtil.obterMensagemSucesso("sacar.sucesso");
	}

}
