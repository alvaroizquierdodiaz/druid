package com.challenge.druid.entity;

import com.challenge.druid.util.DruidConstants;
import com.challenge.druid.util.validator.ValidPassword;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USUARIO")
public class Usuario implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private int id;

  @NotBlank
  private String nombreUsuario;

  @NotNull
  @NotEmpty(message = "El email no puede estar vacío")
  @Email(message = "El email no tiene el formato correcto", regexp = DruidConstants.EMAIL_REGEX)
  @Column(unique = true, name = "EMAIL")
  private String email;

  @NotNull
  @ValidPassword
  @NotEmpty(message = "La contraseña no puede estar vacía")
  @Column(name = "PASSWORD")
  private String password;

  @NotNull
  @NotEmpty(message = "El nombre no puede estar vacío")
  @Pattern(regexp = DruidConstants.NAME_REGEX, message = "El nombre no puede contener números")
  @Column(name = "NOMBRE")
  private String nombre;

  @NotNull
  @NotEmpty(message = "Los apellidos no puede estar vacíos")
  @Pattern(regexp = DruidConstants.NAME_REGEX, message = "Los apellidos no pueden contener números")
  @Column(name = "APELLIDOS")
  private String apellidos;

  @NotNull
  @Column(name = "FECHA")
  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate fechaNacimiento;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Rol> roles = new HashSet<>();

}
