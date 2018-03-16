package com.einfochips.currencyexchange.dto;

import java.util.List;

public class Rates {
	private String symbol;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "Rates [symbol=" + symbol + "]";
	}

	
}
