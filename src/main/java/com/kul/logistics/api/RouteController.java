package com.kul.logistics.api;

import com.kul.logistics.domain.Route;
import com.kul.logistics.mapper.RouteResponseMapper;
import com.kul.logistics.model.request.RouteRequest;
import com.kul.logistics.model.response.RouteResponse;
import com.kul.logistics.service.RouteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    Route route = routeService.calculateRoute(request.getOrigin(), request.getDestination());
    return ResponseEntity.ok(RouteResponseMapper.INSTANCE.mapFromRoute(route));
  }
}
