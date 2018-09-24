package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.entities.MascotaEntity;

import java.util.ArrayList;

public interface MascotaDAO {

    ArrayList<MascotaEntity> obtener();

    ArrayList<MascotaEntity> obtenerConPagina(Integer pagina);

    MascotaEntity obtenerConRut(MascotaDTO mascotaDTO);

    ArrayList<MascotaEntity> obtenerConRutDueno(DuenoMascotaDTO duenoMascotaDTO);
}
