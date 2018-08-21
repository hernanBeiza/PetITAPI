package cl.petit.api.services;

import cl.petit.api.models.dtos.RegionDTO;

import java.util.ArrayList;

public interface RegionService {

    ArrayList<RegionDTO> obtener();

    RegionDTO obtenerConID(Long idRegion);

}
