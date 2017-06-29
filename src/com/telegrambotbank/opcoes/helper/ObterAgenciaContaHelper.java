package com.telegrambotbank.opcoes.helper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.telegrambotbank.enumeration.PosicoesCamposEnum;
import com.telegrambotbank.enumeration.StringUtilsEnum;
import com.telegrambotbank.exception.CampoInvalidoException;
import com.telegrambotbank.exception.ContaOuAgenciaInvalidaException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.opcoes.util.Utils;

public class ObterAgenciaContaHelper {
	
	public static String solicitarNuAgencia(TelegramBot bot, Update update)
			throws CampoInvalidoException {

		GetUpdatesResponse updatesResponse;

		boolean fimInformarAgencia = false;

		int m;

		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(),
				"Favor Informe sua Agência"));

		m = update.updateId() + 1;

		String resp = StringUtilsEnum.BLANK.getBlank();

		while (fimInformarAgencia == false) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m + 1));

			// lista de mensagens
			List<Update> updates3 = updatesResponse.updates();

			// análise de cada ação da mensagem
			for (Update update2 : updates3) {
				resp = update2.message().text().trim();
				if (resp != null) {
					Utils.validarTamanhoMensagem(resp, PosicoesCamposEnum.AGENCIA_CONTA_CORRENTE.getPosicaoMin(),
							PosicoesCamposEnum.AGENCIA_CONTA_CORRENTE.getPosicaoMax());
					fimInformarAgencia = true;
				}
			}
		}
		return resp;
	}
	
	public static String solicitarNuConta(TelegramBot bot, Update update)
			throws ContaOuAgenciaInvalidaException, IOException, CampoInvalidoException {

		GetUpdatesResponse updatesResponse;

		boolean fimInformarConta = false;

		int m;

		String resp = StringUtilsEnum.BLANK.getBlank();

		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(),
				"Favor Informe o número da sua conta"));
		m = update.updateId() + 1;

		while (fimInformarConta == false) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m + 2));

			// lista de mensagens
			List<Update> updates4 = updatesResponse.updates();

			// análise de cada ação da mensagem
			for (Update update3 : updates4) {
				resp = update3.message().text().trim();
				if (resp != null) {
					Utils.validarTamanhoMensagem(resp, PosicoesCamposEnum.NU_CONTA_CORRENTE.getPosicaoMin(),
							PosicoesCamposEnum.NU_CONTA_CORRENTE.getPosicaoMax());
					fimInformarConta = true;
				}
			}
		}
		return resp;
	}
}
