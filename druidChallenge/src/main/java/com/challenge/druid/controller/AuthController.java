package com.challenge.druid.controller;

import com.challenge.druid.dto.JwtDto;
import com.challenge.druid.dto.LoginDTO;
import com.challenge.druid.entity.Rol;
import com.challenge.druid.entity.Usuario;
import com.challenge.druid.enums.RoleEnum;
import com.challenge.druid.jwt.JwtUtil;
import com.challenge.druid.repository.UsuarioRepository;
import com.challenge.druid.service.RolService;
import com.challenge.druid.util.validator.SwaggerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private RolService rolService;

  @Autowired
  private JwtUtil jwtUtil;

  //Espera un json y lo convierte a tipo clase UsuarioDTO
  @PostMapping("/nuevoUsuario")
  public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody Usuario nuevoUsuario){
    Set<Rol> roles = new HashSet<>();
    roles.add(rolService.getByRolName(RoleEnum.ROLE_USER).get());
    if(nuevoUsuario.getRoles().contains("admin")){
      roles.add(rolService.getByRolName(RoleEnum.ROLE_ADMIN).get());
    }
    nuevoUsuario.setRoles(roles);
    return new ResponseEntity<>(usuarioRepository.save(nuevoUsuario), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginDTO loginUsuario){
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtil.generateToken(authentication.getName());
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    JwtDto jwtDto = new JwtDto(jwt, SwaggerConstants.AUTHORIZATION_TYPE, userDetails.getUsername(), userDetails.getAuthorities());
    return new ResponseEntity<>(jwtDto, HttpStatus.OK);
  }

}
