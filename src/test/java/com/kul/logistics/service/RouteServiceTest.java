package com.kul.logistics.service;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 14-Sep-2022
 */

@SpringBootTest
public class RouteServiceTest {

	@SpyBean
	private LocationService locationService;
	@Autowired
	private RouteService routeService;

	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Transactional
	public void testCalculateRoute() {
		System.out.println("--------------------------------------");
		System.out.println(routeService.calculateRoute("A", "C"));
		// Route should be A -> B -> F...
	}
}
