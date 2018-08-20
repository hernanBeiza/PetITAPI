package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.NotificacionDTO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.NotificacionEntity;
import cl.petit.api.models.entities.RolEntity;
import cl.petit.api.models.entities.UsuarioEntity;
import cl.petit.api.persistence.daos.NotificacionDAO;
import cl.petit.api.persistence.daos.UsuarioDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("NotificacionDAO")
public class NotificacionDAOIMP implements NotificacionDAO {

    private static final Logger logger = LogManager.getLogger(NotificacionDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<NotificacionEntity> obtener() {
        logger.info("NotificacionDAO: obtener();");
        String query = "SELECT u FROM NotificacionEntity u JOIN u.usuarioEntity";
        logger.info(query);
        ArrayList<NotificacionEntity> encontrados = (ArrayList<NotificacionEntity>) entityManager.createQuery(query).getResultList();
        if (encontrados.size()>0){
            return encontrados;
        } else {
            return null;
        }
    }

    @Override
    public NotificacionEntity obtenerConID(int idNotificacion) {
        logger.info("NotificacionDAO: obtenerConID();");
        String query = "SELECT u FROM NotificacionEntity u WHERE idNotificacion="+idNotificacion;
        logger.info(query);
        try {
            return (NotificacionEntity)entityManager.createQuery(query).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<NotificacionEntity> obtenerConIDUsuario(int idUsuario) {
        logger.info("NotificacionDAO: obtenerConIDUsuario();");
        String query = "SELECT u FROM NotificacionEntity u WHERE idUsuario="+idUsuario;
        logger.info(query);
        ArrayList<NotificacionEntity> encontrados = (ArrayList<NotificacionEntity>) entityManager.createQuery(query).getResultList();
        if (encontrados.size()>0){
            return encontrados;
        } else {
            return null;
        }
    }

    @Override
    public boolean guardar(NotificacionDTO model) {
        logger.info("NotificacionDAO: guardar();");
        NotificacionEntity notificacionEntity = new NotificacionEntity();
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setIdUsuario(model.getUsuario().getIdUsuario());
        notificacionEntity.setUsuarioEntity(usuarioEntity);
        notificacionEntity.setTitulo(model.getTitulo());
        notificacionEntity.setMensaje(model.getMensaje());
        notificacionEntity.setImagen(model.getImagen());

        try {
            entityManager.persist(notificacionEntity);
            return true;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }

    }

    @Override
    public boolean editar(NotificacionDTO model) {
        logger.info("NotificacionDAO: editar();");
        NotificacionEntity notificacionEntity = new NotificacionEntity();
        notificacionEntity.setIdNotificacion(model.getIdNotificacion());
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setIdUsuario(model.getUsuario().getIdUsuario());
        notificacionEntity.setUsuarioEntity(usuarioEntity);
        notificacionEntity.setTitulo(model.getTitulo());
        notificacionEntity.setMensaje(model.getMensaje());
        notificacionEntity.setImagen(model.getImagen());
        notificacionEntity.setValid(model.getValid());

        try {
            entityManager.merge(notificacionEntity);
            return true;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            logger.error(e.getMessage());
            logger.error(e.getCause());
            return false;
        }
    }

    @Override
    public boolean eliminar(NotificacionDTO model) {
        logger.info("NotificacionDAO: eliminar();");
        NotificacionEntity notificacionEntity = this.obtenerConID(model.getIdNotificacion().intValue());
        if(notificacionEntity!=null){
            try {
                entityManager.remove(notificacionEntity);
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

    @Override
    public boolean marcarComoLeida(NotificacionDTO model) {
        logger.info("NotificacionDAO: marcarComoLeida();");
        NotificacionEntity notificacionEntity = this.obtenerConID(model.getIdNotificacion().intValue());
        if(notificacionEntity!=null){
            notificacionEntity.setValid(1);
            try {
                entityManager.merge(notificacionEntity);
                return true;
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                logger.error(e.getMessage());
                logger.error(e.getCause());
                return false;
            }
        } else {
            logger.warn("No se encontró notificación con ese idnotificacion");
            return false;
        }
    }
}
