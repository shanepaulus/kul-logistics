package com.kul.logistics.repo;

import com.kul.logistics.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
