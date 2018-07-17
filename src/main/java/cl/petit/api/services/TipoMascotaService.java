package cl.petit.api.services;

import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.dtos.TipoMascotaDTO;

import java.util.ArrayList;

public interface TipoMascotaService {

    ArrayList<TipoMascotaDTO> obtener();

    TipoMascotaDTO obtenerConID(TipoMascotaDTO model);
}
