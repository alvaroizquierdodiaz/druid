package com.challenge.druid.repository;

import com.challenge.druid.entity.Rol;
import com.challenge.druid.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

  Optional<Rol> getRolByName(RoleEnum name);
}