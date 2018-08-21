package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.RazaEntity;
import cl.petit.api.persistence.daos.ComunaDAO;
import cl.petit.api.persistence.daos.RazaDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("ComunaDAO")
public class ComunaDAOIMP implements ComunaDAO {

    private static final Logger logger = LogManager.getLogger(NotificacionDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<ComunaEntity> obtener() {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT r FROM ComunaEntity AS r";
        logger.info(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<ComunaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ComunaEntity obtenerConID(Long idComuna) {
        String query = "SELECT r FROM ComunaEntity AS r WHERE r.idComuna = "+idComuna;
        logger.info(query);
        try {
            return (ComunaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<ComunaEntity> obtenerConIDProvincia(Long idProvincia) {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT r FROM ComunaEntity AS r WHERE idProvincia = "+idProvincia;
        logger.info(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<ComunaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

}
