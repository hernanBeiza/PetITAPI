package cl.petit.api.services;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.MascotaDTO;

import java.util.ArrayList;

public interface DuenoMascotaService {

    ArrayList<DuenoMascotaDTO> obtener();

    DuenoMascotaDTO obtenerConID(DuenoMascotaDTO model);

    DuenoMascotaDTO obtenerConRut(DuenoMascotaDTO model);
}
