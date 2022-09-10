package com.kul.logistics.model;

import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 10-Sep-2022
 */

@Data
public class UserLoginRequestModel {

	private String email;
	private String password;

}
