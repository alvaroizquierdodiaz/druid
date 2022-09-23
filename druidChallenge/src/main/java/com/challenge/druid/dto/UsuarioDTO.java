package com.challenge.druid.dto;

import com.challenge.druid.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {

  private int id;
  private String nombreUsuario;
  private String email;
  private String password;
  private String nombre;
  private String apellidos;
  private LocalDate fechaNacimiento;

  //Por defecto crea un usuario normal
  //Si quiero un usuario Admin debo pasar este campo roles
  private Set<String> roles = new HashSet<>();


  public UsuarioDTO(Usuario usuario) {
    this.id = usuario.getId();
    this.nombreUsuario = getNombreUsuario();
    this.email = usuario.getEmail();
    this.password = usuario.getEmail();
    this.nombre = usuario.getNombre();
    this.apellidos = usuario.getApellidos();
    this.fechaNacimiento = usuario.getFechaNacimiento();
  }
}
