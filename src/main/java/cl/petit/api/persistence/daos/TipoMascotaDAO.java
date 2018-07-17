package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.dtos.TipoMascotaDTO;
import cl.petit.api.models.entities.RolEntity;
import cl.petit.api.models.entities.TipoMascotaEntity;

import java.util.ArrayList;

public interface TipoMascotaDAO {

    ArrayList<TipoMascotaEntity> obtener();

    TipoMascotaEntity obtenerConID(TipoMascotaDTO dto);
}
