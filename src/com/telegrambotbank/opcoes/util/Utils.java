package com.telegrambotbank.opcoes.util;

<<<<<<< HEAD
import com.telegrambotbank.exception.CampoInvalidoException;

public class Utils {

	public static void validarTamanho(String resp, int positions) throws CampoInvalidoException {
		if (resp.length() > positions) {
			throw new CampoInvalidoException();
		}

	}

=======
import java.time.LocalDate;
import java.util.Random;

public class Utils {

	public static LocalDate converteData(String data) {
		
		int dia = Integer.parseInt(data.substring(0,1));
		int mes = Integer.parseInt(data.substring(3,5));
		int ano = Integer.parseInt(data.substring(7,10));
		LocalDate dataConvertida = LocalDate.of(ano, mes, dia);
		
		return dataConvertida;
	}
	
	public static String agencia(){
		return "6252"; 
	}
	
	public static String gerarContaCorrente() {
		
		Random r = new Random();
		Integer n = r.nextInt(999999) + 100000;
		return n.toString();
	}
>>>>>>> branch 'master' of https://github.com/lramosouza/JavaUMLTrabalhoFinalFIAP
}
