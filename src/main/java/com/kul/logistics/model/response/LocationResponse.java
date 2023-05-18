package com.kul.logistics.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kul.logistics.model.LocationLinkModel;
import java.util.List;
import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationResponse {

  private Integer id;
  private String name;
  private String description;
  private List<LocationLinkModel> adjacentLocations;

}
