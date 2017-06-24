package com.telegrambotbank.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface PosicaoConta {
	
	int posicaoInicial();
	int posicaoFinal();

}
