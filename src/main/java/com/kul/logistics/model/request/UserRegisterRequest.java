package com.kul.logistics.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 10-Sep-2022
 */

@Data
public class UserRegisterRequest {

  @NotBlank(message = "Name required!")
  private String name;
  @NotBlank(message = "Email required!")
  private String email;
  @NotBlank(message = "Password required!")
  private String password;

}
