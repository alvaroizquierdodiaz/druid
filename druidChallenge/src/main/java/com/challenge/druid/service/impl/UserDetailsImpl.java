package com.challenge.druid.service.impl;

import com.challenge.druid.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;

  private int id;
  private String username;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public static UserDetailsImpl build(Usuario usuario) {
    List<GrantedAuthority> authorities = usuario.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList());

    return new UserDetailsImpl(
            usuario.getId(),
            usuario.getNombreUsuario(),
            usuario.getEmail(),
            usuario.getPassword(),
            authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
