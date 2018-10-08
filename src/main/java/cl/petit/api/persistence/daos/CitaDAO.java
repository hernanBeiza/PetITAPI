package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.CitaDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.entities.CitaEntity;
import cl.petit.api.models.entities.OrigenEntity;

import java.util.ArrayList;

public interface CitaDAO {

    ArrayList<CitaEntity> obtener();

    CitaEntity obtenerConID(CitaDTO citaDTO);

    CitaEntity obtenerConRutMascota(MascotaDTO mascotaDTO);

    CitaEntity obtenerConIDEspecialista(EspecialistaDTO especialistaDTO);

    CitaEntity cambiarEstado(CitaDTO citaDTO);

    CitaEntity tomar(CitaDTO citaDTO);

    CitaEntity liberar(CitaDTO citaDTO);

    boolean guardar(CitaDTO citaDTO);

    boolean editar(CitaDTO citaDTO);

    boolean eliminar(CitaDTO citaDTO);
}
