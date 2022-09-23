package com.challenge.druid.controller;

import com.challenge.druid.dto.UsuarioDTO;
import com.challenge.druid.entity.Usuario;
import com.challenge.druid.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  /**
   * Método para consultar Usuario por su email
   * @param email del usuario
   * @return Información del usuario
   */
  @GetMapping("/filtrar/{email}")
  @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
  public ResponseEntity<UsuarioDTO> getUserByEmail(@PathVariable("email") String email){
    return ResponseEntity.ok(usuarioService.getUserByEmail(email));
  }

  /**
   * método para consultar usuarios por rango de fechas
   * @param fechaNacimientoInicial Fecha nacimiento inicial para consultar
   * @param fechaNacimientoFinal Fecha nacimiento final para consultar
   * @return
   * @throws EntityNotFoundException
   */
  @GetMapping("/filtrarPorFechas")
  @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<UsuarioDTO>> getUserByBirthDaysDates(
          @RequestParam(value = "fechaNacimientoInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaNacimientoInicial,
          @RequestParam(value = "fechaNacimientoFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaNacimientoFinal) throws EntityNotFoundException {
    return ResponseEntity.ok(usuarioService.getUserByBirthDaysDates(fechaNacimientoInicial, fechaNacimientoFinal));
  }

  /**
   * Método para eliminar un usuario
   * @param idUsuario para poder hacer el borrado
   * @return Mensaje de respuesta
   */
  @DeleteMapping("/borrar/{idUsuario}")
  @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> removeUserById(@PathVariable("idUsuario") Integer idUsuario){
    return ResponseEntity.ok(usuarioService.removeUserById(idUsuario));
  }

  /**
   * Método para crear un usuario
   * @param usuario Usuario a crear
   * @return Mensaje de respuesta
   */
  @PostMapping("/crear")
  @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> createUser(@RequestBody @Valid Usuario usuario){
    return ResponseEntity.ok(usuarioService.createUser(usuario));
  }
}
