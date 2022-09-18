package com.kul.logistics.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kul.logistics.domain.Location;
import com.kul.logistics.domain.LocationLink;
import com.kul.logistics.domain.Route;
import com.kul.logistics.service.LocationService;
import com.kul.logistics.service.RouteService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 14-Sep-2022
 */

@Service
@AllArgsConstructor
@Slf4j
public class RouteServiceImpl implements RouteService {

	private final LocationService locationService;

	@Override
	public Route calculateRoute(String origin, String destination) {
		Map<String, Location> locationMap = locationService
				.findAll()
				.stream()
				.collect(Collectors.toMap(Location::getName, Function.identity()));

		Map<String, Double> shortestDistanceMap = new HashMap<>();
		Map<String, String> previousLocationMap = new HashMap<>();

		preLoadShortestDistanceMap(shortestDistanceMap, locationMap.keySet());
		preLoadPreviousLocationMap(previousLocationMap, locationMap.keySet());

		Stack<String> unvisitedLocations = new Stack<>();
		unvisitedLocations.add(origin);

		while (!unvisitedLocations.isEmpty()) {
			Location currentLocation = locationMap.get(unvisitedLocations.pop());

			if (currentLocation.getLocationLinkList() != null && !currentLocation.getLocationLinkList().isEmpty()) {
				LocationLink nearestLocation = null;
				Double nearestLocationDistance = Double.MAX_VALUE;

				for (LocationLink locationLink : currentLocation.getLocationLinkList()) {
					// Set the nearest location link
					if (nearestLocationDistance > locationLink.getDistance()) {
						nearestLocationDistance = locationLink.getDistance();
						nearestLocation = locationLink;
					}

					// Update the shortest distance map
					if (shortestDistanceMap.get(locationLink.getAdjacentLocation()) > locationLink.getDistance()) {
						shortestDistanceMap.put(locationLink.getAdjacentLocation(), locationLink.getDistance());
						previousLocationMap.put(locationLink.getAdjacentLocation(), currentLocation.getName());
					}
				}

				if (nearestLocation != null) {
					unvisitedLocations.add(nearestLocation.getAdjacentLocation());
				}
			}
		}

		String path = getShortestDistancePath(previousLocationMap, origin, destination);
		Double totalDistance = getTotalDistance(previousLocationMap, shortestDistanceMap, origin, destination);

		Route route = new Route();
		route.setRoute(path);
		route.setTotalDistance(totalDistance);
		route.setCostOfDelivery(1 * totalDistance);
		return route;
	}

	private Double getTotalDistance(Map<String, String> previousLocationMap, Map<String, Double> shortestDistanceMap, String origin, String destination) {
		if (!shortestDistanceMap.containsKey(destination)) {
			throw new IllegalStateException("The origin / destination keys was not present in the shortest distance map!");
		} else {
			Double totalDistance = 0.0;
			String currentLocation = destination;

			while (!currentLocation.equals(origin)) {
				totalDistance += shortestDistanceMap.get(currentLocation);
				currentLocation = previousLocationMap.get(currentLocation);
			}

			return totalDistance;
		}
	}

	private String getShortestDistancePath(Map<String, String> shortestDistanceMap, String origin, String destination) {
		if (!shortestDistanceMap.containsKey(origin) || !shortestDistanceMap.containsKey(destination)) {
			throw new IllegalStateException("The origin / destination keys was not present in the shortest distance map!");
		} else {
			StringBuilder pathBuilder = new StringBuilder(destination).append(" > ");
			String currentLocation = shortestDistanceMap.get(destination);

			while (!currentLocation.equals(origin)) {
				pathBuilder.append(currentLocation).append(" > ");
				currentLocation = shortestDistanceMap.get(currentLocation);
			}

			pathBuilder.append(origin).reverse();
			return pathBuilder.toString();
		}
	}

	private void preLoadPreviousLocationMap(Map<String, String> previousLocationMap, Set<String> locationNameSet) {
		locationNameSet
				.forEach(l -> previousLocationMap.put(l, null));
	}

	private void preLoadShortestDistanceMap(Map<String, Double> shortestDistanceMap, Set<String> locationNameSet) {
		locationNameSet
				.forEach(l -> shortestDistanceMap.put(l, Double.MAX_VALUE));
	}
}
