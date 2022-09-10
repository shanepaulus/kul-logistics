package com.kul.logistics.model;

import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 10-Sep-2022
 */

@Data
public class UserRegisterRequestModel {

	private String name;
	private String email;
	private String password;

}
