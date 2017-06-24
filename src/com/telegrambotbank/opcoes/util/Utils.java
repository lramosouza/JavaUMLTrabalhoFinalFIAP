package com.telegrambotbank.opcoes.util;

import com.telegrambotbank.exception.CampoInvalidoException;

public class Utils {

	public static void validarTamanho(String resp, int positions) throws CampoInvalidoException {
		if (resp.length() > positions) {
			throw new CampoInvalidoException();
		}

	}

}
