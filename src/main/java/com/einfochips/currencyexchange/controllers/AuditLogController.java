package com.einfochips.currencyexchange.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.einfochips.currencyexchange.domain.AuditLog;
import com.einfochips.currencyexchange.dto.AuditLogDTO;
import com.einfochips.currencyexchange.services.AuditLogService;
import com.einfochips.currencyexchange.utils.UserTokenParser;

@RestController
public class AuditLogController {

	private static final Logger LOGGER = Logger.getLogger(AuditLogController.class);

	@Autowired
	private AuditLogService auditLogService;

	// API to get AuditLog
	@RequestMapping(value = "/get-user-currency-history", method = RequestMethod.GET)
	public ResponseEntity<List<AuditLogDTO>> getUserCurrencyHistory(HttpServletRequest httpServletRequest) {
		LOGGER.info("------IN GET get-user-currency-history -----".toUpperCase());
		ResponseEntity<List<AuditLogDTO>> response = null;
		Integer userId = UserTokenParser.getUserIdFromToken(httpServletRequest);
		LOGGER.info("check userId:: " + userId);
		List<AuditLogDTO> list = new ArrayList<>();
		for (Iterator<AuditLog> i = auditLogService.getSearchedCurrenciesForUser(Double.parseDouble(userId +"")).iterator(); i.hasNext();) {
			AuditLog item = i.next();
		    AuditLogDTO adto  =  new AuditLogDTO();;
		    adto.setActualAmount(item.getActualAmount());
		    adto.setConvertedAmount(item.getConvertedAmount());
		    adto.setFromSymbolName(item.getFromSymbolName());
		    adto.setFromSymbolRate(item.getFromSymbolRate());
		    adto.setId(item.getId());
		    adto.setToSymbolName(item.getToSymbolName());
		    adto.setToSymbolRate(item.getToSymbolRate());
		    adto.setCreationTime(item.getCreationTime());
		    list.add(adto);
		}
		if(list.size() >0){
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}else{
			response = new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
		}
		LOGGER.info("RETURING LIST");
		return response;
	}

	// API to Add Audit log for user
	@RequestMapping(value = "/add-user-log", method = RequestMethod.POST)
	public ResponseEntity<AuditLog> addUserLog(@RequestBody AuditLog auditLog, HttpServletRequest httpServletRequest) {
		LOGGER.info("------IN ADD  APP START-----");
		ResponseEntity<AuditLog> response;
		Integer userId = UserTokenParser.getUserIdFromToken(httpServletRequest);
		auditLog.setUserId(userId);
		auditLog.setCreationTime(new Date());
		return response = new ResponseEntity<>(auditLogService.save(auditLog), HttpStatus.OK);
	}
}
