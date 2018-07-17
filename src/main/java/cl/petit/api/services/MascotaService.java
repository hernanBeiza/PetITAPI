package cl.petit.api.services;

import cl.petit.api.models.dtos.MascotaDTO;
import java.util.ArrayList;

public interface MascotaService {

    ArrayList<MascotaDTO> obtener();

    MascotaDTO obtenerConID(MascotaDTO model);
}
