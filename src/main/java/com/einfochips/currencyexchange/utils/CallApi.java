package com.einfochips.currencyexchange.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.einfochips.currencyexchange.dto.OpenExchange;

public class CallApi {

	private CallApi() {
	}
	
	/**
	 * @param restApiUrl
	 * @return
	 */
	public static ResponseEntity<String> restCallForGetLiveRatesStatus(
			String restApiUrl) {
		// Call the REST API base on restApiUrl
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(restApiUrl, String.class);
		return restTemplate.exchange(restApiUrl, HttpMethod.GET, null, String.class);
	}
}
