package com.kul.logistics.api;

import com.kul.logistics.model.UserLoginRequestModel;
import com.kul.logistics.model.UserRegisterRequestModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 10-Sep-2022
 */

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {


	@PostMapping("/register")
	public ResponseEntity registerUser(@RequestBody UserRegisterRequestModel requestModel) {
		log.info("registerUser()");
		return ResponseEntity.ok(null);
	}

	@PutMapping("/update")
	public ResponseEntity updateUser() {
		log.info("updateUser()");
		return null;
	}

	public ResponseEntity loginUser(@RequestBody UserLoginRequestModel loginRequestModel) {
		return null;
	}
}
