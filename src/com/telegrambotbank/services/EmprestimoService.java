package com.telegrambotbank.services;

import java.io.IOException;

import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.datatype.EmprestimoVO;
import com.telegrambotbank.exception.ArquivoInvalidoException;

/**
 * Serviços disponíveis para Empréstimo
 * @author ismaelsouza
 *
 */
public interface EmprestimoService {
	
	public String efetivarEmprestimo(EmprestimoVO emprestimoVO, ContaBancariaVO contaBancariaVO) throws IOException, ArquivoInvalidoException; 
}
