package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.CitaDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.entities.CitaEntity;
import cl.petit.api.models.entities.OrigenEntity;
import cl.petit.api.persistence.daos.CitaDAO;
import cl.petit.api.persistence.daos.OrigenDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("CitaDAO")
public class CitaDAOIMP implements CitaDAO {

    private static final Logger logger = LogManager.getLogger(CitaDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<CitaEntity> obtener() {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT r FROM CitaEntity AS r";
        logger.info(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<CitaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public CitaEntity obtenerConID(CitaDTO citaDTO) {
        String query = "SELECT r FROM CitaEntity AS r WHERE r.idCita = "+citaDTO.getIdCita();
        logger.info(query);
        try {
            return (CitaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public CitaEntity obtenerConRutMascota(MascotaDTO mascotaDTO) {
        String query = "SELECT c FROM CitaEntity AS c INNER JOIN c.mascotaEntity AS m WHERE m.rutMascota='"+mascotaDTO.getRutMascota()+"'";
        logger.info(query);
        try {
            return (CitaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public CitaEntity obtenerConIDEspecialista(EspecialistaDTO especialistaDTO) {
        String query = "SELECT c FROM CitaEntity AS c INNER JOIN c.especialistaEntity AS e WHERE e.idEspecialista = "+especialistaDTO.getIdEspecialista();
        logger.info(query);
        try {
            return (CitaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }
}
