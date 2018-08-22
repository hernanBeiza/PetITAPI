package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.entities.ProvinciaEntity;
import cl.petit.api.models.entities.RegionEntity;
import cl.petit.api.persistence.daos.ProvinciaDAO;
import cl.petit.api.persistence.daos.RegionDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("ProvinciaDAO")
public class ProvinciaDAOIMP implements ProvinciaDAO {

    private static final Logger logger = LogManager.getLogger(ProvinciaDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<ProvinciaEntity> obtener() {
        String query = "SELECT r FROM ProvinciaEntity AS r";
        logger.info(query);
        try {
            return (ArrayList<ProvinciaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ProvinciaEntity obtenerConID(Long idProvincia) {
        String query = "SELECT r FROM ProvinciaEntity AS r WHERE r.idProvincia = "+idProvincia;
        logger.info(query);
        try {
            return (ProvinciaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<ProvinciaEntity> obtenerConIDRegion(Long idRegion) {
        String query = "SELECT r FROM ProvinciaEntity AS r WHERE r.idRegion = "+idRegion;
        logger.info(query);
        try {
            return (ArrayList<ProvinciaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }



}
