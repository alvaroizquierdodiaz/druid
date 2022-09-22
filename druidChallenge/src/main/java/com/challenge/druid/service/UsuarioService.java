package com.challenge.druid.service;

import com.challenge.druid.dto.UsuarioDTO;
import com.challenge.druid.entity.Usuario;
import com.challenge.druid.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  public UsuarioDTO getUserByEmail(String email) {
    var usuario = usuarioRepository
            .findByEmail(email)
            .orElseThrow(EntityNotFoundException::new);

    return new UsuarioDTO(usuario);
  }

  public List<UsuarioDTO> getUserByBirthDaysDates(LocalDate fechaNacimientoInicial, LocalDate fechaNacimientoFinal) {
    var usuarios = usuarioRepository.getUsersBetweenDates(fechaNacimientoInicial, fechaNacimientoFinal);
    var listUsuariosDTOs = new ArrayList<UsuarioDTO>();
    if(!usuarios.isEmpty()){
      for(Usuario usu : usuarios){
        listUsuariosDTOs.add(new UsuarioDTO(usu));
      }
    }
    return listUsuariosDTOs;
  }

  public ResponseEntity removeUserById(Integer idUsuario) {
    if (!usuarioRepository.findById(idUsuario).isPresent()){
      return new ResponseEntity(new String("Usuario no se encuentra en la base de datos"), HttpStatus.NOT_FOUND);
    } else {
      usuarioRepository.deleteById(idUsuario);
      return new ResponseEntity(new String("Usuario eliminado"), HttpStatus.OK);
    }
  }

  public ResponseEntity<?>  createUser(Usuario usuario) {
    Usuario nuevoUsuario = new Usuario();
    if (Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears() >= 14) {
      if (usuarioRepository.findById(usuario.getId()).isPresent()) {
        return new ResponseEntity(new String("Usuario ya se encuentra en la base de datos"), HttpStatus.CREATED);
      } else {
        return new ResponseEntity(new UsuarioDTO(usuarioRepository.save(usuario)), HttpStatus.OK);
      }
    }
    return new ResponseEntity(new String("La edad debe ser mayor a 14 años"), HttpStatus.NOT_ACCEPTABLE);
  }
}
