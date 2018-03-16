package com.einfochips.currencyexchange.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author akash.shinde
 *
 */

@Entity
@Table(name = "audit_log")
@NamedQuery(name = "AuditLog.findAll", query = "SELECT a FROM AuditLog a")
public class AuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "from_symbol_name")
	private String fromSymbolName;

	@Column(name = "from_symbol_rate")
	private double fromSymbolRate;

	@Column(name = "to_symbol_name")
	private String  toSymbolName;

	@Column(name = "to_symbol_rate")
	private double toSymbolRate;

	@Column(name = "actual_amount")
	private double actualAmount;

	@Column(name = "converted_amount")
	private double convertedAmount;
	
	@Column(name = "user_id")
	private double userId;
	
	@Column(name = "creation_time")
	private Date creationTime;
	
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public double getUserId() {
		return userId;
	}

	public void setUserId(double userId) {
		this.userId = userId;
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

	public void setFromSymbolRate(long fromSymbolRate) {
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

	public void setToSymbolRate(long toSymbolRate) {
		this.toSymbolRate = toSymbolRate;
	}

	public double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(long actualAmount) {
		this.actualAmount = actualAmount;
	}

	public double getConvertedAmount() {
		return convertedAmount;
	}

	public void setConvertedAmount(long convertedAmount) {
		this.convertedAmount = convertedAmount;
	}
}
