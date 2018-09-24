package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.entities.DuenoMascotaEntity;

import java.util.ArrayList;

public interface DuenoMascotaDAO {

    ArrayList<DuenoMascotaEntity> obtener();

    DuenoMascotaEntity obtenerConRut(DuenoMascotaDTO duenoMascotaDTO);

    ArrayList<DuenoMascotaEntity> buscarPorNombre(DuenoMascotaDTO duenoMascotaDTO);

    boolean guardar(DuenoMascotaDTO duenoMascotaDTO);

    boolean editar(DuenoMascotaDTO duenoMascotaDTO);

    boolean eliminar(DuenoMascotaDTO duenoMascotaDTO);
}
