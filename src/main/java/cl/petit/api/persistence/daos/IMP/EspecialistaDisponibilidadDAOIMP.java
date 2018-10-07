package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.BloqueHorarioDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.models.dtos.EspecialistaDisponibilidadDTO;
import cl.petit.api.models.entities.BloqueHorarioEntity;
import cl.petit.api.models.entities.EspecialistaDisponibilidadEntity;
import cl.petit.api.models.entities.EspecialistaEntity;
import cl.petit.api.persistence.daos.BloqueHorarioDAO;
import cl.petit.api.persistence.daos.EspecialistaDisponibilidadDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("EspecialistaDisponibilidadDAO")
public class EspecialistaDisponibilidadDAOIMP implements EspecialistaDisponibilidadDAO {

    private static final Logger logger = LogManager.getLogger(EspecialistaDisponibilidadDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean guardar(EspecialistaDisponibilidadDTO especialistaDisponibilidadDTO) {
        EspecialistaDisponibilidadEntity especialistaDisponibilidadEntity = new EspecialistaDisponibilidadEntity();
        especialistaDisponibilidadEntity.setIdEspecialista(especialistaDisponibilidadDTO.getIdEspecialista());

        BloqueHorarioEntity bloqueHorarioEntity = new BloqueHorarioEntity();
        bloqueHorarioEntity.setIdBloqueHorario(especialistaDisponibilidadDTO.getBloqueHorarioDTO().getIdBloqueHorario());
        especialistaDisponibilidadEntity.setBloqueHorarioEntity(bloqueHorarioEntity);

        especialistaDisponibilidadEntity.setFecha(especialistaDisponibilidadDTO.getFecha());
        especialistaDisponibilidadEntity.setValid(especialistaDisponibilidadDTO.getValid());
        try {
            entityManager.persist(especialistaDisponibilidadEntity);
            return true;
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return false;
        }
    }


    @Override
    public boolean editar(EspecialistaDisponibilidadDTO especialistaDisponibilidadDTO) {

        EspecialistaDisponibilidadEntity especialistaDisponibilidadEntity = obtenerConID(especialistaDisponibilidadDTO);
        if (especialistaDisponibilidadEntity!=null){
            especialistaDisponibilidadEntity.setIdEspecialista(especialistaDisponibilidadDTO.getIdEspecialista());

            BloqueHorarioEntity bloqueHorarioEntity = new BloqueHorarioEntity();
            bloqueHorarioEntity.setIdBloqueHorario(especialistaDisponibilidadDTO.getBloqueHorarioDTO().getIdBloqueHorario());
            especialistaDisponibilidadEntity.setBloqueHorarioEntity(bloqueHorarioEntity);

            especialistaDisponibilidadEntity.setFecha(especialistaDisponibilidadDTO.getFecha());
            especialistaDisponibilidadEntity.setValid(especialistaDisponibilidadDTO.getValid());

            try {
                entityManager.merge(especialistaDisponibilidadEntity);
                return true;
            } catch (Exception e){
                logger.error(e.getLocalizedMessage());
                return false;
            }

        } else {

            return false;
        }
    }

    @Override
    public boolean eliminar(EspecialistaDisponibilidadDTO especialistaDisponibilidadDTO) {
        EspecialistaDisponibilidadEntity especialistaDisponibilidadEntity = obtenerConID(especialistaDisponibilidadDTO);
        especialistaDisponibilidadEntity.setValid(especialistaDisponibilidadDTO.getValid());
        if(especialistaDisponibilidadEntity!=null){
            try {

                //entityManager.remove(especialistaDisponibilidadEntity);
                entityManager.merge(especialistaDisponibilidadEntity);
                return true;
            } catch (Exception e){
                logger.error(e.getLocalizedMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<EspecialistaDisponibilidadEntity> obtener() {
        logger.info("obtener();");
        String query = "SELECT e FROM EspecialistaDisponibilidadEntity AS e";
        logger.info(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<EspecialistaDisponibilidadEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public EspecialistaDisponibilidadEntity obtenerConID(EspecialistaDisponibilidadDTO especialistaDisponibilidadDTO) {
        logger.info("obtenerConID();");
        String query = "SELECT e FROM EspecialistaDisponibilidadEntity AS e WHERE e.idEspecialistaDisponibilidad = "+especialistaDisponibilidadDTO.getIdEspecialistaDisponibilidad();
        logger.info(query);
        try {
            return (EspecialistaDisponibilidadEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<EspecialistaDisponibilidadEntity> obtenerConIDEspecialista(EspecialistaDTO especialistaDTO) {
        logger.info("obtenerConIDEspecialista");
        String query = "SELECT e FROM EspecialistaDisponibilidadEntity AS e WHERE e.idEspecialista = "+especialistaDTO.getIdEspecialista();
        logger.info(query);
        try {
            return (ArrayList<EspecialistaDisponibilidadEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<EspecialistaDisponibilidadEntity> obtenerConFecha(String fecha) {
        logger.info("obtenerConFecha");
        //String query = "SELECT r FROM EspecialistaDisponibilidadEntity AS r WHERE r.fecha = '"+especialistaDisponibilidadDTO.getFecha()+"' AND r.valid = 1";
        String query = "SELECT e FROM EspecialistaDisponibilidadEntity AS e WHERE e.fecha = '"+fecha+"'";
        logger.info(query);
        try {
            return (ArrayList<EspecialistaDisponibilidadEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }


    @Override
    public ArrayList<EspecialistaDisponibilidadEntity> obtenerConIDEspecialistaYFecha(EspecialistaDTO especialistaDTO, String fecha) {
        logger.info("obtenerConIDEspecialistaYFecha");
        String query = "SELECT e FROM EspecialistaDisponibilidadEntity AS e WHERE e.idEspecialista = "+especialistaDTO.getIdEspecialista() + " AND e.fecha='"+fecha+"'";
        logger.info(query);
        try {
            return (ArrayList<EspecialistaDisponibilidadEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

}
