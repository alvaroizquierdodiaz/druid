package com.challenge.druid.service;

import com.challenge.druid.entity.Rol;
import com.challenge.druid.enums.RoleEnum;
import com.challenge.druid.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RolService {

  @Autowired
  private RolRepository rolRepository;

  public Optional<Rol> getByRolName(RoleEnum name){
    return rolRepository.getRolByName(name);
  }

  public void save(Rol rol){
    rolRepository.save(rol);
  }
}
