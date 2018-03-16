package com.einfochips.currencyexchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einfochips.currencyexchange.domain.AuditLog;
import com.einfochips.currencyexchange.repositories.AuditLogRepository;
import com.einfochips.currencyexchange.services.AuditLogService;

/**
 * @author akash.shinde
 *
 */
@Service
public class AuditLogServiceImpl implements AuditLogService  {

	@Autowired
	private AuditLogRepository auditLogRepository;

	/* (non-Javadoc)
	 * @see com.einfochips.currencyexchange.services.AuditLogService#getSearchedCurrenciesForUser(java.lang.String)
	 */
	@Override
	public Iterable<AuditLog> getSearchedCurrenciesForUser(double id) {
		return auditLogRepository.findByUserId(id);
	}

	/* (non-Javadoc)
	 * @see com.einfochips.currencyexchange.services.AuditLogService#getAuditLog(java.lang.String)
	 */
	@Override
	public AuditLog getAuditLog(String id) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.einfochips.currencyexchange.services.AuditLogService#save(com.einfochips.currencyexchange.domain.AuditLog)
	 */
	@Override
	public AuditLog save(AuditLog auditLog) {
		return auditLogRepository.save(auditLog);
	}
}
