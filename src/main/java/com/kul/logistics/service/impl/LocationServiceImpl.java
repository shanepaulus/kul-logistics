package com.kul.logistics.service.impl;

import com.kul.logistics.domain.Location;
import com.kul.logistics.repo.LocationRepository;
import com.kul.logistics.service.LocationLinkService;
import com.kul.logistics.service.LocationService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

  private final LocationRepository locationRepository;
  private final LocationLinkService locationLinkService;

  @Override
  public Location createLocation(Location location) {
    Location persisted = locationRepository.save(location);
    return locationRepository.save(persisted);
  }

  @Override
  public List<Location> findAll() {
    return locationRepository.findAll();
  }

  @Override
  public Location updateLocation(Location location) {
    Optional<Location> persistedLocationOptional = locationRepository.findById(location.getId());
    persistedLocationOptional
        .ifPresentOrElse((c) -> updateLocationObject(c, location),
            () -> {
              throw new IllegalStateException("Could not find an existing location with "
                  + "id={" + location.getId() + "} to update!");
            });
    return locationRepository.save(location);
  }

  @Override
  @Transactional
  public boolean deleteLocation(Integer id) {
    Location location = locationRepository.findById(id).orElse(null);

    if (location != null) {
      if (locationLinkService.deleteLocationLinkByAdjacentLocation(location.getName())) {
        locationRepository.deleteById(id);
      }
    } else {
      throw new IllegalStateException("Could not find an existing location with "
          + "id={" + id + "} to delete!");
    }

    return locationRepository.findById(id).isEmpty();
  }

  private void updateLocationObject(Location oldLocation, Location newLocation) {
    if (newLocation.getName() == null) {
      newLocation.setName(oldLocation.getName());
    }

    if (newLocation.getDescription() == null || newLocation.getDescription().isEmpty()) {
      newLocation.setDescription(oldLocation.getDescription());
    }
  }
}
