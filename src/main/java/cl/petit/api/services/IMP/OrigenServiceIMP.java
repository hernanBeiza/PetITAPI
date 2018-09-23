package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.models.dtos.OrigenDTO;
import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.OrigenEntity;
import cl.petit.api.persistence.daos.ComunaDAO;
import cl.petit.api.persistence.daos.IMP.NotificacionDAOIMP;
import cl.petit.api.persistence.daos.OrigenDAO;
import cl.petit.api.services.ComunaService;
import cl.petit.api.services.OrigenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class OrigenServiceIMP implements OrigenService {

    private static final Logger logger = LogManager.getLogger(OrigenServiceIMP.class);

    @Autowired
    private OrigenDAO origenDAO;

    @Override
    public ArrayList<OrigenDTO> obtener() {
        ArrayList<OrigenEntity> entities = this.origenDAO.obtener();
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Orígenes encontradas");
                ArrayList<OrigenDTO> encontrados = new ArrayList<OrigenDTO>();
                for (OrigenEntity entity : entities) {
                    OrigenDTO dto = new OrigenDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.error("No se encontraron orígenes");
            return null;
        }
    }

    @Override
    public OrigenDTO obtenerConID(Long idOrigen) {
        OrigenEntity entity = this.origenDAO.obtenerConID(idOrigen);
        if(entity!=null){
            return new OrigenDTO(entity);
        } else {
            return null;
        }
    }



}
