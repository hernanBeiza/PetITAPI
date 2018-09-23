package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.OrigenEntity;
import cl.petit.api.persistence.daos.ComunaDAO;
import cl.petit.api.persistence.daos.OrigenDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("OrigenDAO")
public class OrigenDAOIMP implements OrigenDAO {

    private static final Logger logger = LogManager.getLogger(OrigenDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<OrigenEntity> obtener() {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT r FROM OrigenEntity AS r";
        logger.info(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<OrigenEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public OrigenEntity obtenerConID(Long idOrigen) {
        String query = "SELECT r FROM OrigenEntity AS r WHERE r.idOrigen = "+idOrigen;
        logger.info(query);
        try {
            return (OrigenEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }


}
