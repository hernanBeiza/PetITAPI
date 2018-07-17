package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.entities.DuenoMascotaEntity;

import java.util.ArrayList;

public interface DuenoMascotaDAO {

    ArrayList<DuenoMascotaEntity> obtener();

    DuenoMascotaEntity obtenerConRut(DuenoMascotaDTO dto);
}
