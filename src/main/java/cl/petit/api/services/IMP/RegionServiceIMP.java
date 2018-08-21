package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.models.dtos.RegionDTO;
import cl.petit.api.models.entities.RegionEntity;
import cl.petit.api.persistence.daos.IMP.NotificacionDAOIMP;
import cl.petit.api.persistence.daos.RegionDAO;
import cl.petit.api.services.RegionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class RegionServiceIMP implements RegionService {

    private static final Logger logger = LogManager.getLogger(NotificacionDAOIMP.class);

    @Autowired
    private RegionDAO regionDAO;

    @Override
    public ArrayList<RegionDTO> obtener() {
        ArrayList<RegionEntity> entities = this.regionDAO.obtener();
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Regiones encontradas");
                ArrayList<RegionDTO> encontrados = new ArrayList<RegionDTO>();
                for (RegionEntity entity : entities) {
                    RegionDTO dto = new RegionDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.error("No se encontraron regiones");
            return null;
        }
    }

    @Override
    public RegionDTO obtenerConID(Long idRegion) {
        RegionEntity entity = this.regionDAO.obtenerConID(idRegion);
        if(entity!=null){
            return new RegionDTO(entity);
        } else {
            return null;
        }
    }

}
