package com.kul.logistics.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@AllArgsConstructor
public class LocationListResponse {

  @JsonProperty
  private final List<LocationResponse> locations;

}
