package com.telegrambotbank.services;

import java.io.IOException;
import java.nio.file.Path;

import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.datatype.EmprestimoVO;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;

public class EmprestimoServiceImpl implements EmprestimoService {

	@Override
	public String efetivarEmprestimo(EmprestimoVO emprestimoVO, ContaBancariaVO contaBancariaVO) throws IOException, ArquivoInvalidoException {
		Path destino = ArquivoContaCorrenteUtil.obterCaminhoArquivo(contaBancariaVO.getNuContaCorrete(), contaBancariaVO.getAgenciaBancaria());
		return null;
	}

}
