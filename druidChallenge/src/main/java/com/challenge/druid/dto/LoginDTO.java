package com.challenge.druid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable  {

  @NotBlank
  private String nombreUsuario;

  @NotBlank
  private String password;

}
