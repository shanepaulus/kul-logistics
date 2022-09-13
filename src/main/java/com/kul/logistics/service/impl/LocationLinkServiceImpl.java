package com.kul.logistics.service.impl;

import com.kul.logistics.repo.LocationLinkRepository;
import com.kul.logistics.service.LocationLinkService;

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
public class LocationLinkServiceImpl implements LocationLinkService {

	private final LocationLinkRepository locationLinkRepository;

	@Override
	public boolean deleteLocationLinkByAdjacentLocationId(Integer id) {
		locationLinkRepository.deleteByAdjacentLocationId(id);
		return locationLinkRepository.findAllByAdjacentLocationId(id).isEmpty();
	}
}
