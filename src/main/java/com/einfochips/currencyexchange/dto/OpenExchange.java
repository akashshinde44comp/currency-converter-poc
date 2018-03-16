package com.einfochips.currencyexchange.dto;

public class OpenExchange {

	private String disclaimer;
	private String license;
	private long timestamp;
	private String base;
	private Rates rates;

	public Rates getRates() {
		return rates;
	}

	public void setRates(Rates rates) {
		this.rates = rates;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	@Override
	public String toString() {
		return "OpenExchange [disclaimer=" + disclaimer + ", license=" + license + ", timestamp=" + timestamp
				+ ", base=" + base + ", rates=" + rates + "]";
	}
	
}
