package com.telegrambotbank.opcoes.helper;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
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
import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.enumeration.PosicoesCamposEnum;
import com.telegrambotbank.enumeration.StringUtilsEnum;
import com.telegrambotbank.enumeration.TipoLancamentoEnum;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.CampoInvalidoException;
import com.telegrambotbank.exception.ContaOuAgenciaInvalidaException;
import com.telegrambotbank.exception.SaldoInsuficienteException;
import com.telegrambotbank.exception.ValorInvalidoException;
import com.telegrambotbank.file.helper.ArquivoReaderHelper;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;
import com.telegrambotbank.opcoes.util.Utils;

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

	public static DepositoVO validarValorDeposito(DepositoVO dadosDeposito) throws ContaOuAgenciaInvalidaException {
		if (dadosDeposito == null) {
			throw new ContaOuAgenciaInvalidaException();
		} else {
			return dadosDeposito;
		}
	}

	public static LancamentoVO montarDadosOperacaoDebito(DepositoVO dadosDeposito) {

		LancamentoVO dadosOperacaoDebito = new LancamentoVO();
		dadosOperacaoDebito.setAgenciaBancaria(dadosDeposito.getAgenciaDepositante());
		dadosOperacaoDebito.setContaBancaria(dadosDeposito.getContaDepositante());
		dadosOperacaoDebito.setValorLancamento(dadosDeposito.getValor());
		dadosOperacaoDebito.setTipoLancamento(TipoLancamentoEnum.DEBITO.getTipoLancamento());
		dadosOperacaoDebito.setDataLancamento(LocalDate.now());

		return dadosOperacaoDebito;
	}

	public static LancamentoVO montarDadosOperacaoCredito(DepositoVO dadosDeposito) {

		LancamentoVO dadosOperacaoCredito = new LancamentoVO();
		dadosOperacaoCredito.setAgenciaBancaria(dadosDeposito.getAgenciaDestino());
		dadosOperacaoCredito.setContaBancaria(dadosDeposito.getContaDestino());
		dadosOperacaoCredito.setValorLancamento(dadosDeposito.getValor());
		dadosOperacaoCredito.setTipoLancamento(TipoLancamentoEnum.CREDITO.getTipoLancamento());
		dadosOperacaoCredito.setDataLancamento(LocalDate.now());

		return dadosOperacaoCredito;
	}

	public static String solicitarNuContaDestinoDeposito(TelegramBot bot, Update update, BigDecimal valorDeposito)
			throws SaldoInsuficienteException, ContaOuAgenciaInvalidaException, IOException, CampoInvalidoException {

		GetUpdatesResponse updatesResponse;

		boolean fimInformarConta = false;

		int m;

		String resp = StringUtilsEnum.BLANK.getBlank();

		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(),
				"Favor Informe o número da conta na qual deseja depositar"));
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

	public static String solicitarNuAgenciaDestinoDeposito(TelegramBot bot, Update update)
			throws CampoInvalidoException {

		GetUpdatesResponse updatesResponse;

		boolean fimInformarAgencia = false;

		int m;

		bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
		bot.execute(new SendMessage(update.message().chat().id(),
				"Favor Informe a Agência da conta na qual deseja depositar"));

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

	public static BigDecimal solicitarValorInformadoDeposito(TelegramBot bot, Update update)
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
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

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
	
	/**
	 * Valida se arquivo de agência e conta corrente é válido no momento do depósito
	 * @param dadosOperacaoDebito
	 * @param dadosOperacaoCredito
	 * @throws ArquivoInvalidoException
	 * @throws ContaOuAgenciaInvalidaException
	 */
	public static void validarArquivoAgenciaEContaDeposito(LancamentoVO dadosOperacaoDebito,
			LancamentoVO dadosOperacaoCredito) throws ArquivoInvalidoException, ContaOuAgenciaInvalidaException {

		Path arquivoDebito = ArquivoContaCorrenteUtil.obterCaminhoArquivo(dadosOperacaoDebito.getContaBancaria(),
				dadosOperacaoDebito.getAgenciaBancaria());
		
		ArquivoReaderHelper arquivoContaCorrenteReaderDebito = new ArquivoReaderHelper(
				arquivoDebito);
		
		if (!arquivoContaCorrenteReaderDebito.isArquivoExistente(arquivoDebito)) {
			throw new ContaOuAgenciaInvalidaException();
		}

		Path arquivoCredito = ArquivoContaCorrenteUtil.obterCaminhoArquivo(dadosOperacaoCredito.getContaBancaria(),
				dadosOperacaoCredito.getAgenciaBancaria());
		
		ArquivoReaderHelper arquivoContaCorrenteReaderCredito = new ArquivoReaderHelper(
				arquivoCredito);

		if (!arquivoContaCorrenteReaderCredito.isArquivoExistente(arquivoCredito)) {
			throw new ContaOuAgenciaInvalidaException();
		}

	}

}
