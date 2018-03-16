package com.einfochips.currencyexchange.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.einfochips.currencyexchange.dto.SymbolLiveRatesDTO;
import com.einfochips.currencyexchange.utils.CallApi;


/**
 * @author akash.shinde
 *
 */
@RestController
public class RealtimeExchangeDataController {

	static final Logger logger = Logger.getLogger(RealtimeExchangeDataController.class);

	@Value("${current.exchange.rate.user.api.url}")
	String apiPath;

	@Value("${current.exchange.rate.user.api.key}")
	String apikey;
	
	@RequestMapping(value = "/get-realtime-exchange-rates", method = RequestMethod.GET)
	public ResponseEntity<List<SymbolLiveRatesDTO>>  getRealTimeExchangeData() {
		logger.info("API called");
		ResponseEntity<String> liveRates = CallApi
				.restCallForGetLiveRatesStatus(apiPath+apikey);
		System.out.println();
		String result  = liveRates.toString().replaceAll("<200 OK," , "").replaceAll(">", "");
		int startIndex  = result.indexOf(",{Access-Control");
		result  =  result.substring(0, startIndex);

		final JSONObject obj = new JSONObject(result);
		JSONObject rates  = obj.getJSONObject("rates");
		List<SymbolLiveRatesDTO> list =  new ArrayList<>();
		
		String symbols  = rates.toString().replaceAll("\\{", "").replaceAll("\\}", "");
		for(String str : symbols.split(",")){
			String []s  =str.split(":");
			SymbolLiveRatesDTO sym  =  new SymbolLiveRatesDTO();
			 sym.setLiveRate(Double.parseDouble(s[1]));
			 sym.setSymbolName(s[0].replaceAll("\\\"", ""));
			 list.add(sym);
			System.out.println(s[0] + ": "+ s[1]);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
}
