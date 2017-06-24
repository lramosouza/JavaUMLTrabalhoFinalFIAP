package com.telegrambotbank.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface PosicaoCliente {

	int posicaoInicial();
	int posicaoFinal();
}
