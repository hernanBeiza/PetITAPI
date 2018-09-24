package cl.petit.api.services;

import cl.petit.api.models.dtos.RazaDTO;
import cl.petit.api.models.dtos.TipoMascotaDTO;

import java.util.ArrayList;

public interface RazaService {

    ArrayList<RazaDTO> obtener();

    RazaDTO obtenerConID(RazaDTO razaDTO);
}
