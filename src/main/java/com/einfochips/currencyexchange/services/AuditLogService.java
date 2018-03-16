package com.einfochips.currencyexchange.services;

import org.springframework.stereotype.Service;

import com.einfochips.currencyexchange.domain.AuditLog;
import com.einfochips.currencyexchange.domain.Users;

@Service
public interface AuditLogService {

	/**
	 * Returns the list of audit log currencies for user
	 * 
	 * @return
	 */
	public Iterable<AuditLog> getSearchedCurrenciesForUser(double id);

	/**
	 * Used to get the audit log by id
	 * @param id
	 * @return
	 */
	public AuditLog getAuditLog(String id);
	/**
	 * This method used save audit log
	 * 
	 * @param id
	 * @return
	 */
	public AuditLog save(AuditLog auditLog);

}