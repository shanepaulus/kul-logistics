package com.kul.logistics.service.impl;

import com.kul.logistics.repo.LocationLinkRepository;
import com.kul.logistics.service.LocationLinkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 14-Sep-2022
 */

@Service
@AllArgsConstructor
@Slf4j
public class LocationLinkServiceImpl implements LocationLinkService {

  private final LocationLinkRepository locationLinkRepository;

  @Override
  public boolean deleteLocationLinkByAdjacentLocation(String adjacentLocation) {
    locationLinkRepository.deleteByAdjacentLocation(adjacentLocation);
    return locationLinkRepository.findAllByAdjacentLocation(adjacentLocation).isEmpty();
  }
}
