package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.entities.RolEntity;
import cl.petit.api.persistence.daos.UsuarioDAO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.UsuarioEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("UsuarioDAO")
public class UsuarioDAOIMP implements UsuarioDAO {

    private static final Logger logger = LogManager.getLogger(UsuarioDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UsuarioEntity buscar(String rut, String contrasena) {
        logger.info("buscar();");
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT u FROM UsuarioEntity AS u WHERE u.rut ='"+rut+"' AND u.password='"+contrasena+"'";
        logger.info(query);
        try{
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (UsuarioEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            logger.warn(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public UsuarioEntity buscarPorNombre(String nombre) {
        logger.info("buscarPorNombre();");
        String query = "SELECT u FROM UsuarioEntity AS u WHERE u.nombre LIKE '%"+nombre+"%'";
        logger.info(query);
        try {
            return (UsuarioEntity)entityManager.createQuery(query).getSingleResult();
        } catch (Exception ex){
            logger.warn(ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public UsuarioEntity buscarPorRut(String rut) {
        logger.info("buscarPorRut();");
        String query = "SELECT u FROM UsuarioEntity AS u WHERE u.rut = '"+rut+"'";
        logger.info(query);
        try {
            return (UsuarioEntity)entityManager.createQuery(query).getSingleResult();
        } catch (Exception ex){
            logger.warn(ex.getLocalizedMessage());
            //System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<UsuarioEntity> obtener() {
        logger.info("obtener();");
        //String query = "SELECT u FROM UsuarioEntity AS u";
        String query = "SELECT u FROM UsuarioEntity u JOIN u.rolEntity";
        logger.info(query);
        ArrayList<UsuarioEntity> encontrados = (ArrayList<UsuarioEntity>) entityManager.createQuery(query).getResultList();
        if (encontrados.size()>0){
            return encontrados;
        } else {
            return null;
        }
    }

    @Override
    public UsuarioEntity obtenerConID(Long idUsuario) {
        logger.info("obtenerConID();");
        String query = "SELECT u FROM UsuarioEntity u WHERE idUsuario="+idUsuario;
        logger.info(query);
        try {
            return (UsuarioEntity)entityManager.createQuery(query).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public boolean guardar(UsuarioDTO model) {
        logger.info("guardar();");
        UsuarioEntity encontrado = this.buscarPorRut(model.getRut());

        if(encontrado!=null){
            logger.info(encontrado.toString());
            logger.info("Usuario rut " + encontrado.getRut() + " encontrado");
            return false;
        } else {
            logger.info("Usuario rut " + model.getRut() + " no encontrado");
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            RolEntity rolEntity = new RolEntity();
            rolEntity.setIdRol(model.getRol().getIdRol());
            rolEntity.setNombre(model.getRol().getNombre());
            usuarioEntity.setRolEntity(rolEntity);
            usuarioEntity.setNombre(model.getNombre());
            usuarioEntity.setPassword(model.getPassword());
            usuarioEntity.setRut(model.getRut());
            usuarioEntity.setValid(model.getValid());

            try {
                entityManager.persist(usuarioEntity);
                logger.info("Usuario guardado correctamente");
                return true;
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                return false;
            }

        }

    }

    @Override
    public boolean editar(UsuarioDTO model) {
        logger.info("editar();");
        UsuarioEntity encontrado = this.buscarPorRut(model.getRut());

        if(encontrado!=null){
            logger.info(encontrado.toString());
            logger.info("Usuario rut " + encontrado.getRut() + " encontrado");

            UsuarioEntity usuarioEntity = new UsuarioEntity();

            RolEntity rolEntity = new RolEntity();
            rolEntity.setIdRol(model.getRol().getIdRol());
            rolEntity.setNombre(model.getRol().getNombre());
            usuarioEntity.setIdUsuario(model.getIdUsuario());
            usuarioEntity.setRolEntity(rolEntity);
            usuarioEntity.setNombre(model.getNombre());
            usuarioEntity.setPassword(model.getPassword());
            usuarioEntity.setRut(model.getRut());
            usuarioEntity.setValid(model.getValid());

            System.out.println(usuarioEntity.toString());
            try {
                entityManager.merge(usuarioEntity);
                return true;
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                //logger.error(e.getMessage());
                //logger.error(e.getCause());
                //System.out.println(e.getLocalizedMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminar(UsuarioDTO model) {
        logger.info("eliminar();");
        UsuarioEntity usuarioEntity = this.obtenerConID(model.getIdUsuario());
        if(usuarioEntity!=null){
            try {
                entityManager.remove(usuarioEntity);
                return true;
            } catch (Exception ex){
                logger.error(ex.getLocalizedMessage());
                logger.error(ex.getMessage());
                logger.error(ex.getCause());
                //System.out.println(ex.getLocalizedMessage());
                return false;
            }
        } else {
            return false;
        }
    }

}
