package com.kul.logistics.repo;

import com.kul.logistics.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 10-Sep-2022
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
