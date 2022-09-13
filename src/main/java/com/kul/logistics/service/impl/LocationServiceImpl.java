package com.kul.logistics.service.impl;

import java.util.List;

import com.kul.logistics.domain.Location;
import com.kul.logistics.repo.LocationRepository;
import com.kul.logistics.service.LocationService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

	private final LocationRepository locationRepository;

	@Override
	public List<Location> findAll() {
		return locationRepository.findAll();
	}
}
