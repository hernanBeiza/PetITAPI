package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.TipoMascotaDTO;
import cl.petit.api.models.entities.TipoMascotaEntity;
import cl.petit.api.persistence.daos.TipoMascotaDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("TipoMascotaDAO")
public class TipoMascotaDAOIMP implements TipoMascotaDAO {

    private static final Logger logger = LogManager.getLogger(TipoMascotaDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public ArrayList<TipoMascotaEntity> obtener() {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT t FROM TipoMascotaEntity AS t";
        logger.info(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<TipoMascotaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public TipoMascotaEntity obtenerConID(TipoMascotaDTO dto) {
        String query = "SELECT r FROM TipoMascotaEntity AS r WHERE r.idTipoMascota = "+dto.getIdTipoMascota();
        logger.info(query);
        try {
            return (TipoMascotaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

}
