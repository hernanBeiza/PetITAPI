package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.entities.*;
import cl.petit.api.persistence.daos.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;

@Repository("MascotaDAO")
public class MascotaDAOIMP implements MascotaDAO {

    private static final Logger logger = LogManager.getLogger(MascotaDAOIMP.class);

    /*
    @Autowired
    private TipoMascotaDAO tipoMascotaDAO;
    @Autowired
    private DuenoMascotaDAO duenoMascotaDAO;
    @Autowired
    private RazaDAO razaDAO;
    */

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

    //TODO leer esta página https://www.baeldung.com/jpa-pagination
    @Override
    public ArrayList<MascotaEntity> obtenerConPagina(Long pagina) {
        String queryString = "SELECT r FROM MascotaEntity AS r";
        logger.info(queryString);
        try {
            int pageSize = 10;
            Query query = entityManager.createQuery(queryString);
            query.setFirstResult((pagina.intValue()-1) * pageSize);
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
    public ArrayList<MascotaEntity> buscarPorNombre (MascotaDTO mascotaDTO){
        String query = "SELECT m FROM MascotaEntity AS m WHERE m.nombre  LIKE '%"+mascotaDTO.getNombre()+"%'";
        logger.info(query);
        try {
            return (ArrayList<MascotaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<MascotaEntity> obtenerConRutDueno (DuenoMascotaDTO duenoMascotaDTO){
        //TODO Revisar https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpql-inner-join.html
        //"SELECT DISTINCT e FROM Employee e INNER JOIN e.tasks t where t.supervisor='Denise'");
        String query = "SELECT m FROM MascotaEntity AS m INNER JOIN m.duenoMascotaEntity AS dm WHERE dm.rutDueno='"+duenoMascotaDTO.getRutDueno()+"'";
        logger.info(query);
        try {
            return (ArrayList<MascotaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public boolean guardar(MascotaDTO mascotaDTO) {
        MascotaEntity mascotaEntity = this.obtenerConRut(mascotaDTO);
        if(mascotaEntity==null) {
            mascotaEntity = new MascotaEntity();
            mascotaEntity.setRutMascota(mascotaDTO.getRutMascota());
            //TODO Manejar cuando no exista
            TipoMascotaEntity tipoMascotaEntity = new TipoMascotaEntity();//this.tipoMascotaDAO.obtenerConID(mascotaDTO.getTipoMascota());
            tipoMascotaEntity.setIdTipoMascota(mascotaDTO.getTipoMascota().getIdTipoMascota());
            //logger.info(tipoMascotaEntity.toString());
            mascotaEntity.setTipoMascota(tipoMascotaEntity);
            //TODO Manejar cuando no exista
            RazaEntity razaEntity = new RazaEntity();//this.razaDAO.obtenerConID(mascotaDTO.getRaza());
            razaEntity.setIdRaza(mascotaDTO.getRaza().getIdRaza());
            //logger.info(razaEntity.toString());
            mascotaEntity.setRazaEntity(razaEntity);
            //TODO Manejar cuando no exista
            DuenoMascotaEntity duenoMascotaEntity = new DuenoMascotaEntity();//this.duenoMascotaDAO.obtenerConRut(mascotaDTO.getDuenoMascota());
            //logger.info(duenoMascotaEntity.toString());
            duenoMascotaEntity.setRutDueno(mascotaDTO.getDuenoMascota().getRutDueno());
            mascotaEntity.setDuenoMascotaEntity(duenoMascotaEntity);

            mascotaEntity.setNombre(mascotaDTO.getNombre());
            mascotaEntity.setPeso(mascotaDTO.getPeso());
            mascotaEntity.setEdad(mascotaDTO.getEdad());
            mascotaEntity.setValid(mascotaDTO.getValid());
            try {
                entityManager.persist(mascotaEntity);
                return true;
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                return false;
            }
        } else {
            logger.warn("Mascota con rut " + mascotaDTO.getRutMascota() + " ya existe");
            return false;
        }
    }

    @Override
    public boolean editar(MascotaDTO mascotaDTO) {
        logger.info("editar();");
        MascotaEntity mascotaEntity = this.obtenerConRut(mascotaDTO);
        if(mascotaEntity!=null) {
            mascotaEntity.setRutMascota(mascotaDTO.getRutMascota());
            //TODO Manejar cuando no exista
            TipoMascotaEntity tipoMascotaEntity = new TipoMascotaEntity();//this.tipoMascotaDAO.obtenerConID(mascotaDTO.getTipoMascota());
            tipoMascotaEntity.setIdTipoMascota(mascotaDTO.getTipoMascota().getIdTipoMascota());
            //logger.info(tipoMascotaEntity.toString());
            mascotaEntity.setTipoMascota(tipoMascotaEntity);
            //TODO Manejar cuando no exista
            RazaEntity razaEntity = new RazaEntity();//this.razaDAO.obtenerConID(mascotaDTO.getRaza());
            razaEntity.setIdRaza(mascotaDTO.getRaza().getIdRaza());
            //logger.info(razaEntity.toString());
            mascotaEntity.setRazaEntity(razaEntity);
            //TODO Manejar cuando no exista
            DuenoMascotaEntity duenoMascotaEntity = new DuenoMascotaEntity();//this.duenoMascotaDAO.obtenerConRut(mascotaDTO.getDuenoMascota());
            //logger.info(duenoMascotaEntity.toString());
            duenoMascotaEntity.setRutDueno(mascotaDTO.getDuenoMascota().getRutDueno());
            mascotaEntity.setDuenoMascotaEntity(duenoMascotaEntity);

            mascotaEntity.setNombre(mascotaDTO.getNombre());
            mascotaEntity.setPeso(mascotaDTO.getPeso());
            mascotaEntity.setEdad(mascotaDTO.getEdad());
            mascotaEntity.setValid(mascotaDTO.getValid());
            try {
                entityManager.merge(mascotaEntity);
                return true;
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                return false;
            }
        } else {
            logger.warn("Mascota con rut " + mascotaDTO.getRutMascota() + " no existe");
            return false;
        }
    }

    @Override
    public boolean eliminar(MascotaDTO mascotaDTO) {
        MascotaEntity mascotaEntity = this.obtenerConRut(mascotaDTO);
        if(mascotaEntity!=null) {
            try{
                entityManager.remove(mascotaEntity);
                return true;
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                return false;
            }
        } else {
            logger.warn("Mascota con rut " + mascotaDTO.getRutMascota() + " no existe");
            return false;
        }

    }
}
