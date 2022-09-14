package com.kul.logistics.api;

import javax.validation.Valid;

import com.kul.logistics.model.request.RouteRequest;
import com.kul.logistics.model.response.RouteResponse;
import com.kul.logistics.service.RouteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 14-Sep-2022
 */

@RestController
@AllArgsConstructor
@RequestMapping("/api/route")
public class RouteController {

	private final RouteService routeService;

	@PostMapping
	public ResponseEntity<RouteResponse> calculateRoute(@Valid @RequestBody RouteRequest request) {
		return null;
	}
}
