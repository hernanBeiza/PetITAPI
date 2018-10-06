package cl.petit.api.services;

import cl.petit.api.models.dtos.BloqueHorarioDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.models.dtos.EspecialistaDisponibilidadDTO;

import java.util.ArrayList;

public interface EspecialistaDisponibilidadService {

    ArrayList<EspecialistaDisponibilidadDTO> obtener();

    EspecialistaDisponibilidadDTO obtenerConID(EspecialistaDisponibilidadDTO especialistaDisponibilidadDTO);

    ArrayList<EspecialistaDisponibilidadDTO> obtenerConIDEspecialista(EspecialistaDTO especialistaDTO);

    ArrayList<EspecialistaDisponibilidadDTO> obtenerConFecha(String fecha);

    ArrayList<EspecialistaDisponibilidadDTO> obtenerConIDEpecialistaYFecha(EspecialistaDTO especialistaDTO,String fecha);

}