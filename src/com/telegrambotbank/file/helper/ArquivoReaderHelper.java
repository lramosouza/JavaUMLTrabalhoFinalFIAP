package com.telegrambotbank.file.helper;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import com.telegrambotbank.exception.ArquivoInvalidoException;

/**
 * Classe responsável por efetuar a leitura do arquivo de conta corrente
 * 
 * @author user
 *
 */
public class ArquivoReaderHelper extends SimpleFileVisitor<Path> {

	private Path destino;
	private String dadosArquivo;

	public ArquivoReaderHelper(Path destino) {
		this.destino = destino;
	}
	
	/**
	 * Implementação de um visitFlile responsável por ler o arquivo
	 */
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		
		try{
			PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");
			System.out.println("Analisando arquivo: " + file.getFileName() + "....");
			if(matcher.matches(file.getFileName())) {
				List<String> listaArquivos = Files.readAllLines(file);
	
				listaArquivos.forEach(item -> {
					setDadosArquivo(item);
				});
			}
		}catch(Exception e){
			throw new ArquivoInvalidoException();
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.out.println("Falhou ");
		return FileVisitResult.TERMINATE;
		
	}
	
	public boolean isArquivoExistente(Path file) throws ArquivoInvalidoException{
		try{
			return Files.exists(file);
		}catch(Exception e){
			throw new ArquivoInvalidoException();
		}
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
