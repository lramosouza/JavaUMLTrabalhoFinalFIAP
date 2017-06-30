package com.telegrambotbank.messages;

import java.lang.reflect.Field;


/**
 * Classe responsavel por receber o cliente e listar todos os comandos
 * @author Diogo Brito
 *
 */
public class GeneralMessages {

	private final String msgBoasVindas = "Seja bem vindo(a) ao Telegram Bot Bank. "
			+ "Segue abaixo alguns comandos importantes: \n /start - Mensagem de boas vindas e listar comandos \n "
			+"/depositar - Realizar deposito em sua conta \n"
			+ "/sacar - Realizar um saque virtual \n"
			+ "/emprestimo - Solicitar emprestimo \n"
			+ "/criarConta - Realizar cadastro de uma nova conta \n"
			+ "/help - Para mais informa��ees  \n"
			+ "/tarifas - Nossas taxas de servi�os";
	
	private final String msgHelp = "TelegranBank tem total compromisso com nossos clientes, "
			+ "esperamos que voc� esteja contente com nosso atendimento! \n\n"
			+ "para maiores informa��es, d�vidas ou reclama��ees, ligue em nossa "
			+ "central de atendimento: \n"
			+ " 0800 4002 8922. \n\n"
			+ "Para acessar nossas funcionalidades, acesse o comando /start";
	
	private final String tarifas = "Valores dos nossos serv�os: \n\n"
			+ "Saque: R$2,50\n"
			+ "Deposito: R$1,00\n"
			+ "Extrato: R$1,50\n"
			+ "Solicita��o de Emprestimo: R$15,00";									
									

	public String getMsgBoasVindas() {
		
		return msgBoasVindas;
	}
	
	public String getMsgHelp() {
		
		return msgHelp;
	}
	
	
	public String getTarifas() {
		return tarifas;
	}

	
}
