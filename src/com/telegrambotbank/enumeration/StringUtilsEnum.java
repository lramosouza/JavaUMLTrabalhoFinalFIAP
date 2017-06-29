package com.telegrambotbank.enumeration;

/**
 * Enum que contém constantes para strings
 * @author BRQVotorantim
 *
 */
public enum StringUtilsEnum {

	BLANK(" "), PULAR_LINHA("\n");

	private String blank = " ";

	StringUtilsEnum(String blank) {
		this.blank = blank;
	}

	public String getBlank() {
		return blank;
	}

	public void setBlank(String blank) {
		this.blank = blank;
	}

}
