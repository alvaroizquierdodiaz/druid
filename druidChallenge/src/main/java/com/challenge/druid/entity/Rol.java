package com.challenge.druid.entity;

import com.challenge.druid.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROLES")
public class Rol {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @NotNull
  private RoleEnum name;
}
