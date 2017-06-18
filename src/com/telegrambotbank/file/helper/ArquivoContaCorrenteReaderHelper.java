package com.telegrambotbank.file.helper;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * Classe responsável por efetuar a leitura do arquivo de conta corrente
 * 
 * @author user
 *
 */
public class ArquivoContaCorrenteReaderHelper extends SimpleFileVisitor<Path> {

	private Path destino;
	private String dadosArquivo;

	public ArquivoContaCorrenteReaderHelper(Path destino) {
		this.destino = destino;
	}
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		System.out.println("Analisando arquivo: " + file.getFileName() + "....");
		String caminho = file.toString();
		String contaCorrente = caminho.substring(23, 28);
		List<String> listaArquivos = Files.readAllLines(file);

		listaArquivos.forEach(item -> {
			if (item.contains(contaCorrente)) {
				setDadosArquivo(item);
				;
			}
		});

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.out.println("Falhou ");
		return FileVisitResult.CONTINUE;
	}

	public Path getDestino() {
		return destino;
	}

	public void setDestino(Path destino) {
		this.destino = destino;
	}

	public String getDadosArquivo() {
		return dadosArquivo;
	}

	public void setDadosArquivo(String dadosArquivo) {
		this.dadosArquivo = dadosArquivo;
	}

}
