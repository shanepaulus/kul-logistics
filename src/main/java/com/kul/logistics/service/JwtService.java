package com.kul.logistics.service;

import java.util.Date;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 14-Sep-2022
 */

public interface JwtService {

	String generateToken(String email);

	boolean isTokenExpired(String token, Date currentDate);
	
}
