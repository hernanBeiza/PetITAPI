package cl.petit.api.services;

import cl.petit.api.models.dtos.ComunaDTO;

import java.util.ArrayList;

public interface ComunaService {

    ArrayList<ComunaDTO> obtener();

    ComunaDTO obtenerConID(Long idComuna);
}
