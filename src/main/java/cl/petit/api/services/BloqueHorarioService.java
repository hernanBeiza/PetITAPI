package cl.petit.api.services;

import cl.petit.api.models.dtos.BloqueHorarioDTO;
import cl.petit.api.models.dtos.ComunaDTO;

import java.util.ArrayList;

public interface BloqueHorarioService {

    ArrayList<BloqueHorarioDTO> obtener();

    BloqueHorarioDTO obtenerConID(BloqueHorarioDTO bloqueHorarioDTO);
}
