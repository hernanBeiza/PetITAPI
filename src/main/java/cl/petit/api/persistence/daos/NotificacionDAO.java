package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.NotificacionDTO;
import cl.petit.api.models.entities.NotificacionEntity;

import java.util.ArrayList;

public interface NotificacionDAO {

    ArrayList<NotificacionEntity> obtener();

    NotificacionEntity obtenerConID(int idNotificacion);

    ArrayList<NotificacionEntity> obtenerConIDUsuario(int idUsuario);

    boolean guardar(NotificacionDTO model);

    boolean editar(NotificacionDTO model);

    boolean eliminar(NotificacionDTO model);

    boolean marcarComoLeida(NotificacionDTO model);
}
