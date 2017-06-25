package com.telegrambotbank.opcoes.util;

import java.time.LocalDate;
import java.util.Random;

import com.telegrambotbank.enumeration.StringUtilsEnum;
import com.telegrambotbank.exception.CampoInvalidoException;

/**
 * Classe Utils que contém métodos de apoio para o funcionamento da aplicação
 * 
 * @author BRQVotorantim
 *
 */
public class Utils {

	/**
	 * Método responsável por receber o tamanho da mensagem, o número mínimo e
	 * máximo de posições possíveis, caso tamanho seja menor ou maior,
	 * respectivamente, lança exceção
	 * 
	 * @param resp
	 * @throws CampoInvalidoException
	 * @param positionsMin
	 * @param positionsMax
	 *
	 */
	public static void validarTamanhoMensagem(String resp, int positionsMin, int positionsMax)
			throws CampoInvalidoException {
		if (resp.length() > positionsMax || resp.length() < positionsMin) {
			throw new CampoInvalidoException();
		}
	}

	/**
	 * Método responsável por converter data String para um LocalDate
	 * 
	 * @param data
	 * @return
	 */
	public static LocalDate converteData(String data) {

		int dia = Integer.parseInt(data.substring(0, 1));
		int mes = Integer.parseInt(data.substring(3, 5));
		int ano = Integer.parseInt(data.substring(7, 10));
		LocalDate dataConvertida = LocalDate.of(ano, mes, dia);

		return dataConvertida;
	}

	public static String agencia() {
		return "6252";
	}

	/**
	 * Método responsável por gerar um número de conta corrente aleatório para o
	 * cliente
	 * 
	 * @return
	 */
	public static String gerarContaCorrente() {

		Random r = new Random();
		Integer n = r.nextInt(999999) + 100000;
		return n.toString();
	}

	/**
	 * Método que retorna um String buffer em branco de acordo com o parametro
	 * nuPosicoesDesejadas
	 * 
	 * @param tamanhoNome
	 * @return
	 */
	public static StringBuffer completarBlanks(int nuPosicoesDesejadas) {

		StringBuffer blanksNome = new StringBuffer();

		for (int i = 0; i < nuPosicoesDesejadas; i++) {
			blanksNome.append(StringUtilsEnum.BLANK.getBlank());
		}

		return blanksNome;
	}

}
