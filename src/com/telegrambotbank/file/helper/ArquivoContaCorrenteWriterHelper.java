package com.telegrambotbank.file.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe responsável por efetuar a escrita no arquivo de contas correntes
 * @author user
 *
 */
public class ArquivoContaCorrenteWriterHelper {
	
	
	public void alteraLinha(String dadoAntigo, String dadoNovo, String arquivo) throws IOException {
	    String arquivoTmp = arquivo+"-tmp";

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
	}

}
