package cl.petit.api.services;

import cl.petit.api.models.dtos.OrigenDTO;

import java.util.ArrayList;

public interface OrigenService {

    ArrayList<OrigenDTO> obtener();

    OrigenDTO obtenerConID(Long idOrigen);

}
