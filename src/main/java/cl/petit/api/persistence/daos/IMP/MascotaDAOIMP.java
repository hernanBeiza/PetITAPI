package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.entities.DuenoMascotaEntity;
import cl.petit.api.models.entities.MascotaEntity;
import cl.petit.api.models.entities.RolEntity;
import cl.petit.api.persistence.daos.MascotaDAO;
import cl.petit.api.persistence.daos.RolDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;

@Repository("MascotaDAO")
public class MascotaDAOIMP implements MascotaDAO {

    private static final Logger logger = LogManager.getLogger(MascotaDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<MascotaEntity> obtener() {
        String query = "SELECT r FROM MascotaEntity AS r";
        logger.info(query);
        try {
            return (ArrayList<MascotaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<MascotaEntity> obtenerConPagina(Integer pagina) {
        String queryString = "SELECT r FROM MascotaEntity AS r";
        logger.info(queryString);
        try {
            int pageSize = 10;
            Query query = entityManager.createQuery(queryString);
            query.setFirstResult((pagina-1) * pageSize);
            query.setMaxResults(pageSize);
            return (ArrayList<MascotaEntity>) query.getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public MascotaEntity obtenerConRut(MascotaDTO mascotaDTO) {
        String query = "SELECT m FROM MascotaEntity AS m WHERE m.rutMascota = '" + mascotaDTO.getRutMascota()+"'";
        logger.info(query);
        try {
            return (MascotaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<MascotaEntity> obtenerConRutDueno (DuenoMascotaDTO duenoMascotaDTO){
        //https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpql-inner-join.html
        //"SELECT DISTINCT e FROM Employee e INNER JOIN e.tasks t where t.supervisor='Denise'");
        String query = "SELECT m FROM MascotaEntity AS m INNER JOIN m.duenoMascota AS dm WHERE dm.rutDueno='"+duenoMascotaDTO.getRutDueno()+"'";
        logger.info(query);
        try {
            return (ArrayList<MascotaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

}
