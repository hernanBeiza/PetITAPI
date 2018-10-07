package cl.petit.api.services;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import java.util.ArrayList;

public interface MascotaService {

    ArrayList<MascotaDTO> obtener();

    ArrayList<MascotaDTO> obtenerConPagina(Long pagina);

    MascotaDTO obtenerConRut(MascotaDTO mascotaDTO);

    ArrayList<MascotaDTO> obtenerConRutDueno(DuenoMascotaDTO duenoMascotaDTO);

    ArrayList<MascotaDTO> buscarPorNombre(MascotaDTO mascotaDTO);

    boolean guardar(MascotaDTO mascotaDTO);

    boolean editar(MascotaDTO mascotaDTO);

    boolean eliminar(MascotaDTO mascotaDTO);
}
