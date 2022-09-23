package com.challenge.druid.service;

import com.challenge.druid.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
    var user = usuarioRepository.findByNombreUsuario(nombreUsuario);

    if(!user.isPresent()) {
      throw new UsernameNotFoundException(nombreUsuario);
    }
    return new org.springframework.security.core.userdetails.User(user.get().getNombreUsuario(), user.get().getPassword(), new ArrayList<>());
  }

}