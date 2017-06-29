package com.telegrambotbank.enumeration;

import java.math.BigDecimal;

public enum TarifaEnum {
	
	
	SAQUE(new BigDecimal(1.50));
	
	
	private BigDecimal tarifa;
	
	TarifaEnum(BigDecimal tarifa){
		this.tarifa=tarifa;
	}

	public BigDecimal getTarifa() {
		return tarifa;
	}

	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}
	
	
}
