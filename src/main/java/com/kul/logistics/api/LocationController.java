package com.kul.logistics.api;

import java.util.List;

import javax.validation.Valid;

import com.kul.logistics.domain.Location;
import com.kul.logistics.mapper.LocationEntityMapper;
import com.kul.logistics.mapper.LocationResponseMapper;
import com.kul.logistics.model.request.LocationCreateRequest;
import com.kul.logistics.model.request.LocationUpdateRequest;
import com.kul.logistics.model.response.LocationListResponse;
import com.kul.logistics.model.response.LocationResponse;
import com.kul.logistics.service.LocationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/create")
	public ResponseEntity<LocationResponse> create(@Valid @RequestBody LocationCreateRequest request) {
		Location location = LocationEntityMapper.INSTANCE.mapFromAddRequest(request);
		location = locationService.createLocation(location);
		LocationResponse locationResponse = LocationResponseMapper
				.INSTANCE.mapFromLocation(location);
		return ResponseEntity.status(HttpStatus.CREATED).body(locationResponse);
	}

	@PutMapping("/update")
	public ResponseEntity<LocationResponse> updateLocation(@Valid @RequestBody LocationUpdateRequest request) {
		Location location = LocationEntityMapper.INSTANCE.mapFromUpdateRequest(request);
		location = locationService.updateLocation(location);
		return ResponseEntity.ok(LocationResponseMapper.INSTANCE.mapFromLocation(location));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteLocation(@PathVariable Integer id) {
		if (locationService.deleteLocation(id)) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<LocationListResponse> allLocations() {
		List<LocationResponse> locations = LocationResponseMapper
				.INSTANCE.mapFromLocationList(locationService.findAll());
		return ResponseEntity.ok(new LocationListResponse(locations));
	}

	//	@PutMapping("/update")
	//	public ResponseEntity<LocationResponse> update(@Valid @RequestBody )
}
