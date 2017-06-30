package com.telegrambotbank.opcoes.helper;

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
import com.telegrambotbank.exception.ValorInvalidoException;
import com.telegrambotbank.opcoes.util.Utils;

public class OprecoesHelper {
	
	
	public static String solicitarContaCliente(TelegramBot bot, Update update) throws CampoInvalidoException {
		GetUpdatesResponse updatesResponse;

		boolean fimInformarConta = false;

		int m;

		String resp = StringUtilsEnum.BLANK.getBlank();

		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(),
				"Favor Informe o número da sua conta"));
		m = update.updateId() + 1;

		while (fimInformarConta == false) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

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

	public static String solicitarAgenciaCliente(TelegramBot bot, Update update) throws CampoInvalidoException {

		GetUpdatesResponse updatesResponse;

		boolean fimInformarAgencia = false;

		int m;

		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(),
				"Favor Informe a Agência da sua conta"));

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


	public static BigDecimal solicitarValor(TelegramBot bot, Update update)
			throws ValorInvalidoException {
		
		boolean fimInformarValor = false;

		GetUpdatesResponse updatesResponse;

		int m;

		// Declaração de variáveis da conta
		BigDecimal valorDeposito = BigDecimal.ZERO;

		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Favor Informe o Valor da Operação"));

		m = update.updateId() + 1;
		String resp = "";

		while (fimInformarValor == false) {
			bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m + 2));

			// lista de mensagens
			List<Update> updates2 = updatesResponse.updates();

			// análise de cada ação da mensagem
			for (Update update1 : updates2) {
				resp = update1.message().text().trim();
				if (resp != null) {
					try {
						Utils.validarTamanhoMensagem(resp, PosicoesCamposEnum.VALOR_LANCAMENTO.getPosicaoMin(),
								PosicoesCamposEnum.VALOR_LANCAMENTO.getPosicaoMax());
						valorDeposito = new BigDecimal(resp);
						fimInformarValor = true;
					} catch (Exception e) {
						throw new ValorInvalidoException();
					}
				}
			}
		}
		return valorDeposito;
	}

}
