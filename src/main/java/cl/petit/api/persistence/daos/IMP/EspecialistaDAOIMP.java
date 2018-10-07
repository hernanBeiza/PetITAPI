package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.entities.EspecialistaEntity;
import cl.petit.api.persistence.daos.EspecialistaDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("EspecialistaDAO")
public class EspecialistaDAOIMP implements EspecialistaDAO {

    private static final Logger logger = LogManager.getLogger(EspecialistaDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public ArrayList<EspecialistaEntity> obtener() {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT e FROM EspecialistaEntity AS e";
        logger.info(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<EspecialistaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public EspecialistaEntity obtenerConID(Long idEspecialista) {
        String query = "SELECT e FROM EspecialistaEntity AS e WHERE e.idEspecialista = "+idEspecialista;
        logger.info(query);
        try {
            return (EspecialistaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<EspecialistaEntity> obtenerConIDEspecialidad(Long idEspecialidad) {
        String query = "SELECT e FROM EspecialistaEntity AS e WHERE e.especialidad.idEspecialidad = "+idEspecialidad;
        logger.info(query);
        try {
            return (ArrayList<EspecialistaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }


}
