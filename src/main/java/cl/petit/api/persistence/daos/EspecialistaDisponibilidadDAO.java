package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.BloqueHorarioDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.models.dtos.EspecialistaDisponibilidadDTO;
import cl.petit.api.models.entities.BloqueHorarioEntity;
import cl.petit.api.models.entities.EspecialistaDisponibilidadEntity;

import java.util.ArrayList;

public interface EspecialistaDisponibilidadDAO {

    boolean guardar(EspecialistaDisponibilidadEntity especialistaDisponibilidadEntity);

    boolean editar(EspecialistaDisponibilidadEntity especialistaDisponibilidadEntity);

    boolean eliminar(EspecialistaDisponibilidadEntity especialistaDisponibilidadEntity);

    ArrayList<EspecialistaDisponibilidadEntity> obtener();

    EspecialistaDisponibilidadEntity obtenerConID(EspecialistaDisponibilidadDTO especialistaDisponibilidadDTO);

    ArrayList<EspecialistaDisponibilidadEntity> obtenerConIDEspecialista(EspecialistaDTO especialistaDTO);

    ArrayList<EspecialistaDisponibilidadEntity> obtenerConFecha(String fecha);

    ArrayList<EspecialistaDisponibilidadEntity> obtenerConIDEspecialistaYFecha(EspecialistaDTO especialistaDTO, String fecha);

}
