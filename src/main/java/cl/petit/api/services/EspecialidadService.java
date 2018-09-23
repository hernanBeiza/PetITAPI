package cl.petit.api.services;

import cl.petit.api.models.dtos.EspecialidadDTO;

import java.util.ArrayList;

public interface EspecialidadService {

    ArrayList<EspecialidadDTO> obtener();

    EspecialidadDTO obtenerConID(Long idEspecialidad);

}
