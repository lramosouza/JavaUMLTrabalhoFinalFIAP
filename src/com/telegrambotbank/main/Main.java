package com.telegrambotbank.main;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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
import com.telegrambotbank.datatype.ClienteVO;
import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.datatype.DependenteVO;
import com.telegrambotbank.datatype.EmprestimoVO;
import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.enumeration.OpcoesBotEnum;
import com.telegrambotbank.enumeration.TipoContaCorrenteEnum;
import com.telegrambotbank.exception.CampoInvalidoException;
import com.telegrambotbank.exception.ContaOuAgenciaInvalidaException;
import com.telegrambotbank.messages.GeneralMessages;
import com.telegrambotbank.opcoes.helper.DependenteHelper;
import com.telegrambotbank.opcoes.helper.DepositoBancarioHelper;
import com.telegrambotbank.opcoes.helper.EmprestimoHelper;
import com.telegrambotbank.opcoes.helper.ObterAgenciaContaHelper;
import com.telegrambotbank.opcoes.mediator.OpcoesMediator;
import com.telegrambotbank.opcoes.util.ClienteUtil;
import com.telegrambotbank.opcoes.util.Utils;

public class Main {
	public static void main(String[] args) {
		// Mediator responsável por verificar qual é a ação a ser tomada de
		// acordo com a opção desejada
		OpcoesMediator opcoesMediator = new OpcoesMediator();

		// Criação do objeto bot com as informações de acesso
		TelegramBot bot = TelegramBotAdapter.build("332862407:AAGAwq3hj0XGS3y_TlrWtkQuc2Lh8deSes0");

		// objeto responsável por receber as mensagens
		GetUpdatesResponse updatesResponse;

//		objeto responsável por gerenciar o envio de respostas
		SendResponse sendResponse;
		
		// objeto responsável por gerenciar o envio de ações do chat
		BaseResponse baseResponse;
		
		// Mockery TODO retirar - INÍCIO

		ContaBancariaVO contaCorrenteDepositante = new ContaBancariaVO();
		contaCorrenteDepositante.setAgenciaBancaria("6252");
		contaCorrenteDepositante.setNuContaCorrete("176117");

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
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), new GeneralMessages().getMsgBoasVindas() ));
						mensagemRecebida = "";
					
					} else if(OpcoesBotEnum.EMPRESTIMO.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)){
						EmprestimoVO emprestimoVO = new EmprestimoVO();
						String mensagemRetorno = null ;
						try{
							//TODO Mock teste emprestimo
							BigDecimal saldo = new BigDecimal(10000);
							
							ContaBancariaVO contaBancariaVO = obterAgenciaConta(bot, update);
							emprestimoVO.setCodEmprestimo(Integer.parseInt(contaBancariaVO.getAgenciaBancaria()+contaBancariaVO.getNuContaCorrete()));
							emprestimoVO.setVlContratado(EmprestimoHelper.valorEmprestimoDisponivel(bot, update, saldo));
							emprestimoVO.setPrazo(EmprestimoHelper.prazoEmprestimo(bot, update));
							emprestimoVO.setVlCalculado(emprestimoVO.getVlParcela().multiply(new BigDecimal(emprestimoVO.getPrazo().toString())));
							emprestimoVO.setDtContracao(new Date());
							emprestimoVO.setVlParcela(EmprestimoHelper.calculaEmprestimo(bot, update, emprestimoVO));
							
							
							mensagemRetorno = opcoesMediator.efetivarEmprestimo(emprestimoVO, contaBancariaVO);
							baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), mensagemRetorno));
							mensagemRecebida = "";
						} catch(Exception e){
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						}
					
					} else if (OpcoesBotEnum.DEPOSITAR.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)) {
						ContaBancariaVO contaCorrenteDestino = new ContaBancariaVO();
				
						String mensagemRetorno = null ;
						
						try{
							
							BigDecimal valorDeposito = DepositoBancarioHelper.solicitarValorInformadoDeposito(bot, update);

							contaCorrenteDestino.setAgenciaBancaria(DepositoBancarioHelper.solicitarNuAgenciaDestinoDeposito(bot, update));

							contaCorrenteDestino.setNuContaCorrete(DepositoBancarioHelper.solicitarNuContaDestinoDeposito(bot, update, valorDeposito));
							
							mensagemRetorno = opcoesMediator.depositar(contaCorrenteDepositante, contaCorrenteDestino, valorDeposito);
							
							baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), mensagemRetorno));
							mensagemRecebida = "";
							
						}catch(Exception e){
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						}						
					
					} else if (OpcoesBotEnum.INCLUIR_DEPENDENTE.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)) {
						DependenteVO dependente = new DependenteVO();

						String mensagemRetorno = null;
						
						try{
							dependente.setNomeDependente(DependenteHelper.solicitarNomeDependente(bot, update));
						
							dependente.setCpfDependente(DependenteHelper.solicitarCPFDependente(bot, update, dependente));
							
//							TODO TIRAR MOCK DA CONTA						
							LancamentoVO dadosOperacao = new LancamentoVO();
							dadosOperacao.setContaBancaria(contaCorrenteDepositante.getNuContaCorrete().trim());
							dadosOperacao.setAgenciaBancaria(contaCorrenteDepositante.getAgenciaBancaria().trim());
//							TODO TIRAR MOCK DA CONTA
							
							mensagemRetorno = opcoesMediator.cadastrarDependente(dependente, dadosOperacao);
							
							baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), mensagemRetorno));
							mensagemRecebida = "";
							
						}catch(Exception e){
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						}						
					
					} else if (OpcoesBotEnum.EXIBIR_MINHAS_INFORMACOES.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)){
						String mensagemRetorno = null;
						
//						TODO TIRAR MOCK DA CONTA						
						LancamentoVO dadosOperacao = new LancamentoVO();
						dadosOperacao.setContaBancaria(contaCorrenteDepositante.getNuContaCorrete().trim());
						dadosOperacao.setAgenciaBancaria(contaCorrenteDepositante.getAgenciaBancaria().trim());
//						TODO TIRAR MOCK DA CONTA
						
						try{
							mensagemRetorno = opcoesMediator.exibirInformacoesConta(dadosOperacao);
							baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), mensagemRetorno));
							mensagemRecebida = "";
						}catch (Exception e){
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						}
						
					} else if(OpcoesBotEnum.CRIAR_CONTA.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)) {				    	
				    	
				    	ClienteVO cli = new ClienteVO();
				    	
				    	cli.setNome(ClienteUtil.solicitarNomeCliente(bot, update));
				    	cli.setCPF(ClienteUtil.solicitarCpfCliente(bot, update));
				    	cli.setDataNascimento(ClienteUtil.solicitarDtNascCliente(bot, update));
				    	cli.setEmail(ClienteUtil.solicitarEmailCliente(bot, update));

				    	
				    	baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Estamos criando sua conta..."));
				    	
				    	ContaBancariaVO cntBancaria = new ContaBancariaVO();
				    	cntBancaria.setAgenciaBancaria(Utils.agencia());
				    	cntBancaria.setNuContaCorrete(Utils.gerarContaCorrente());
				    	cntBancaria.setCliente(cli);
						
				    	String mensagemRetorno = "Pronto! Anote o número da sua conta: \n"
				    			+ "Agência: " + cntBancaria.getAgenciaBancaria() + "\n"
				    			+ "Conta: " + cntBancaria.getNuContaCorrete();
				    	
				    	try{				    		
				    		opcoesMediator.criarArquivoConta(cntBancaria); //FIXME
				    	} catch (Exception e) {
				    		System.out.println(e.getMessage());
						}
				
				    	baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), mensagemRetorno));
						mensagemRecebida = "";
						updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+2));
						
					} else if(OpcoesBotEnum.HELP.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)){
				    	baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), new GeneralMessages().getMsgHelp()));
						mensagemRecebida = "";
						updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+2));
				    
					} else if(OpcoesBotEnum.TARIFAS.getOpcaoDesejada().equalsIgnoreCase(mensagemRecebida)){
				    	baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), new GeneralMessages().getTarifas()));
						mensagemRecebida = "";
						updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+2));
				    }else if (OpcoesBotEnum.SACAR.getOpcaoDesejada().equals(mensagemRecebida)){
				    	baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
//				    	OpcoesMediator 
//						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), new GeneralMessages().getTarifas()));
//						mensagemRecebida = "";
//						updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m+2));
				    	
				    }

//					baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
//					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
//				mensagemRecebida = "";
//				
				
				
				// envio de "Escrevendo" antes de enviar a resposta
//				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));

				// verificaï¿½ï¿½o de aï¿½ï¿½o de chat foi enviada com sucesso
//				System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

				// envio da mensagem de resposta
				//sendResponse = bot.execute(new
				 //SendMessage(update.message().chat().id(), "Nï¿½o entend..."));

				// verificaï¿½ï¿½o de mensagem enviada com sucesso
//				System.out.println("Mensagem Enviada?" +
//						baseResponse.isOk());
				

			}
		}
	}
	
	public static ContaBancariaVO obterAgenciaConta(TelegramBot bot, Update update) throws CampoInvalidoException, ContaOuAgenciaInvalidaException, IOException{
		ContaBancariaVO vo = new ContaBancariaVO();
		
		vo.setAgenciaBancaria(ObterAgenciaContaHelper.solicitarNuAgencia(bot, update));

		vo.setNuContaCorrete(ObterAgenciaContaHelper.solicitarNuConta(bot, update));
		
		return vo;
	}
}