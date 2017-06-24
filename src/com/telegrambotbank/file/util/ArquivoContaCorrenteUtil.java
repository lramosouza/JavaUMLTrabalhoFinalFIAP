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

	/**
	 * Recebe a agência e conta e retorna a localização do arquivo
	 */
	private final static String EXTENSAO = ".txt";
	private final static String CAMINHO_INICIO = "C:\\TelegramBOTApp\\";

	public static Path obterCaminhoArquivo(String nuContaBancaria, String agenciaBancaria) {

		String nomeArquivo = agenciaBancaria + "_" + nuContaBancaria;

		Path caminho = Paths.get(CAMINHO_INICIO + nomeArquivo + EXTENSAO);

		return caminho;

	}
	
	public static Path obterCaminhoArquivoDependentes(String nuContaBancaria, String agenciaBancaria, String CPFDependente) {

		String nomeArquivo = agenciaBancaria + "_" + nuContaBancaria + "_" + CPFDependente;

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

}
