package com.telegrambotbank.opcoes.helper;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.telegrambotbank.datatype.DependenteVO;
import com.telegrambotbank.enumeration.PosicoesCamposEnum;
import com.telegrambotbank.enumeration.StringUtilsEnum;
import com.telegrambotbank.exception.CampoInvalidoException;
import com.telegrambotbank.opcoes.util.Utils;

/**
 * Classe Helper responsável por apoiar as operações de escolha de opção
 * 
 * @author BRQVotorantim
 * 
 */
public class DependenteHelper {

	//
	public static String solicitarNomeDependente(TelegramBot bot, Update update) throws CampoInvalidoException {
		
		GetUpdatesResponse updatesResponse;
		
		boolean fimInformarNomeDependente = false;
		
		int m;
		
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Favor Informe o nome do dependente"));
		
		m = update.updateId() + 1;
		String resp = StringUtilsEnum.BLANK.getBlank();
		
		while (fimInformarNomeDependente == false) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			// lista de mensagens
			List<Update> updates5 = updatesResponse.updates();

			// análise de cada ação da mensagem
			for (Update update1 : updates5) {
				resp = update1.message().text().trim();
				if (resp != null) {
					Utils.validarTamanhoMensagem(resp, PosicoesCamposEnum.NOME_DEPENDENTE.getPosicaoMin(), PosicoesCamposEnum.NOME_DEPENDENTE.getPosicaoMax());
					fimInformarNomeDependente = true;
				}
			}
		}
		return resp;
	}

	public static String solicitarCPFDependente(TelegramBot bot, Update update, DependenteVO dependente)
			throws CampoInvalidoException {
		
		GetUpdatesResponse updatesResponse;
		
		boolean fimInformarNomeDependente = false;
		
		int m;
		
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Favor Informe o CPF do dependente"));
		
		m = update.updateId() + 1;
		String resp = StringUtilsEnum.BLANK.getBlank();
		
		while (fimInformarNomeDependente == false) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m + 1));

			// lista de mensagens
			List<Update> updates5 = updatesResponse.updates();

			// análise de cada ação da mensagem
			for (Update update2 : updates5) {
				resp = update2.message().text().trim();
				if (resp != null) {
					Utils.validarTamanhoMensagem(resp, PosicoesCamposEnum.CPF_DEPENDENTE.getPosicaoMin(),PosicoesCamposEnum.CPF_DEPENDENTE.getPosicaoMax());
					fimInformarNomeDependente = true;

				}
			}
		}
		return resp;
	}

}
