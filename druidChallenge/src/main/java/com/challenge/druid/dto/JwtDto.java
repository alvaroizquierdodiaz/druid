package com.challenge.druid.dto;

import com.challenge.druid.util.validator.SwaggerConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtDto implements Serializable {

  private String token;
  private String bearer = SwaggerConstants.AUTHORIZATION_TYPE;
  private String nombreUsuario;
  private Collection<? extends GrantedAuthority> authorities;

}
