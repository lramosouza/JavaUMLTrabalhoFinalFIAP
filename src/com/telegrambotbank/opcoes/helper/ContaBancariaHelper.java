package com.telegrambotbank.opcoes.helper;

import java.io.IOException;
import java.nio.file.Path;

import com.telegrambotbank.datatype.ClienteVO;
import com.telegrambotbank.datatype.ContaBancariaVO;
import com.telegrambotbank.datatype.DadosTitularVO;
import com.telegrambotbank.datatype.DependenteVO;
import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.enumeration.StringUtilsEnum;
import com.telegrambotbank.enumeration.TipoContaCorrenteEnum;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.ContaOuAgenciaInvalidaException;
import com.telegrambotbank.file.helper.ArquivoReaderHelper;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;

public class ContaBancariaHelper {

	public static String buscarDadosConta(LancamentoVO dadosOperacao) throws ArquivoInvalidoException, ContaOuAgenciaInvalidaException {
		
		String dadosContaCorrente = null;
		String dadosArquivoDependente;
		
		DadosTitularVO dadosTitular = new DadosTitularVO(); 
		
		DependenteVO dependente = new DependenteVO();
		
		ClienteVO cliente = new ClienteVO();
		
		ContaBancariaVO contaBancaria = new ContaBancariaVO();
		
		Path arquivoContaCorrente = ArquivoContaCorrenteUtil.obterCaminhoArquivo(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria());
		
		ArquivoReaderHelper arquivoContaCorrenteReaderContaCorrente = new ArquivoReaderHelper(arquivoContaCorrente);
		
		if (!arquivoContaCorrenteReaderContaCorrente.isArquivoExistente(arquivoContaCorrente)) {
			throw new ContaOuAgenciaInvalidaException();
		}
		
		Path arquivoDependentes = ArquivoContaCorrenteUtil.obterCaminhoArquivoDependentes(dadosOperacao.getContaBancaria(), dadosOperacao.getAgenciaBancaria());
		
		ArquivoReaderHelper arquivoContaCorrenteReaderDependentes = new ArquivoReaderHelper(arquivoDependentes);
		
		if (!arquivoContaCorrenteReaderDependentes.isArquivoExistente(arquivoDependentes)) {
			throw new ContaOuAgenciaInvalidaException();
		}
		
		/**
		 * Le o arquivo da conta bancária
		 */
		try {
			arquivoContaCorrenteReaderContaCorrente.visitFile(arquivoContaCorrente, null);
			 dadosContaCorrente = arquivoContaCorrenteReaderContaCorrente.getDadosArquivo();
		} catch (IOException e) {
			throw new ArquivoInvalidoException();
		}
		
		/**
		 * Le o arquivo de dependentes
		 */
		try {
			arquivoContaCorrenteReaderDependentes.visitFile(arquivoDependentes, null);
			dadosArquivoDependente = arquivoContaCorrenteReaderDependentes.getDadosArquivo();
		} catch (IOException e) {
			throw new ArquivoInvalidoException();
		}
		
		try{
			montarDadosTitular(dadosContaCorrente, dadosArquivoDependente, dadosTitular, dependente, cliente, contaBancaria);
		}catch(Exception e){
			throw new ArquivoInvalidoException();
		}
		
		StringBuilder dadosTitularLayout = new StringBuilder();
		
		dadosTitularLayout.append("Seu Nome: ")
		.append(dadosTitular.getDadosTitular().getNome())
		.append(StringUtilsEnum.PULAR_LINHA.getBlank())
		.append("Seu CPF: ").append(dadosTitular.getDadosTitular().getCPF())
		.append(StringUtilsEnum.PULAR_LINHA.getBlank())
		.append("Tipo de Conta: ").append(contaBancaria.getTipo())
		.append(StringUtilsEnum.PULAR_LINHA.getBlank())
		.append("Data de Nascimento: ").append(cliente.getDataNascimento())
		.append(StringUtilsEnum.PULAR_LINHA.getBlank())
		.append("Email: ").append(cliente.getEmail())
		.append(StringUtilsEnum.PULAR_LINHA.getBlank())
		.append("Dependentes: ").append(dependente.getNomeDependente())
		.append(StringUtilsEnum.PULAR_LINHA.getBlank())
		.append("CPF Dependente: ").append(dependente.getCpfDependente());		

		return dadosTitularLayout.toString();
	}

	private static void montarDadosTitular(String dadosContaCorrente, String dadosArquivoDependente,
			DadosTitularVO dadosTitular, DependenteVO dependente, ClienteVO cliente, ContaBancariaVO contaBancaria) {
		cliente.setNome(dadosContaCorrente.substring(23, 123).trim()); // nome
		cliente.setCPF(dadosContaCorrente.substring(124,135)); // cpf
		contaBancaria.setTipo(dadosContaCorrente.substring(136, 137).equalsIgnoreCase("1") ? TipoContaCorrenteEnum.SIMPLES : TipoContaCorrenteEnum.CONJUNTA); // tipo conta
		cliente.setDataNascimento(dadosContaCorrente.substring(138, 148).trim()); // data nascimento
		cliente.setEmail(dadosContaCorrente.substring(149, 249).trim()); // e-mail
		
//		for (String dadosDependente : dadosArquivoDependente) {
			dependente.setNomeDependente(dadosArquivoDependente.substring(0, 99).trim()); //nome dependente
			dependente.setCpfDependente(dadosArquivoDependente.substring(100,111)); // cpf cliente	
//		}
		
		
		dadosTitular.setDadosTitular(cliente);
		dadosTitular.setContaBancaria(contaBancaria);		
		dadosTitular.setDadosDependente(dependente);
	}

}
