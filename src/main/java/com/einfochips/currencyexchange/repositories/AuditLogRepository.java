package com.einfochips.currencyexchange.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.einfochips.currencyexchange.domain.AuditLog;

/**
 * @author akash.shinde
 *
 */
@Repository
public interface AuditLogRepository extends CrudRepository<AuditLog, Long> {
	public Iterable<AuditLog> findByUserId(double id);
}
