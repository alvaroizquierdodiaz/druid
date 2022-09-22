package com.challenge.druid.entity;

import com.challenge.druid.util.Constants;
import com.challenge.druid.util.validator.ValidAge;
import com.challenge.druid.util.validator.ValidPassword;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @NotEmpty(message = "El email no puede estar vacío")
  @Email(message = "El email no tiene el formato correcto", regexp = Constants.EMAIL_REGEX)
  @Column(unique = true)
  private String email;

  @NotNull
  @ValidPassword
  @NotEmpty(message = "La contraseña no puede estar vacía")
  private String password;

  @NotNull
  @NotEmpty(message = "El nombre no puede estar vacío")
  @Pattern(regexp = Constants.NAME_REGEX, message = "El nombre no puede contener números")
  private String nombre;

  @NotNull
  @NotEmpty(message = "Los apellidos no puede estar vacíos")
  @Pattern(regexp = Constants.NAME_REGEX, message = "Los apellidos no pueden contener números")
  private String apellidos;

  @NotNull
  private LocalDate fechaNacimiento;

  @ValidAge
  private Integer edad;

}
