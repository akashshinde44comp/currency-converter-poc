package com.einfochips.currencyexchange.dto;

public class SymbolLiveRatesDTO {
	private String symbolName;
	private Double liveRate;

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public Double getLiveRate() {
		return liveRate;
	}

	public void setLiveRate(Double liveRate) {
		this.liveRate = liveRate;
	}

}
