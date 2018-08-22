package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.ProvinciaDTO;
import cl.petit.api.models.entities.ProvinciaEntity;
import cl.petit.api.persistence.daos.ProvinciaDAO;
import cl.petit.api.services.ProvinciaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class ProvinciaServiceIMP implements ProvinciaService {

    private static final Logger logger = LogManager.getLogger(ProvinciaServiceIMP.class);

    @Autowired
    private ProvinciaDAO provinciaDAO;

    @Override
    public ArrayList<ProvinciaDTO> obtener() {
        ArrayList<ProvinciaEntity> entities = this.provinciaDAO.obtener();
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Provincias encontradas");
                ArrayList<ProvinciaDTO> encontrados = new ArrayList<ProvinciaDTO>();
                for (ProvinciaEntity entity : entities) {
                    ProvinciaDTO dto = new ProvinciaDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.error("No se encontraron provincias");
            return null;
        }
    }

    @Override
    public ProvinciaDTO obtenerConID(Long idProvincia) {
        ProvinciaEntity entity = this.provinciaDAO.obtenerConID(idProvincia);
        if(entity!=null){
            return new ProvinciaDTO(entity);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ProvinciaDTO> obtenerConIDRegion(Long idRegion){
        ArrayList<ProvinciaEntity> entities = this.provinciaDAO.obtenerConIDRegion(idRegion);
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Provincias encontradas");
                ArrayList<ProvinciaDTO> encontrados = new ArrayList<ProvinciaDTO>();
                for (ProvinciaEntity entity : entities) {
                    ProvinciaDTO dto = new ProvinciaDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.error("No se encontraron provincias");
            return null;
        }
    }
}
