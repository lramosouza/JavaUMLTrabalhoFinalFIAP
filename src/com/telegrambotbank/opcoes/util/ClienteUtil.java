package com.telegrambotbank.opcoes.util;

import java.time.LocalDate;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

public class ClienteUtil {

	
	public static String solicitarNomeCliente(TelegramBot bot, Update update) {
		
		Boolean fimInformarNome = false;
		
		GetUpdatesResponse updatesResponse;
		int m;
		//Declaração de variáveis da conta
		String nomeCliente = "";
		
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Qual seu nome?"));
		m = update.updateId() + 1;	
		while (fimInformarNome == false) {
			bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));
			
			// lista de mensagens
			List<Update> updates3 = updatesResponse.updates();
			
			// análise de cada ação da mensagem
			for (Update update1 : updates3) {
				String resp = update1.message().text().trim();
				if (resp != null) {
					nomeCliente = resp;
					fimInformarNome = true;
				}
			}
		}
		return nomeCliente;		
	}
	
	public static String solicitarCpfCliente(TelegramBot bot, Update update) {
		
		Boolean fimInformarCpf = false;;
		
		GetUpdatesResponse updatesResponse;
		int m;
		//Declaração de variáveis da conta
		String cpfCliente = "";
		
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Me diga agora qual é o seu CPF?"));
		m = update.updateId() + 1;	
		while (fimInformarCpf == false) {
			bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+1));
			
			// lista de mensagens
			List<Update> updates4 = updatesResponse.updates();
			
			// análise de cada ação da mensagem
			for (Update update2 : updates4) {
				String resp = update2.message().text().trim();
				if (resp != null) {
					cpfCliente = resp;
					fimInformarCpf = true;
				}
			}
		}
		return cpfCliente;		
	}
	
	public static String solicitarDtNascCliente(TelegramBot bot, Update update) {
		
		Boolean fimInformarDtNasc = false;;
		
		GetUpdatesResponse updatesResponse;
		int m;
		//Declaração de variáveis da conta
		LocalDate dtNascCliente = LocalDate.now();
		
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Quando você nasceu? (dd/mm/aaaa)"));
		m = update.updateId() + 1;	
		while (fimInformarDtNasc == false) {
			bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+2));
			
			// lista de mensagens
			List<Update> updates5 = updatesResponse.updates();
			
			// análise de cada ação da mensagem
			for (Update update3 : updates5) {
				String resp = update3.message().text().trim();
				if (resp != null) {
					dtNascCliente = Utils.converteData(resp);
					fimInformarDtNasc = true;
				}
			}
		}
		return dtNascCliente.toString();		
	}
	
	public static String solicitarEmailCliente(TelegramBot bot, Update update) {
		
		Boolean fimInformarEmail = false;;
		
		GetUpdatesResponse updatesResponse;
		int m;
		//Declaração de variáveis da conta
		String emailCliente = "";
		
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Agora só falta seu e-mail"));
		m = update.updateId() + 1;	
		while (fimInformarEmail == false) {
			bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+3));
			
			// lista de mensagens
			List<Update> updates6 = updatesResponse.updates();
			
			// análise de cada ação da mensagem
			for (Update update4 : updates6) {
				String resp = update4.message().text().trim();
				if (resp != null) {
					emailCliente = resp;
					fimInformarEmail = true;
				}
			}
		}
		return emailCliente;		
	}
	
}
