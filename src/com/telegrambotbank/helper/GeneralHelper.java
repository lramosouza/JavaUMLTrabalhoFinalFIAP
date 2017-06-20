package com.telegrambotbank.helper;


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
			;

	public String getMsgBoasVindas() {
		
		return msgBoasVindas;
	}
	
}
