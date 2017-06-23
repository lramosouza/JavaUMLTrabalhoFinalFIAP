package com.telegrambotbank.datatype;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Value Object que representa um cliente
 * 
 * @author user
 *
 */
public class ClienteVO implements Serializable {

	/**
	 * 
	 */
	
	@Posicao(posicaoInicial = 0, posicaoFinal = 49)
	private String nome;
	
	@Posicao(posicaoInicial = 49, posicaoFinal = 59)
	private String dataNascimento;
	
	@Posicao(posicaoInicial = 59, posicaoFinal = 70)
	private String CPF;
	
	@Posicao(posicaoInicial = 70, posicaoFinal = 115)
	private String email;

	
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public ClienteVO getCienteByLine(String line) throws IllegalArgumentException, IllegalAccessException{
		ClienteVO cliente = new ClienteVO();
		try {
		for (Field f : ClienteVO.class.getDeclaredFields()) {
			   Posicao posicao = f.getDeclaredAnnotation(Posicao.class);
			   if (posicao != null){
				   f.set(this, line.substring(posicao.posicaoInicial(),posicao.posicaoFinal()).trim());
			   }
			}
		} catch (Exception e) {
	        throw new IllegalStateException(e);
	    }
		return this;
	}
	
	public String getLineByCliente(ClienteVO cliente)throws IllegalArgumentException, IllegalAccessException{
		String retorno = "";
		int aux = 0;
		try {
			for (Field f : cliente.getClass().getDeclaredFields()) {
				aux = 0;
				   Posicao posicao = f.getDeclaredAnnotation(Posicao.class);
				   if (posicao != null){
					  String blankSpaces = "";
					  String fieldValue =  f.get(cliente).toString();
					  aux = (posicao.posicaoFinal() - posicao.posicaoInicial()) - fieldValue.length();
					  
					  for (int i = 0; i < aux; i++) {
						  blankSpaces = blankSpaces + " ";
					}
					  fieldValue = fieldValue + blankSpaces;
					  retorno = retorno + fieldValue;
					  
				   }
				}
			} catch (Exception e) {
		        throw new IllegalStateException(e);
		    }
		
		
		return retorno;
		
	}

}
