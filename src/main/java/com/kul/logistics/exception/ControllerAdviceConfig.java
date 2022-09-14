package com.kul.logistics.exception;

import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import org.hibernate.exception.ConstraintViolationException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@RestControllerAdvice
@Slf4j
public class ControllerAdviceConfig {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleFieldValidationErrors(MethodArgumentNotValidException exc) {
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

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleContrstraintExceptionError(ConstraintViolationException exc) {
		return new ErrorMessage(exc.getClass() + ": " + exc.getMessage());
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorMessage handleAuthenticationException(AuthenticationException exc) {
		return new ErrorMessage(exc.getClass() + ": " + exc.getMessage());
	}

	@ExceptionHandler(JWTVerificationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handTokenExpiredException(TokenExpiredException exc) {
		return new ErrorMessage(exc.getClass() + ": " + exc.getMessage());
	}
}
