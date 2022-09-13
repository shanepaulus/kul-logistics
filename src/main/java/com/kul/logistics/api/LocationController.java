package com.kul.logistics.api;

import java.util.List;

import com.kul.logistics.mapper.LocationResponseMapper;
import com.kul.logistics.model.response.LocationResponse;
import com.kul.logistics.service.LocationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@RestController
@RequestMapping("/api/locations")
@AllArgsConstructor
public class LocationController {

	private final LocationService locationService;

	@GetMapping
	public ResponseEntity<List<LocationResponse>> allLocations() {
		List<LocationResponse> locationResponseList = LocationResponseMapper
				.INSTANCE.mapFromLocationList(locationService.findAll());
		return ResponseEntity.ok(locationResponseList);
	}
}
