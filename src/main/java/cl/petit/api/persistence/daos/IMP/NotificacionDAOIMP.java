package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.NotificacionDTO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.NotificacionEntity;
import cl.petit.api.models.entities.RolEntity;
import cl.petit.api.models.entities.UsuarioEntity;
import cl.petit.api.persistence.daos.NotificacionDAO;
import cl.petit.api.persistence.daos.UsuarioDAO;
import cl.petit.api.services.ArchivoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("NotificacionDAO")
public class NotificacionDAOIMP implements NotificacionDAO {

    private static final Logger logger = LogManager.getLogger(NotificacionDAOIMP.class);

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ArchivoService archivoService;

    @Override
    public ArrayList<NotificacionEntity> obtener() {
        logger.info("obtener();");
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
    public NotificacionEntity obtenerConID(NotificacionDTO notificacionDTO) {
        logger.info("obtenerConID();");
        String query = "SELECT u FROM NotificacionEntity u WHERE idNotificacion="+notificacionDTO.getIdNotificacion();
        logger.info(query);
        try {
            return (NotificacionEntity)entityManager.createQuery(query).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<NotificacionEntity> obtenerConIDUsuario(UsuarioDTO usuarioDTO) {
        logger.info("obtenerConIDUsuario();");
        String query = "SELECT u FROM NotificacionEntity u WHERE idUsuario="+usuarioDTO.getIdUsuario();
        logger.info(query);
        ArrayList<NotificacionEntity> encontrados = (ArrayList<NotificacionEntity>) entityManager.createQuery(query).getResultList();
        if (encontrados.size()>0){
            return encontrados;
        } else {
            return null;
        }
    }

    @Override
    public boolean guardar(NotificacionDTO notificacionDTO) {
        logger.info("guardar();");
        NotificacionEntity notificacionEntity = new NotificacionEntity();
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setIdUsuario(notificacionDTO.getUsuario().getIdUsuario());
        notificacionEntity.setUsuarioEntity(usuarioEntity);
        notificacionEntity.setTitulo(notificacionDTO.getTitulo());
        notificacionEntity.setMensaje(notificacionDTO.getMensaje());
        notificacionEntity.setImagen(notificacionDTO.getImagen());

        try {
            entityManager.persist(notificacionEntity);
            //Eliminar archivo de la notificación
            boolean eliminada = this.archivoService.eliminar(notificacionDTO.getImagen());

            return true;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }

    }

    @Override
    public boolean editar(NotificacionDTO notificacionDTO) {
        logger.info("editar();");
        NotificacionEntity notificacionEntity = this.obtenerConID(notificacionDTO);
        if (notificacionEntity!=null){
            String imagenAntigua = notificacionEntity.getImagen();
            //notificacionEntity.setIdNotificacion(notificacionDTO.getIdNotificacion());
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity.setIdUsuario(notificacionDTO.getUsuario().getIdUsuario());
            notificacionEntity.setUsuarioEntity(usuarioEntity);
            notificacionEntity.setTitulo(notificacionDTO.getTitulo());
            notificacionEntity.setMensaje(notificacionDTO.getMensaje());
            notificacionEntity.setImagen(notificacionDTO.getImagen());
            notificacionEntity.setValid(notificacionDTO.getValid());

            try {
                entityManager.merge(notificacionEntity);
                this.archivoService.eliminar(imagenAntigua);

                return true;
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                logger.error(e.getMessage());
                logger.error(e.getCause());
                return false;
            }
        } else {
            logger.warn("Notificación con id " + notificacionDTO.getIdNotificacion() + " no existe. No se puede editar");
            return false;
        }
    }

    @Override
    public boolean eliminar(NotificacionDTO notificacionDTO) {
        logger.info("eliminar();");
        NotificacionEntity notificacionEntity = this.obtenerConID(notificacionDTO);
        if(notificacionEntity!=null){
            try {
                entityManager.remove(notificacionEntity);
                //Eliminar archivo de la notificación
                boolean eliminada = this.archivoService.eliminar(notificacionEntity.getImagen());

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
    public boolean marcarComoLeida(NotificacionDTO notificacionDTO) {
        logger.info("marcarComoLeida();");
        NotificacionEntity notificacionEntity = this.obtenerConID(notificacionDTO);
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
