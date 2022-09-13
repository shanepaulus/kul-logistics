package com.kul.logistics.service;

import java.util.List;

import com.kul.logistics.domain.Location;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

public interface LocationService {

	List<Location> findAll();

	Location createLocation(Location location);

	Location updateLocation(Location location);

	boolean deleteLocation(Integer id);
}
