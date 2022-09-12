package com.kul.logistics.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 10-Sep-2022
 */

@Data
public class UserLoginRequestModel {

	@NotBlank(message = "Email required!")
	private String email;
	@NotBlank(message = "Password required!")
	private String password;

}
