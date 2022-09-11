package com.kul.logistics.model;

import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 11-Sep-2022
 */

@Data
public class UserUpdateRequestModel {

	private Integer id;
	private String name;
	private String password;

}
