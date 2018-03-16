package com.einfochips.currencyexchange.security;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public final class JWTParser {

	private JWTParser() {
		// Empty constructor
	}

	public static Claims tokenParser(HttpServletRequest request) {

		return Jwts.parser().setSigningKey("currency-converter")
				.parseClaimsJws(request.getHeader("X-Auth-Token")).getBody();
	}
}
