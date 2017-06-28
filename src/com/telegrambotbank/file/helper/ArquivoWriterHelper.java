 	package com.telegrambotbank.file.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import com.telegrambotbank.datatype.DependenteVO;
import com.telegrambotbank.datatype.LancamentoVO;
import com.telegrambotbank.enumeration.PosicoesCamposEnum;
import com.telegrambotbank.enumeration.StringUtilsEnum;
import com.telegrambotbank.exception.ArquivoInvalidoException;
import com.telegrambotbank.exception.GravarArquivoDependenteException;
import com.telegrambotbank.file.util.ArquivoContaCorrenteUtil;
import com.telegrambotbank.opcoes.util.Utils;

/**
 * Classe responsável por efetuar a escrita no arquivo de contas correntes
 * 
 * @author user
 *
 */
public class ArquivoWriterHelper {

	public void alteraLinha(String dadoAntigo, String dadoNovo, String arquivo)
			throws IOException, ArquivoInvalidoException {
		String arquivoTmp = arquivo + "-tmp";
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTmp));
			BufferedReader reader = new BufferedReader(new FileReader(arquivo));

			String linha;

			while ((linha = reader.readLine()) != null) {
				if (linha.contains(dadoAntigo)) {
					linha = linha.replace(dadoAntigo, dadoNovo);
				}
				writer.write(linha + "\n");
			}

			writer.close();
			reader.close();

			new File(arquivo).delete();
			new File(arquivoTmp).renameTo(new File(arquivo));
		} catch (Exception e) {
			throw new ArquivoInvalidoException();
		}
	}

	public void gravarNovoArquivo(String caminho, StringBuffer layout)
			throws IOException, GravarArquivoDependenteException {
		try {
			FileWriter arq = new FileWriter(caminho);

			PrintWriter gravarArq = new PrintWriter(arq);

			gravarArq.print(layout);

			arq.close();

		} catch (Exception e) {
			throw new GravarArquivoDependenteException();
		}
	}
	
	/**
	 * Registra no arquivo de lançamentos, os dados do lançamento efetuado
	 * @param lancamento
	 * @param caminho
	 * @throws GravarArquivoDependenteException 
	 */
	public void inserirLinha(Path caminho, StringBuffer layout) throws GravarArquivoDependenteException {
		
		PrintWriter gravarArq = null;
		
		try {
			FileWriter arq = new FileWriter(caminho.toString(), true);

			gravarArq = new PrintWriter(arq);

			gravarArq.println(layout.toString());

			gravarArq.close();
			arq.close();
		
		}catch(Exception e){
			throw new GravarArquivoDependenteException();
		}
		
	}

}
