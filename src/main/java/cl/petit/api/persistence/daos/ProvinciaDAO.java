package cl.petit.api.persistence.daos;

import cl.petit.api.models.entities.ProvinciaEntity;

import java.util.ArrayList;

public interface ProvinciaDAO {

    ArrayList<ProvinciaEntity> obtener();

    ProvinciaEntity obtenerConID(Long idProvincia);


    ArrayList<ProvinciaEntity> obtenerConIDRegion(Long idRegion);

}
