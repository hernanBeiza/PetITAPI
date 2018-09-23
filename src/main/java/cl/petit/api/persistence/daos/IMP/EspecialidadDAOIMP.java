package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.entities.EspecialidadEntity;
import cl.petit.api.persistence.daos.EspecialidadDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("EspecialidadDAO")
public class EspecialidadDAOIMP implements EspecialidadDAO {

    private static final Logger logger = LogManager.getLogger(EspecialidadDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<EspecialidadEntity> obtener() {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT r FROM EspecialidadEntity AS r";
        logger.info(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<EspecialidadEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public EspecialidadEntity obtenerConID(Long idEspecialidad) {
        String query = "SELECT r FROM EspecialidadEntity AS r WHERE r.idEspecialidad = "+idEspecialidad;
        logger.info(query);
        try {
            return (EspecialidadEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }


}
