package com.kul.logistics.service;

import com.kul.logistics.domain.User;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 10-Sep-2022
 */

public interface UserService {

	User registerUser(User user);

	User updateUser(User user);

	String generateToken(String email);

}
