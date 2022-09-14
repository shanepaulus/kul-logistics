package com.kul.logistics.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 14-Sep-2022
 */

@Data
public class RouteRequest {

	@NotBlank(message = "Origin required")
	private String origin;
	@NotBlank(message = "Destination required")
	private String destination;

}
