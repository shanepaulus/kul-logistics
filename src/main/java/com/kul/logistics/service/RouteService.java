package com.kul.logistics.service;

import com.kul.logistics.domain.Route;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 14-Sep-2022
 */

public interface RouteService {

  Route calculateRoute(String origin, String destination);

}
