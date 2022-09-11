package com.kul.logistics.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.kul.logistics.domain.User;
import com.kul.logistics.mapper.UserMapper;
import com.kul.logistics.mapper.UserResponseMapper;
import com.kul.logistics.model.UserLoginRequestModel;
import com.kul.logistics.model.UserRegisterRequestModel;
import com.kul.logistics.model.UserRegisterResponseModel;
import com.kul.logistics.model.UserUpdateRequestModel;
import com.kul.logistics.service.UserService;

import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserRegisterResponseModel> registerUser(@Valid @RequestBody UserRegisterRequestModel requestModel) {
		User user = UserMapper.INSTANCE.mapFromUserRegisterRequestModel(requestModel);
		user = userService.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseMapper.INSTANCE.mapFromUserToRegisterNodel(user));
	}

	@PutMapping
	public ResponseEntity updateUser(@Valid @RequestBody UserUpdateRequestModel requestModel) {
		return null;
	}

	public ResponseEntity loginUser(@RequestBody UserLoginRequestModel loginRequestModel) {
		return null;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exc) {
		Map<String, String> errors = new HashMap<>();
		exc.getBindingResult()
				.getAllErrors()
				.forEach(e -> {
					String fieldName = ((FieldError) e).getField();
					String message = e.getDefaultMessage();
					errors.put(fieldName, message);
				});

		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataException.class)
	public Map<String, String> handleDataAccessException(DataAccessException exc) {
		return null;
	}
}
