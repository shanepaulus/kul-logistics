package com.kul.logistics.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 13-Sep-2022
 */

@Data
public class LocationCreateRequest {

  @NotBlank(message = "Name required")
  private String name;
  private String description;

}
