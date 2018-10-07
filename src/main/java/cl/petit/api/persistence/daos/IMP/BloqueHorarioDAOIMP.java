package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.BloqueHorarioDTO;
import cl.petit.api.models.entities.BloqueHorarioEntity;
import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.persistence.daos.BloqueHorarioDAO;
import cl.petit.api.persistence.daos.ComunaDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("BloqueHorarioDAO")
public class BloqueHorarioDAOIMP implements BloqueHorarioDAO {

    private static final Logger logger = LogManager.getLogger(BloqueHorarioDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<BloqueHorarioEntity> obtener() {
        String query = "SELECT b FROM BloqueHorarioEntity AS b";
        logger.info(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<BloqueHorarioEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public BloqueHorarioEntity obtenerConID(BloqueHorarioDTO bloqueHorarioDTO) {
        String query = "SELECT b FROM BloqueHorarioEntity AS b WHERE b.idBloqueHorario = "+bloqueHorarioDTO.getIdBloqueHorario();
        logger.info(query);
        try {
            return (BloqueHorarioEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

}
