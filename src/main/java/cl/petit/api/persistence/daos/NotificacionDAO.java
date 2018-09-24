package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.NotificacionDTO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.NotificacionEntity;

import java.util.ArrayList;

public interface NotificacionDAO {

    ArrayList<NotificacionEntity> obtener();

    NotificacionEntity obtenerConID(NotificacionDTO notificacionDTO);

    ArrayList<NotificacionEntity> obtenerConIDUsuario(UsuarioDTO usuarioDTO);

    boolean guardar(NotificacionDTO notificacionDTO);

    boolean editar(NotificacionDTO notificacionDTO);

    boolean eliminar(NotificacionDTO notificacionDTO);

    boolean marcarComoLeida(NotificacionDTO notificacionDTO);
}
