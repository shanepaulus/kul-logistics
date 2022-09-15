package com.kul.logistics.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

		Set<String> visitedLocations = new HashSet<>();
		Stack<String> unvisitedLocations = new Stack<>();

		unvisitedLocations.add(origin);

		while (!unvisitedLocations.isEmpty()) {
			Location currentLocation = locationMap.get(unvisitedLocations.pop());
			List<LocationLink> locationLinkList = currentLocation.getLocationLinkList();

			if (!locationLinkList.isEmpty()) {
				for (LocationLink locationLink : locationLinkList) {
					Double shortestDistance = shortestDistanceMap.get(locationLink.getAdjacentLocation());

					if (locationLink.getDistance() < shortestDistance) {
						shortestDistanceMap.put(locationLink.getAdjacentLocation(), locationLink.getDistance());
						previousLocationMap.put(locationLink.getAdjacentLocation(), currentLocation.getName());
					}
				}

				String nextLocation = getNextShortestDistanceLocation(shortestDistanceMap, currentLocation.getName());
				unvisitedLocations.add(nextLocation);
				visitedLocations.add(currentLocation.getName());
			}

			if (currentLocation.getName().equals(destination)) {
				break;
			}
		}

		System.out.println("The shortestDistanceMap >> " + shortestDistanceMap);
		System.out.println("The previousLocationMap >> " + previousLocationMap);
		System.out.println("The unvisitedLocationMap >> " + unvisitedLocations);
		System.out.println("The visitedLocations >> " + visitedLocations);

		//System.out.println(calculateRoute(previousLocationMap, origin, destination));

		return null;
	}

//	private String calculateRoute(Map<String, String> distanceMap, String origin, String destination) {
//		StringBuilder routeBuilder = new StringBuilder();
//		String currentLocation = destination;
//
//		while (!currentLocation.equals(origin)) {
//			routeBuilder.append(currentLocation);
//			currentLocation = distanceMap.get(currentLocation);
//
//			System.out.println("Current location >> " + currentLocation);
//		}
//
//		return routeBuilder.toString();
//	}

	private String getNextShortestDistanceLocation(Map<String, Double> shortestDistanceMap, String currentLocation) {
		Double lowestDistance = null;
		String nextShortestDistance = null;

		for (Map.Entry<String, Double> entry : shortestDistanceMap.entrySet()) {
			if (!entry.getKey().equals(currentLocation) && (lowestDistance == null || entry.getValue() < lowestDistance)) {
				lowestDistance = entry.getValue();
				nextShortestDistance = entry.getKey();
			}
		}

		return nextShortestDistance;
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
