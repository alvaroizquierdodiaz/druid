package com.challenge.druid.service;


import com.challenge.druid.entity.Rol;
import com.challenge.druid.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

  @Autowired
  private RolService rolService;

  @Override
  public void run(String... args) throws Exception {
    Rol rolAdmin = new Rol(1, RoleEnum.ROLE_ADMIN);
    Rol rolUser = new Rol(2, RoleEnum.ROLE_USER);
    rolService.save(rolAdmin);
    rolService.save(rolUser);
  }
}