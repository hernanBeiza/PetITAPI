package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.CitaDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.dtos.OrigenDTO;
import cl.petit.api.models.entities.*;
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
        String query = "SELECT c FROM CitaEntity AS c";
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
        String query = "SELECT c FROM CitaEntity AS c WHERE c.idCita = "+citaDTO.getIdCita();
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

    @Override
    public CitaEntity cambiarEstado(CitaDTO citaDTO) {
        CitaEntity citaEntity = this.obtenerConID(citaDTO);
        if(citaEntity.getValid()==1){
            citaEntity.setValid(2);
        } else {
            citaEntity.setValid(1);
        }

        try {
            entityManager.merge(citaEntity);
            return this.obtenerConID(citaDTO);
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public CitaEntity tomar(CitaDTO citaDTO) {
        CitaEntity citaEntity = this.obtenerConID(citaDTO);
        citaEntity.setValid(citaDTO.getValid());
        try {
            entityManager.merge(citaEntity);
            return this.obtenerConID(citaDTO);
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public CitaEntity liberar(CitaDTO citaDTO) {
        CitaEntity citaEntity = this.obtenerConID(citaDTO);
        citaEntity.setValid(citaDTO.getValid());
        try {
            entityManager.merge(citaEntity);
            return this.obtenerConID(citaDTO);
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public boolean guardar(CitaDTO citaDTO) {
        CitaEntity citaEntity = new CitaEntity();

        EspecialistaEntity especialistaEntity = new EspecialistaEntity();
        especialistaEntity.setIdEspecialista(citaDTO.getEspecialistaDTO().getIdEspecialista());
        citaEntity.setEspecialistaEntity(especialistaEntity);

        MascotaEntity mascotaEntity = new MascotaEntity();
        mascotaEntity.setRutMascota(citaDTO.getMascotaDTO().getRutMascota());
        citaEntity.setMascotaEntity(mascotaEntity);

        OrigenEntity origenEntity = new OrigenEntity();
        origenEntity.setIdOrigen(citaDTO.getOrigenDTO().getIdOrigen());
        citaEntity.setOrigenEntity(origenEntity);

        citaEntity.setValid(citaDTO.getValid());
        try {
            entityManager.persist(citaEntity);
            return true;
        } catch (Exception ex){
            logger.error("No se pudo guardar la cita");
            logger.error(ex.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean editar(CitaDTO citaDTO) {

        CitaEntity citaEntityEncontrada = this.obtenerConID(citaDTO);
        if (citaEntityEncontrada!=null) {

            EspecialistaEntity especialistaEntity = new EspecialistaEntity();
            especialistaEntity.setIdEspecialista(citaDTO.getEspecialistaDTO().getIdEspecialista());
            citaEntityEncontrada.setEspecialistaEntity(especialistaEntity);

            MascotaEntity mascotaEntity = new MascotaEntity();
            mascotaEntity.setRutMascota(citaDTO.getMascotaDTO().getRutMascota());
            citaEntityEncontrada.setMascotaEntity(mascotaEntity);

            OrigenEntity origenEntity = new OrigenEntity();
            origenEntity.setIdOrigen(citaDTO.getOrigenDTO().getIdOrigen());
            citaEntityEncontrada.setOrigenEntity(origenEntity);

            citaEntityEncontrada.setValid(citaDTO.getValid());

            try {
                entityManager.merge(citaEntityEncontrada);
                return true;
            } catch (Exception ex){
                logger.error("No se pudo editar la cita");
                logger.error(ex.getLocalizedMessage());
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public boolean eliminar(CitaDTO citaDTO) {
        CitaEntity citaEntityEncontrada = this.obtenerConID(citaDTO);
        if (citaEntityEncontrada!=null) {
            try {
                entityManager.remove(citaEntityEncontrada);
                return true;
            } catch (Exception ex){
                logger.error("No se pudo eliminar la cita");
                logger.error(ex.getLocalizedMessage());
                return false;
            }
        } else {
            return false;
        }
    }
}
