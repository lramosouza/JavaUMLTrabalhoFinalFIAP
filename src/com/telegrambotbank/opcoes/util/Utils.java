package com.telegrambotbank.opcoes.util;

import java.time.LocalDate;
import java.util.Random;

import com.telegrambotbank.enumeration.StringUtilsEnum;
import com.telegrambotbank.exception.CampoInvalidoException;

/**
 * Classe Utils que cont�m m�todos de apoio para o funcionamento da aplica��o
 * 
 * @author BRQVotorantim
 *
 */
public class Utils {

	/**
	 * M�todo respons�vel por receber o tamanho da mensagem, o n�mero m�nimo e
	 * m�ximo de posi��es poss�veis, caso tamanho seja menor ou maior,
	 * respectivamente, lan�a exce��o
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
	 * M�todo respons�vel por converter data String para um LocalDate
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
	 * M�todo respons�vel por gerar um n�mero de conta corrente aleat�rio para o
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
	 * M�todo que retorna um String buffer em branco de acordo com o parametro
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
