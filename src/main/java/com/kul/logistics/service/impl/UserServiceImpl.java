package com.kul.logistics.service.impl;

import com.kul.logistics.domain.User;
import com.kul.logistics.repo.UserRepository;
import com.kul.logistics.service.UserService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 11-Sep-2022
 */

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return null;
	}

	@Override
	public String loginUser(User user) {
		return null;
	}
}
