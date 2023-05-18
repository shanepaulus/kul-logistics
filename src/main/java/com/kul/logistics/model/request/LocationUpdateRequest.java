package com.kul.logistics.model.request;

import com.kul.logistics.model.LocationLinkModel;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@Data
public class LocationUpdateRequest {

  @NotNull(message = "Id required!")
  private Integer id;
  private String name;
  private String description;
  private List<LocationLinkModel> adjacentLocations;

}
