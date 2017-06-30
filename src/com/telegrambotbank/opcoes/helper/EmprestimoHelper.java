package com.telegrambotbank.opcoes.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.telegrambotbank.datatype.EmprestimoVO;
import com.telegrambotbank.enumeration.PosicoesCamposEnum;
import com.telegrambotbank.exception.CampoInvalidoException;
import com.telegrambotbank.exception.EmprestimoException;
import com.telegrambotbank.exception.PrazoException;
import com.telegrambotbank.opcoes.util.Utils;

public class EmprestimoHelper {
	
	public static BigDecimal valorEmprestimoDisponivel(TelegramBot bot, Update update, BigDecimal saldo) throws CampoInvalidoException{
		GetUpdatesResponse updatesResponse;
		boolean fimInformarNomeDependente = false;
		int m;
		
		BigDecimal valor = new BigDecimal(40)	;	
		BigDecimal valorAprovado = saldo.multiply(valor);
		BigDecimal valorContratado = BigDecimal.ZERO;
		
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Voc� tem um cr�dito aprovado no valor de R$ "+valorAprovado
				+" podendo parcelar em at� 36x! Deseja Fazer um Emprestimo no valor de quanto?"));
		
		m = update.updateId() + 1;
		String resp = "";
		
		while (fimInformarNomeDependente == false) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			// lista de mensagens
			List<Update> updates = updatesResponse.updates();

			// an�lise de cada a��o da mensagem
			for (Update u : updates) {
				resp = u.message().text().trim();
				if (resp != null) {
					Utils.validarTamanhoMensagem(resp, PosicoesCamposEnum.VALOR_CONTRATADO.getPosicaoMin(), PosicoesCamposEnum.VALOR_CONTRATADO.getPosicaoMax());
					valorContratado = new BigDecimal(resp);
					fimInformarNomeDependente = true;
				}
			}
		}
		
		return valorContratado;
	}
	
	public static Integer prazoEmprestimo(TelegramBot bot, Update update) throws CampoInvalidoException, PrazoException{
		GetUpdatesResponse updatesResponse;
		boolean fimInformarNomeDependente = false;
		int m;
		
		Integer quantParcelas = 0;
		
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), "Voc� deseja parcelar em quantas vezes? "));
		
		m = update.updateId() + 2;
		String resp = "";
		
		while (fimInformarNomeDependente == false) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			// lista de mensagens
			List<Update> updates = updatesResponse.updates();

			// an�lise de cada a��o da mensagem
			for (Update u : updates) {
				resp = u.message().text().trim();
				if (resp != null) {
					Utils.validarTamanhoMensagem(resp, PosicoesCamposEnum.PRAZO.getPosicaoMin(), PosicoesCamposEnum.PRAZO.getPosicaoMax());
					quantParcelas = Integer.parseInt(resp);
					fimInformarNomeDependente = true;
				}
			}
		}
		
		return quantParcelas;
	}
	
	public static BigDecimal calculaEmprestimo(TelegramBot bot, Update update, EmprestimoVO vo) throws CampoInvalidoException{
		GetUpdatesResponse updatesResponse;
		boolean fimEmprestimo = false;
		int m;
		
		BigDecimal taxa = new BigDecimal("0.05");
		BigDecimal iof = new BigDecimal("15.0");
		
		BigDecimal juros = vo.getVlContratado().multiply(taxa).multiply(new BigDecimal(vo.getPrazo().toString()));
		BigDecimal valorCalculado = vo.getVlContratado().add(juros.add(iof));		
		vo.setVlParcela(valorCalculado.divide(new BigDecimal(vo.getPrazo().toString())).divide(new BigDecimal("1.3"),3,RoundingMode.UP));
		vo.setVlCalculado(valorCalculado);
		
		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(), vo.getPrazo()+"X de R$ " +vo.getVlParcela()+" Deseja Contratar? Sim ou N�o"));
		
		m = update.updateId() + 3;
		String resp = "";
		
		while (fimEmprestimo == false) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			// lista de mensagens
			List<Update> updates = updatesResponse.updates();

			// an�lise de cada a��o da mensagem
			for (Update u : updates) {
				resp = u.message().text().trim();
				if (resp != null) {
					String msgRetorno = resp;
					if("sim".equalsIgnoreCase(msgRetorno)){
						fimEmprestimo = true;
					}else{
						new EmprestimoException("Se caso mudar de id�ia estamos a disposi��o.");
					}
					
				}
			}
		}
		
		return vo.getVlCalculado();
	}
}
