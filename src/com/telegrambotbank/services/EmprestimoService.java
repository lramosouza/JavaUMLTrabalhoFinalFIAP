package com.telegrambotbank.services;

import java.io.IOException;

import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.datatype.EmprestimoVO;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;

/**
 * Servi�os dispon�veis para Empr�stimo
 * @author ismaelsouza
 *
 */
public interface EmprestimoService {
	
	public String efetivarEmprestimo(EmprestimoVO emprestimoVO, ContaBancariaVO contaBancariaVO) throws IOException, ArquivoInvalidoException, SaldoInsuficienteException, GravarArquivoDependenteException; 
}
