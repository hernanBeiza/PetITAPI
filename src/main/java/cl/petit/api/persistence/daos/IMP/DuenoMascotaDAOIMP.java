package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.DuenoMascotaEntity;
import cl.petit.api.models.entities.NotificacionEntity;
import cl.petit.api.models.entities.UsuarioEntity;
import cl.petit.api.persistence.daos.DuenoMascotaDAO;
import cl.petit.api.persistence.daos.UsuarioDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository("DuenoMascotaDAO")
public class DuenoMascotaDAOIMP implements DuenoMascotaDAO {

    private static final Logger logger = LogManager.getLogger(DuenoMascotaDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<DuenoMascotaEntity> obtener() {
        logger.info("DuenoMascotaDAOIMP: obtener();");
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT dm FROM DuenoMascotaEntity AS dm";
        logger.info(query);
        try {
            return (ArrayList<DuenoMascotaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public DuenoMascotaEntity obtenerConRut(DuenoMascotaDTO duenoMascotaDTO) {
        logger.info("DuenoMascotaDAOIMP: obtenerConRut();");
        String query = "SELECT dm FROM DuenoMascotaEntity AS dm WHERE dm.rutDueno = '"+duenoMascotaDTO.getRutDueno()+"'";
        logger.info(query);
        try {
            return (DuenoMascotaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<DuenoMascotaEntity> buscarPorNombre(DuenoMascotaDTO duenoMascotaDTO) {
        logger.info("DuenoMascotaDAOIMP: obtenerConNombre();");
        String query = "SELECT dm FROM DuenoMascotaEntity AS dm WHERE dm.nombres LIKE '%"+duenoMascotaDTO.getNombres()+"%'";
        logger.info(query);
        try {
            return (ArrayList<DuenoMascotaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public boolean guardar(DuenoMascotaDTO duenoMascotaDTO) {
        logger.info("DuenoMascotaDAOIMP: guardar();");
        DuenoMascotaEntity duenoMascotaEntity = new DuenoMascotaEntity();

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setIdUsuario(duenoMascotaDTO.getUsuario().getIdUsuario());
        duenoMascotaEntity.setUsuarioEntity(usuarioEntity);

        ComunaEntity comunaEntity = new ComunaEntity();
        comunaEntity.setIdComuna(duenoMascotaDTO.getComuna().getIdComuna());
        duenoMascotaEntity.setComuna(comunaEntity);

        duenoMascotaEntity.setRutDueno(duenoMascotaDTO.getRutDueno());
        duenoMascotaEntity.setNombres(duenoMascotaDTO.getNombres());
        duenoMascotaEntity.setApellidoPaterno(duenoMascotaDTO.getApellidoPaterno());
        duenoMascotaEntity.setApellidoMaterno(duenoMascotaDTO.getApellidoMaterno());

        duenoMascotaEntity.setDireccion(duenoMascotaDTO.getDireccion());
        duenoMascotaEntity.setTelefono(duenoMascotaDTO.getTelefono());
        duenoMascotaEntity.setCorreo(duenoMascotaDTO.getCorreo());
        duenoMascotaEntity.setValid(duenoMascotaDTO.getValid());

        logger.info(duenoMascotaEntity.toString());
        try {
            entityManager.persist(duenoMascotaEntity);
            return true;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean editar(DuenoMascotaDTO duenoMascotaDTO) {
        logger.info("DuenoMascotaDAOIMP: editar();");
        DuenoMascotaEntity duenoMascotaEntity = new DuenoMascotaEntity();

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setIdUsuario(duenoMascotaDTO.getUsuario().getIdUsuario());
        duenoMascotaEntity.setUsuarioEntity(usuarioEntity);

        ComunaEntity comunaEntity = new ComunaEntity();
        comunaEntity.setIdComuna(duenoMascotaDTO.getComuna().getIdComuna());
        duenoMascotaEntity.setComuna(comunaEntity);

        duenoMascotaEntity.setRutDueno(duenoMascotaDTO.getRutDueno());
        duenoMascotaEntity.setNombres(duenoMascotaDTO.getNombres());
        duenoMascotaEntity.setApellidoPaterno(duenoMascotaDTO.getApellidoPaterno());
        duenoMascotaEntity.setApellidoMaterno(duenoMascotaDTO.getApellidoMaterno());

        duenoMascotaEntity.setDireccion(duenoMascotaDTO.getDireccion());
        duenoMascotaEntity.setTelefono(duenoMascotaDTO.getTelefono());
        duenoMascotaEntity.setCorreo(duenoMascotaDTO.getCorreo());
        duenoMascotaEntity.setValid(duenoMascotaDTO.getValid());

        logger.info(duenoMascotaEntity.toString());
        try {
            entityManager.merge(duenoMascotaEntity);
            return true;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(DuenoMascotaDTO dto) {
        logger.info("DuenoMascotaDAOIMP: eliminar();");
        DuenoMascotaEntity duenoMascotaEntity = this.obtenerConRut(dto);
        if(duenoMascotaEntity!=null){
            try {
                entityManager.remove(duenoMascotaEntity);
                return true;
            } catch (Exception ex){
                logger.error(ex.getLocalizedMessage());
                logger.error(ex.getMessage());
                logger.error(ex.getCause());
                return false;
            }
        } else {
            return false;
        }
    }
}
