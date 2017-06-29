package com.telegrambotbank.file.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * Classe responsável por encapsular operações referentes a manipulação de
 * arquivos
 * 
 * @author user
 *
 */
public class ArquivoContaCorrenteUtil {
	
	private static final String SUFIXO_DEPENDENTES = "DEPENDENTES";
	private static final String SUFIXO_LANCAMENTOS = "LANCAMENTOS";
	private static final String SUFIXO_EMPRESTIMO = "EMPRESTIMO";

	/**
	 * Recebe a agência e conta e retorna a localização do arquivo
	 */
	private final static String EXTENSAO = ".txt";
	private final static String CAMINHO_INICIO = "C:\\TelegramBOTApp\\";
	
	public static Path obterCaminhoArquivoEmprestimo(String nuContaBancaria, String agenciaBancaria) {

		String nomeArquivo = agenciaBancaria + "_" + nuContaBancaria + "_" + SUFIXO_EMPRESTIMO;

		Path caminho = Paths.get(CAMINHO_INICIO + nomeArquivo + EXTENSAO);

		return caminho;

	}
	
	public static Path obterCaminhoArquivo(String nuContaBancaria, String agenciaBancaria) {

		String nomeArquivo = agenciaBancaria + "_" + nuContaBancaria;

		Path caminho = Paths.get(CAMINHO_INICIO + nomeArquivo + EXTENSAO);

		return caminho;

	}
	
	/**
	 * Obtem caminho do arquivo de depententes
	 * @param nuContaBancaria
	 * @param agenciaBancaria
	 * @return
	 */
	public static Path obterCaminhoArquivoDependentes(String nuContaBancaria, String agenciaBancaria) {

		String nomeArquivo = agenciaBancaria + "_" + nuContaBancaria + "_" + SUFIXO_DEPENDENTES;

		Path caminho = Paths.get(CAMINHO_INICIO + nomeArquivo + EXTENSAO);

		return caminho;

	}

	/**
	 * Obtem a mensagem de sucesso a partir de um arquivo de propiedades
	 * 
	 * @param string
	 * @return
	 */
	public static String obterMensagemSucesso(String string) {
		ResourceBundle propriedades = ResourceBundle.getBundle("com.telegrambotbank.services.impl.conf.Messages");
		return propriedades.getString(string);
	}
	
	/**
	 * Obtém o camnho do arquivo de lancamentos
	 * @param contaBancaria
	 * @param agenciaBancaria
	 * @return
	 */
	public static Path obterCaminhoArquivoLancamentos(String contaBancaria, String agenciaBancaria) {
		
		String nomeArquivo = agenciaBancaria + "_" + contaBancaria + "_" + SUFIXO_LANCAMENTOS;

		Path caminho = Paths.get(CAMINHO_INICIO + nomeArquivo + EXTENSAO);

		return caminho;
	}

	public static String getSufixoDependentes() {
		return SUFIXO_DEPENDENTES;
	}

	public static String getSufixoLancamentos() {
		return SUFIXO_LANCAMENTOS;
	}
	

}
