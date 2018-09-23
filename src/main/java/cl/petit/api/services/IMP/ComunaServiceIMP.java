package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.models.dtos.RazaDTO;
import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.RazaEntity;
import cl.petit.api.persistence.daos.ComunaDAO;
import cl.petit.api.persistence.daos.IMP.NotificacionDAOIMP;
import cl.petit.api.persistence.daos.RazaDAO;
import cl.petit.api.services.ComunaService;
import cl.petit.api.services.RazaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class ComunaServiceIMP implements ComunaService {

    private static final Logger logger = LogManager.getLogger(ComunaServiceIMP.class);

    @Autowired
    private ComunaDAO comunaDAO;

    @Override
    public ArrayList<ComunaDTO> obtener() {
        ArrayList<ComunaEntity> entities = this.comunaDAO.obtener();
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Comunas encontradas");
                ArrayList<ComunaDTO> encontrados = new ArrayList<ComunaDTO>();
                for (ComunaEntity entity : entities) {
                    ComunaDTO dto = new ComunaDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.error("No se encontraron comunas");
            return null;
        }
    }

    @Override
    public ComunaDTO obtenerConID(Long idComuna) {
        ComunaEntity entity = this.comunaDAO.obtenerConID(idComuna);
        if(entity!=null){
            return new ComunaDTO(entity);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ComunaDTO> obtenerConIDProvincia(Long idProvincia) {
        ArrayList<ComunaEntity> entities = this.comunaDAO.obtenerConIDProvincia(idProvincia);
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Comunas encontradas con idProvincia " + idProvincia);
                ArrayList<ComunaDTO> encontrados = new ArrayList<ComunaDTO>();
                for (ComunaEntity entity : entities) {
                    ComunaDTO dto = new ComunaDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.error("No se encontraron comunas");
            return null;
        }
    }


}
