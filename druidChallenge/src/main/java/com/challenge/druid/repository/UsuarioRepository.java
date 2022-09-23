package com.challenge.druid.repository;

import com.challenge.druid.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

  Optional<Usuario> findByEmail(String email);

  Optional<Usuario> findByNombreUsuario(String nombreUsuario);

  @Query(value = "SELECT * FROM USUARIO u WHERE u.FECHA BETWEEN :fechaNacimientoInicial AND :fechaNacimientoFinal", nativeQuery = true)
  List<Usuario> getUsersBetweenDates(@Param("fechaNacimientoInicial") LocalDate fechaNacimientoInicial,
                                       @Param("fechaNacimientoFinal") LocalDate fechaNacimientoFinal);
}