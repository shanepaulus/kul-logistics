package com.kul.logistics.service;

import com.kul.logistics.domain.User;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 10-Sep-2022
 */

public interface UserService {

	boolean registerUser(User user);

	String loginUser(User user);

}
