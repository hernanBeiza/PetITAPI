package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.EspecialidadDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.models.entities.EspecialidadEntity;
import cl.petit.api.models.entities.EspecialistaEntity;
import cl.petit.api.persistence.daos.EspecialidadDAO;
import cl.petit.api.persistence.daos.EspecialistaDAO;
import cl.petit.api.services.EspecialidadService;
import cl.petit.api.services.EspecialistaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class EspecialistaServiceIMP implements EspecialistaService {

    private static final Logger logger = LogManager.getLogger(EspecialistaServiceIMP.class);

    @Autowired
    private EspecialistaDAO especialistaDAO;

    @Override
    public ArrayList<EspecialistaDTO> obtener() {
        ArrayList<EspecialistaEntity> entities = this.especialistaDAO.obtener();
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Especialistas encontrados");
                ArrayList<EspecialistaDTO> encontrados = new ArrayList<EspecialistaDTO>();
                for (EspecialistaEntity entity : entities) {
                    EspecialistaDTO dto = new EspecialistaDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.error("No se encontraron especialistas");
            return null;
        }
    }

    @Override
    public EspecialistaDTO obtenerConID(Long idEspecialista) {
        EspecialistaEntity entity = this.especialistaDAO.obtenerConID(idEspecialista);
        if(entity!=null){
            return new EspecialistaDTO(entity);
        } else {
            return null;
        }
    }


    @Override
    public ArrayList<EspecialistaDTO> obtenerConIDEspecialidad(Long idEspecialidad) {
        ArrayList<EspecialistaEntity> entities = this.especialistaDAO.obtenerConIDEspecialidad(idEspecialidad);
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Especialistas encontrados con idEspecialidad " + idEspecialidad);
                ArrayList<EspecialistaDTO> encontrados = new ArrayList<EspecialistaDTO>();
                for (EspecialistaEntity entity : entities) {
                    EspecialistaDTO dto = new EspecialistaDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.error("No se encontraron especialistas con idEspecialidad " + idEspecialidad);
            return null;
        }
    }
}
