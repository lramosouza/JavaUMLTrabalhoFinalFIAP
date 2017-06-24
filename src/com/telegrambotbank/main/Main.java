package com.telegrambotbank.main;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.enumeration.OpcoesBotEnum;
import com.telegrambotbank.enumeration.TipoContaCorrenteEnum;
import com.telegrambotbank.exception.ContaInexistenteException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.helper.GeneralHelper;
import com.telegrambotbank.opcoes.mediator.OpcoesMediator;
import com.telegrambotbank.opcoes.util.DepositoBancarioUtil;

public class Main {
	public static void main(String[] args) {
		// Mediator responsável por verificar qual é a ação a ser tomada de
		// acordo com a opção desejada
		OpcoesMediator opcoesMediator = new OpcoesMediator();

		// Criação do objeto bot com as informações de acesso
		TelegramBot bot = TelegramBotAdapter.build("332862407:AAGAwq3hj0XGS3y_TlrWtkQuc2Lh8deSes0");

		// objeto responsável por receber as mensagens
		GetUpdatesResponse updatesResponse;

		//objeto responsável por gerenciar o envio de respostas
		SendResponse sendResponse;
		
		// objeto responsável por gerenciar o envio de ações do chat
		BaseResponse baseResponse;
		
		// Mockery TODO retirar - INÍCIO

		ContaBancariaVO contaCorrenteDepositante = new ContaBancariaVO();
		contaCorrenteDepositante.setAgenciaBancaria("6252");
		contaCorrenteDepositante.setNuContaCorrete("176117");
		contaCorrenteDepositante.setTipo(TipoContaCorrenteEnum.SIMPLES);

		/*ClienteVO cliente = new ClienteVO();
		cliente.setCPF(new BigDecimal("42847256881"));
		cliente.setDataNascimento(new Date());
		cliente.setEmail("teste@teste.com.br");
		cliente.setNome("Teste Leandro");*/

		//contaCorrenteDepositante.setCliente(cliente);

		// Mockery TODO retirar - FIM
		// controle de off-set, isto é, a partir deste ID será lido as
		// mensagens
		// pendentes na fila
		int m = 0;

		// loop infinito pode ser alterado por algum timer de intervalo curto
		while (true) {
			// executa comando no Telegram para obter as mensagens pendentes a
			// partir de um off-set (limite inicial)
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			// lista de mensagens
			List<Update> updates = updatesResponse.updates();

			// análise de cada ação da mensagem
			for (Update update : updates) {
			
				// atualização do off-set
				m = update.updateId() + 1;

				String mensagemRecebida = update.message().text();

				System.out.println("Recebendo mensagem:" + mensagemRecebida);


			
					if(OpcoesBotEnum.START.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)){
						baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), new GeneralHelper().getMsgBoasVindas() ));
						mensagemRecebida = "";
					}
					
					if (OpcoesBotEnum.DEPOSITAR.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)) {
						ContaBancariaVO contaCorrenteDestino = new ContaBancariaVO();

						boolean fimInformarValor = false;
						boolean fimInformarAgencia = false;
						boolean fimInformarConta = false;

						String mensagemRetorno = null;
						try{
							BigDecimal valorDeposito = DepositoBancarioUtil.solicitarValorInformadoDeposito(bot, update,
								fimInformarValor);

							DepositoBancarioUtil.solicitarNuAgenciaDestinoDeposito(bot, update, contaCorrenteDestino,
								fimInformarAgencia);

							mensagemRetorno = DepositoBancarioUtil.solicitarNuContaDestinoDeposito(opcoesMediator, bot,
								update, contaCorrenteDepositante, contaCorrenteDestino, fimInformarConta,
								mensagemRetorno, valorDeposito);
						}catch(Exception e){
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));

						}
						baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), mensagemRetorno));
						mensagemRecebida = "";
					}
				    if(OpcoesBotEnum.CRIAR_CONTA.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)){
						String mensagemRetorno = null;
						
						mensagemRetorno = "você não pode criar uma conta no nosso banco!";
						baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), mensagemRetorno));
						mensagemRecebida = "";
						updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+2));
						
					}
				    if(OpcoesBotEnum.HELP.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)){
				    	baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), new GeneralHelper().getMsgHelp()));
						mensagemRecebida = "";
						updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+2));
				    }
				    if(OpcoesBotEnum.TARIFAS.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)){
				    	baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), new GeneralHelper().getTarifas()));
						mensagemRecebida = "";
						updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+2));
				    }

//					baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
//					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
//				mensagemRecebida = "";
//				
				
				
				// envio de "Escrevendo" antes de enviar a resposta
				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));

				// verificaï¿½ï¿½o de aï¿½ï¿½o de chat foi enviada com sucesso
				System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

				// envio da mensagem de resposta
				//sendResponse = bot.execute(new
				 //SendMessage(update.message().chat().id(), "Nï¿½o entend..."));

				// verificaï¿½ï¿½o de mensagem enviada com sucesso
				System.out.println("Mensagem Enviada?" +
						baseResponse.isOk());
				

			}
		}
	}

}