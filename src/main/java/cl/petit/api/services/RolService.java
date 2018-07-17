package cl.petit.api.services;

import cl.petit.api.models.dtos.RolDTO;

import java.util.ArrayList;

public interface RolService {

    ArrayList<RolDTO> obtener();

    RolDTO obtenerConID(Long idRol);
}
