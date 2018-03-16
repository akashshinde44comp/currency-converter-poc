package com.einfochips.currencyexchange.dto;

/**
 * @author akash.shinde
 *
 */
public class AuthenticationDto {

	private String userLoginId;
	private String userPassword;

	public AuthenticationDto() {
		// Empty Constructor
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
