package com.einfochips.currencyexchange.dto;

import java.util.Date;

import javax.persistence.Column;

/**
 * @author akash.shinde
 *
 */

public class AuditLogDTO {

	// DTO variables declaration used for data transfer
	private Integer id;
	private String fromSymbolName;
	private double fromSymbolRate;
	private String toSymbolName;
	private double toSymbolRate;
	private double actualAmount;
	private double convertedAmount;
	private Date creationTime;

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFromSymbolName() {
		return fromSymbolName;
	}

	public void setFromSymbolName(String fromSymbolName) {
		this.fromSymbolName = fromSymbolName;
	}

	public double getFromSymbolRate() {
		return fromSymbolRate;
	}

	public void setFromSymbolRate(double fromSymbolRate) {
		this.fromSymbolRate = fromSymbolRate;
	}

	public String getToSymbolName() {
		return toSymbolName;
	}

	public void setToSymbolName(String toSymbolName) {
		this.toSymbolName = toSymbolName;
	}

	public double getToSymbolRate() {
		return toSymbolRate;
	}

	public void setToSymbolRate(double toSymbolRate) {
		this.toSymbolRate = toSymbolRate;
	}

	public double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public double getConvertedAmount() {
		return convertedAmount;
	}

	public void setConvertedAmount(double convertedAmount) {
		this.convertedAmount = convertedAmount;
	}
}
