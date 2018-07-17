package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.RolEntity;
import cl.petit.api.models.entities.UsuarioEntity;

import java.util.ArrayList;

public interface RolDAO {

    ArrayList<RolEntity> obtener();

    RolEntity obtenerConID(Long idRol);
}
