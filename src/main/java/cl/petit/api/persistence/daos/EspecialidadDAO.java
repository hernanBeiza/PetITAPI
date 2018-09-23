package cl.petit.api.persistence.daos;

import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.EspecialidadEntity;

import java.util.ArrayList;

public interface EspecialidadDAO {

    ArrayList<EspecialidadEntity> obtener();

    EspecialidadEntity obtenerConID(Long idEspecialidad);

}
