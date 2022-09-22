package com.challenge.druid.dto;

import com.challenge.druid.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {

  private int id;
  private String email;
  private String password;
  private String nombre;
  private String apellidos;
  private LocalDate fechaNacimiento;

  public UsuarioDTO(Usuario usuario) {
    this.id = usuario.getId();
    this.email = usuario.getEmail();
    this.password = usuario.getEmail();
    this.nombre = usuario.getNombre();
    this.apellidos = usuario.getApellidos();
    this.fechaNacimiento = usuario.getFechaNacimiento();
  }
}
