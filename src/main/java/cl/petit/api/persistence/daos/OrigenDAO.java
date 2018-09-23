package cl.petit.api.persistence.daos;

import cl.petit.api.models.entities.OrigenEntity;

import java.util.ArrayList;

public interface OrigenDAO {

    ArrayList<OrigenEntity> obtener();

    OrigenEntity obtenerConID(Long idOrigen);
}
