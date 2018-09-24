package cl.petit.api.services;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.MascotaDTO;

import java.util.ArrayList;

public interface DuenoMascotaService {

    ArrayList<DuenoMascotaDTO> obtener();

    DuenoMascotaDTO obtenerConRut(DuenoMascotaDTO duenoMascotaDTO);

    ArrayList<DuenoMascotaDTO> buscarPorNombre(DuenoMascotaDTO duenoMascotaDTO);

    boolean guardar(DuenoMascotaDTO duenoMascotaDTO);

    boolean editar(DuenoMascotaDTO duenoMascotaDTO);

    boolean eliminar(DuenoMascotaDTO duenoMascotaDTO);
}
