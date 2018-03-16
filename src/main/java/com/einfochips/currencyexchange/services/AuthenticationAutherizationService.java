package com.einfochips.currencyexchange.services;


import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.einfochips.currencyexchange.domain.Users;

/**
 * @author akash.shinde
 *
 */
public interface AuthenticationAutherizationService {
	// Method will validate login details
	boolean getLoginDetails(String userName, String password);
	// Method will load user details based on userName
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	// Method will get user details based on userName
	Users getLoginUserInfo(String userName);

}
