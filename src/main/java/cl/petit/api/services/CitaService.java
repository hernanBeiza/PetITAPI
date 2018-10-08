package cl.petit.api.services;

import cl.petit.api.models.dtos.CitaDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.models.dtos.MascotaDTO;

import java.util.ArrayList;

public interface CitaService {

    ArrayList<CitaDTO> obtener();

    CitaDTO obtenerConID(CitaDTO citaDTO);

    CitaDTO obtenerConRutMascota(MascotaDTO mascotaDTO);

    CitaDTO obtenerConIDEspecialista(EspecialistaDTO especialistaDTO);

    CitaDTO cambiarEstado(CitaDTO citaDTO);
    boolean guardar(CitaDTO citaDTO);
    boolean editar(CitaDTO citaDTO);
    boolean eliminar(CitaDTO citaDTO);
}
