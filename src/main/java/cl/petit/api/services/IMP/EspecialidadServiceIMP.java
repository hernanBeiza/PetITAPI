package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.models.dtos.EspecialidadDTO;
import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.EspecialidadEntity;
import cl.petit.api.persistence.daos.EspecialidadDAO;
import cl.petit.api.services.EspecialidadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class EspecialidadServiceIMP implements EspecialidadService {

    private static final Logger logger = LogManager.getLogger(EspecialidadServiceIMP.class);

    @Autowired
    private EspecialidadDAO especialidadDAO;

    @Override
    public ArrayList<EspecialidadDTO> obtener() {
        ArrayList<EspecialidadEntity> entities = this.especialidadDAO.obtener();
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Especialidades encontradas");
                ArrayList<EspecialidadDTO> encontrados = new ArrayList<EspecialidadDTO>();
                for (EspecialidadEntity entity : entities) {
                    EspecialidadDTO dto = new EspecialidadDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.error("No se encontraron especialidades");
            return null;
        }
    }

    @Override
    public EspecialidadDTO obtenerConID(Long idEspecialidad) {
        EspecialidadEntity entity = this.especialidadDAO.obtenerConID(idEspecialidad);
        if(entity!=null){
            return new EspecialidadDTO(entity);
        } else {
            return null;
        }
    }



}
