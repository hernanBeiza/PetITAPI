package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.BloqueHorarioDTO;
import cl.petit.api.models.entities.BloqueHorarioEntity;

import java.util.ArrayList;

public interface BloqueHorarioDAO {

    ArrayList<BloqueHorarioEntity> obtener();

    BloqueHorarioEntity obtenerConID(BloqueHorarioDTO bloqueHorarioDTO);

}
