package cl.petit.api.persistence.daos;

import cl.petit.api.models.entities.RegionEntity;

import java.util.ArrayList;

public interface RegionDAO {

    ArrayList<RegionEntity> obtener();

    RegionEntity obtenerConID(Long idRegion);

}
