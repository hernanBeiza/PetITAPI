package cl.petit.api.services;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import java.util.ArrayList;

public interface MascotaService {

    ArrayList<MascotaDTO> obtener();

    ArrayList<MascotaDTO> obtenerConPagina(Integer pagina);

    MascotaDTO obtenerConRut(MascotaDTO mascotaDTO);

    ArrayList<MascotaDTO> obtenerConRutDueno(DuenoMascotaDTO duenoMascotaDTO);
}
