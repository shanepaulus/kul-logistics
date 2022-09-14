package com.kul.logistics.service.impl;

import com.kul.logistics.domain.Route;
import com.kul.logistics.service.LocationService;
import com.kul.logistics.service.RouteService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 14-Sep-2022
 */

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {

	private final LocationService locationService;

	@Override
	public Route calculateRoute(String origin, String destination) {
		return null;
	}
}
