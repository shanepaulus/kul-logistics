package com.kul.logistics.model.response;

import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 14-Sep-2022
 */

@Data
public class RouteResponse {

	private String route;
	private Double totalDistance;
	private Double costOfDelivery;

}
