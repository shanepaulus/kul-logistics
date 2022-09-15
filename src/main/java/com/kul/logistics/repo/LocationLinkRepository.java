package com.kul.logistics.repo;

import java.util.List;

import com.kul.logistics.domain.LocationLink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@Repository
public interface LocationLinkRepository extends JpaRepository<LocationLink, Integer> {

	void deleteByAdjacentLocation(String adjacentLocation);

	List<LocationLink> findAllByAdjacentLocation(String adjacentLocation);

}
