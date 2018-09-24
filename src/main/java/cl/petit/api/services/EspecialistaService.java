package cl.petit.api.services;

import cl.petit.api.models.dtos.EspecialistaDTO;

import java.util.ArrayList;

public interface EspecialistaService {

    ArrayList<EspecialistaDTO> obtener();

    EspecialistaDTO obtenerConID(Long idEspecialista);

    ArrayList<EspecialistaDTO> obtenerConIDEspecialidad(Long idEspecialidad);

}
