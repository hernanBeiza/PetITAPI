package cl.petit.api.persistence.daos;

import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.RolEntity;

import java.util.ArrayList;

public interface ComunaDAO {

    ArrayList<ComunaEntity> obtener();

    ComunaEntity obtenerConID(Long idComuna);

    ArrayList<ComunaEntity> obtenerConIDProvincia(Long idProvincia);
}
