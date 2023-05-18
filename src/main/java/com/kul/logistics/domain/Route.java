package com.kul.logistics.domain;

import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 14-Sep-2022
 */

@Data
public class Route {

  private String route;
  private Double totalDistance;
  private Double costOfDelivery;

}
