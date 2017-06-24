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
import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.datatype.DepositoVO;
import com.telegrambotbank.datatype.OperacaoVO;
import com.telegrambotbank.exception.ContaInexistenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.exception.ValorInvalidoException;
import com.telegrambotbank.opcoes.mediator.OpcoesMediator;

/**
 * Classe Util responsável por dar apoio as opreações de depósito
 * 
 * @author user
 *
 */
public class DepositoBancarioHelper {

	public static DepositoVO montarDadosDepositoBancario(ContaBancariaVO contaDepositante, ContaBancariaVO contaDestino,
			BigDecimal valorDeposito) {
		DepositoVO depositoBancario = new DepositoVO();
		depositoBancario.setAgenciaDepositante(contaDepositante.getAgenciaBancaria());
		depositoBancario.setAgenciaDestino(contaDestino.getAgenciaBancaria());
		depositoBancario.setContaDepositante(contaDepositante.getNuContaCorrete());
		depositoBancario.setContaDestino(contaDestino.getNuContaCorrete());
		depositoBancario.setValor(valorDeposito);
		return depositoBancario;

	}

	public static DepositoVO validarValorDeposito(DepositoVO dadosDeposito) throws ContaInexistenteException {
		if (dadosDeposito == null) {
			throw new ContaInexistenteException();
		} else {
			return dadosDeposito;
		}
	}

	public static OperacaoVO montarDadosOperacaoDebito(DepositoVO dadosDeposito) {

		OperacaoVO dadosOperacaoDebito = new OperacaoVO();
		dadosOperacaoDebito.setAgenciaBancaria(dadosDeposito.getAgenciaDepositante());
		dadosOperacaoDebito.setContaBancaria(dadosDeposito.getContaDepositante());
		dadosOperacaoDebito.setValor(dadosDeposito.getValor());

		return dadosOperacaoDebito;
	}
	
	public static OperacaoVO montarDadosOperacaoCredito(DepositoVO dadosDeposito) {

		OperacaoVO dadosOperacaoCredito = new OperacaoVO();
		dadosOperacaoCredito.setAgenciaBancaria(dadosDeposito.getAgenciaDestino());
		dadosOperacaoCredito.setContaBancaria(dadosDeposito.getContaDestino());
		dadosOperacaoCredito.setValor(dadosDeposito.getValor());

		return dadosOperacaoCredito;
	}
	
	public static void solicitarNuContaDestinoDeposito(TelegramBot bot, Update update,
			ContaBancariaVO contaCorrenteDepositante, ContaBancariaVO contaCorrenteDestino, String mensagemRetorno, BigDecimal valorDeposito)
			throws SaldoInsuficienteException, ContaInexistenteException, IOException {
		GetUpdatesResponse updatesResponse;
		boolean fimInformarConta = false;
		int m;
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Favor Informe o número da conta na qual deseja depositar"));
		m = update.updateId() + 1;	
		while (fimInformarConta == false) {
				updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+2));
				
				// lista de mensagens
				List<Update> updates4 = updatesResponse.updates();
				
				// análise de cada ação da mensagem
				for (Update update3 : updates4) {
					String resp = update3.message().text().trim();
					if (resp != null) {
						contaCorrenteDestino.setNuContaCorrete(resp);
						fimInformarConta = true;
					}
				}
		}
	}

	public static void solicitarNuAgenciaDestinoDeposito(TelegramBot bot, Update update,
			ContaBancariaVO contaCorrenteDestino) {
		GetUpdatesResponse updatesResponse;
		boolean fimInformarAgencia = false;
		int m;
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Favor Informe a Agência da conta na qual deseja depositar"));
		m = update.updateId() + 1;	
		while (fimInformarAgencia == false) {
				updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+1));
				
				// lista de mensagens
				List<Update> updates3 = updatesResponse.updates();
				
				// análise de cada ação da mensagem
				for (Update update2 : updates3) {
					String resp = update2.message().text().trim();
					if (resp != null) {
						contaCorrenteDestino.setAgenciaBancaria(resp);
						fimInformarAgencia = true;
					}
				}
		}
	}

	public static BigDecimal solicitarValorInformadoDeposito(TelegramBot bot, Update update) throws ValorInvalidoException {
		boolean fimInformarValor = false;
		GetUpdatesResponse updatesResponse;
		int m;
		//Declaração de variáveis da conta
		BigDecimal valorDeposito = BigDecimal.ZERO;
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Favor Informe o Valor da Operação"));
		m = update.updateId() + 1;	
		while (fimInformarValor == false) {
			bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));
			
			// lista de mensagens
			List<Update> updates2 = updatesResponse.updates();
			
			// análise de cada ação da mensagem
			for (Update update1 : updates2) {
				String resp = update1.message().text().trim();
				if (resp != null) {
					try{
						valorDeposito = new BigDecimal(resp);
						fimInformarValor = true;
					}catch(Exception e){
						throw new ValorInvalidoException();
					}					
				}
			}
		}
		return valorDeposito;
	}

}
