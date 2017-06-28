package com.telegrambotbank.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;

import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.datatype.EmprestimoVO;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.file.helper.ArquivoReaderHelper;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;

public class EmprestimoServiceImpl implements EmprestimoService {

	@Override
	public String efetivarEmprestimo(EmprestimoVO emprestimoVO, ContaBancariaVO contaBancariaVO) throws IOException, ArquivoInvalidoException, SaldoInsuficienteException {
		Path destino = ArquivoContaCorrenteUtil.obterCaminhoArquivo(contaBancariaVO.getNuContaCorrete(), contaBancariaVO.getAgenciaBancaria());
		ArquivoReaderHelper arquivoContaCorrenteReader = new ArquivoReaderHelper(destino);
		
		//Obtem saldo do arquivo a partir do arquivo da conta corrente
		BigDecimal saldo = new BigDecimal(arquivoContaCorrenteReader.getDadosArquivo().substring(13, 23).trim());
		
		if (saldo.compareTo(BigDecimal.ZERO) == 0 || saldo == null){ 
			throw new SaldoInsuficienteException();
		}
		
		return null;
	}

}
