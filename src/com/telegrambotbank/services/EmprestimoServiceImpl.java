package com.telegrambotbank.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.text.SimpleDateFormat;

import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.datatype.EmprestimoVO;
import com.telegrambotbank.enumeration.PosicoesCamposEnum;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.file.helper.ArquivoReaderHelper;
import com.telegrambotbank.file.helper.ArquivoWriterHelper;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;
import com.telegrambotbank.opcoes.util.Utils;

public class EmprestimoServiceImpl implements EmprestimoService {
	
	@Override
	public String efetivarEmprestimo(EmprestimoVO emprestimoVO, ContaBancariaVO contaBancariaVO) throws IOException, ArquivoInvalidoException, SaldoInsuficienteException, GravarArquivoDependenteException {
		Path destino = ArquivoContaCorrenteUtil.obterCaminhoArquivo(contaBancariaVO.getNuContaCorrete(), contaBancariaVO.getAgenciaBancaria());
				
		StringBuffer layoutEmprestimo = new StringBuffer();
		
		layoutEmprestimo.append(emprestimoVO.getVlContratado().toString()).append(preencheBlanck(PosicoesCamposEnum.VALOR_CONTRATADO.getPosicaoMax(), emprestimoVO.getVlContratado().toString().length()));
		layoutEmprestimo.append(emprestimoVO.getVlCalculado().toString()).append(preencheBlanck(PosicoesCamposEnum.VALOR_CALCULADO.getPosicaoMax(), emprestimoVO.getVlCalculado().toString().length()));
		layoutEmprestimo.append(emprestimoVO.getVlParcela().toString()).append(preencheBlanck(PosicoesCamposEnum.VALOR_PARCELA.getPosicaoMax(), emprestimoVO.getVlParcela().toString().length()));
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		layoutEmprestimo.append(formataData.format(emprestimoVO.getDtContracao()));
		layoutEmprestimo.append(emprestimoVO.getPrazo().toString());
		
		ArquivoWriterHelper arquivoWriterHelper = new ArquivoWriterHelper();
		arquivoWriterHelper.inserirLinha(destino, layoutEmprestimo);
		
		String mensagemRetorno = ArquivoContaCorrenteUtil.obterMensagemSucesso("emprestimo.efetivar.sucesso");
		
		System.out.println(mensagemRetorno);
		
		return mensagemRetorno;
	}
	
	public StringBuffer preencheBlanck(Integer max, Integer min){
		int num = max - min - 1;
		StringBuffer blanks = Utils.completarBlanks(num);
		return blanks;
	}
	
}
