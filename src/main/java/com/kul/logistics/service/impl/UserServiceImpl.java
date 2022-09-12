package com.kul.logistics.service.impl;

import java.util.Collections;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kul.logistics.domain.User;
import com.kul.logistics.repo.UserRepository;
import com.kul.logistics.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public String generateToken(String email) {
		return JWT.create()
				.withSubject(email)
				.withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
				.withIssuer("kul-logistics")
				.sign(Algorithm.HMAC256("secret"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Inside the loadByUsername method!");
		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not found user by email={" + username + "}");
		} else {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
		}
	}
}
