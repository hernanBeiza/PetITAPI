package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.entities.RegionEntity;
import cl.petit.api.persistence.daos.RegionDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("RegionDAO")
public class RegionDAOIMP implements RegionDAO {

    private static final Logger logger = LogManager.getLogger(NotificacionDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<RegionEntity> obtener() {
        String query = "SELECT r FROM RegionEntity AS r";
        logger.info(query);
        try {
            return (ArrayList<RegionEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public RegionEntity obtenerConID(Long idRegion) {
        String query = "SELECT r FROM RegionEntity AS r WHERE r.idRegion = "+idRegion;
        logger.info(query);
        try {
            return (RegionEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

}
