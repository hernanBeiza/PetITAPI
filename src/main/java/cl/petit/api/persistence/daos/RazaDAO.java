package cl.petit.api.persistence.daos;

import cl.petit.api.models.entities.RazaEntity;
import cl.petit.api.models.entities.RolEntity;

import java.util.ArrayList;

public interface RazaDAO {

    ArrayList<RazaEntity> obtener();

    RazaEntity obtenerConID(Long idRol);
}
