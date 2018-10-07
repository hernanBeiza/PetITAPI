package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.BloqueHorarioDTO;
import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.models.entities.BloqueHorarioEntity;
import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.persistence.daos.BloqueHorarioDAO;
import cl.petit.api.persistence.daos.ComunaDAO;
import cl.petit.api.services.BloqueHorarioService;
import cl.petit.api.services.ComunaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class BloqueHorarioServiceIMP implements BloqueHorarioService {

    private static final Logger logger = LogManager.getLogger(BloqueHorarioServiceIMP.class);

    @Autowired
    private BloqueHorarioDAO bloqueHorarioDAO;

    @Override
    public ArrayList<BloqueHorarioDTO> obtener() {
        ArrayList<BloqueHorarioEntity> entities = this.bloqueHorarioDAO.obtener();
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Bloques de Horarios encontrados");
                ArrayList<BloqueHorarioDTO> encontrados = new ArrayList<BloqueHorarioDTO>();
                for (BloqueHorarioEntity entity : entities) {
                    BloqueHorarioDTO dto = new BloqueHorarioDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.warn("No se encontraron bloques horarios");
            return null;
        }
    }

    @Override
    public BloqueHorarioDTO obtenerConID(BloqueHorarioDTO bloqueHorarioDTO) {
        BloqueHorarioEntity entity = this.bloqueHorarioDAO.obtenerConID(bloqueHorarioDTO);
        if(entity!=null){
            return new BloqueHorarioDTO(entity);
        } else {
            logger.warn("No se encontraron bloque horario con ese id");
            return null;
        }
    }

}
