package cl.petit.api.persistence.daos;

import cl.petit.api.models.entities.EspecialistaEntity;

import java.util.ArrayList;

public interface EspecialistaDAO {

    ArrayList<EspecialistaEntity> obtener();

    EspecialistaEntity obtenerConID(Long idEspecialista);

    ArrayList<EspecialistaEntity> obtenerConIDEspecialidad(Long idEspecialidad);

}
