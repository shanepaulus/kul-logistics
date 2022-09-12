package com.kul.logistics.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 11-Sep-2022
 */

@Data
public class UserUpdateRequestModel {

	@NotBlank(message = "Id cannot be empty")
	private Integer id;
	@NotBlank(message = "Name cannot be empty")
	private String name;
	@NotBlank(message = "Password cannot be empty")
	private String password;

}
