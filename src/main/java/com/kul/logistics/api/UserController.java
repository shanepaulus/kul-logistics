package com.kul.logistics.api;

import javax.validation.Valid;

import com.kul.logistics.domain.User;
import com.kul.logistics.mapper.UserEntityMapper;
import com.kul.logistics.mapper.UserResponseMapper;
import com.kul.logistics.model.request.UserLoginRequest;
import com.kul.logistics.model.request.UserRegisterRequest;
import com.kul.logistics.model.response.UserLoginResponse;
import com.kul.logistics.model.response.UserRegisterResponse;
import com.kul.logistics.service.impl.UserServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 10-Sep-2022
 */

@RestController
@RequestMapping("/api/users")
@Slf4j
@AllArgsConstructor
public class UserController {

	private final UserServiceImpl userService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest requestModel) {
		User user = UserEntityMapper.INSTANCE.mapFromUserRegisterRequestModel(requestModel);
		user = userService.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseMapper.INSTANCE.mapFromUserToRegisterModel(user));
	}

	@PostMapping("/login")
	public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest loginRequestModel) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestModel.getEmail(), loginRequestModel.getPassword()));
		String token = userService.generateToken(loginRequestModel.getEmail());
		return ResponseEntity.ok(new UserLoginResponse(token));
	}
}
