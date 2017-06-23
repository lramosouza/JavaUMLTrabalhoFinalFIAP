package com.telegrambotbank.helper;

import java.lang.reflect.Field;

import com.telegrambotbank.datatype.ClienteVO;
import com.telegrambotbank.datatype.Posicao;

/**
 * Classe responsavel por receber o cliente e listar todos os comandos
 * @author Diogo Brito
 *
 */
public class GeneralHelper {

	private final String msgBoasVindas = "Seja bem vindo(a) ao Telegram Bot Bank. "
			+ "Segue abaixo alguns comandos importantes: \n /start - Mensagem de boas vindas e listar comandos \n "
			+"/depositar - Realizar deposito em sua conta \n"
			+ "/sacar - Realizar um saque virtual \n"
			+ "/emprestimo - Solicitar emprestimo \n"
			+ "/criarConta - Realizar cadastro de uma nova conta \n"
			+ "/help - Para mais informações  \n";
	
	private final String msgHelp = "TelegranBank tem total compromisso com nossos clientes,"
			+ "esperamos que você esteja contente com nosso atendimento! \n"
			+ "< br />"
			+ "para maiores informações, dúvidas ou reclamações, ligue em nossa "
			+ "central de atendimento: \n"
			+ " 0800 4002 8922. \n"
			+ "< br />"
			+ "Para acessar nossas funcionalidades, acesse o comando /start";
									
									

	public String getMsgBoasVindas() {
		
		return msgBoasVindas;
	}
	
	public String getMsgHelp() {
		
		return msgHelp;
	}
	
	public Object getObjectByLine(String line, Object obj) throws IllegalArgumentException, IllegalAccessException{
		try {
		for (Field f : obj.getClass().getDeclaredFields()) {
			   Posicao posicao = f.getDeclaredAnnotation(Posicao.class);
			   if (posicao != null){
				   f.setAccessible(true);
				   f.set(obj, line.substring(posicao.posicaoInicial(),posicao.posicaoFinal()).trim());
			   }
			}
		} catch (Exception e) {
	        throw new IllegalStateException(e);
	    }
		return obj;
	}
	
	public String getLineByObject(Object obj)throws IllegalArgumentException, IllegalAccessException{
		String retorno = "";
		int aux = 0;
		try {
			for (Field f : obj.getClass().getDeclaredFields()) {
				aux = 0;
				   Posicao posicao = f.getDeclaredAnnotation(Posicao.class);
				   if (posicao != null){
					  f.setAccessible(true);
					  String blankSpaces = "";
					  String fieldValue =  f.get(obj).toString();
					  
					  aux = (posicao.posicaoFinal() - posicao.posicaoInicial()) - fieldValue.length();
					  
					  for (int i = 0; i < aux; i++) {
						  blankSpaces = blankSpaces + " ";
					}
					  fieldValue = fieldValue + blankSpaces;
					  retorno = retorno + fieldValue;
					  
				   }
				}
			} catch (Exception e) {
		        throw new IllegalStateException(e);
		    }
		
		
		return retorno;
		
	}
	
}
