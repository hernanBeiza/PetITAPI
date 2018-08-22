package cl.petit.api.services;

import cl.petit.api.models.dtos.ProvinciaDTO;

import java.util.ArrayList;

public interface ProvinciaService {

    ArrayList<ProvinciaDTO> obtener();

    ProvinciaDTO obtenerConID(Long idProvincia);

    ArrayList<ProvinciaDTO> obtenerConIDRegion(Long idRegion);
}
