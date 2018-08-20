package cl.petit.api.services;

import cl.petit.api.models.dtos.NotificacionDTO;
import cl.petit.api.models.dtos.UsuarioDTO;

import java.util.ArrayList;

public interface NotificacionService {

    ArrayList<NotificacionDTO> obtener();

    NotificacionDTO obtenerConID(int idNotificacion);

    ArrayList<NotificacionDTO> obtenerConIDUsuario(int idUsuario);

    boolean guardar(NotificacionDTO model);

    boolean editar(NotificacionDTO model);

    boolean eliminar(NotificacionDTO model);

    boolean marcarComoLeida(NotificacionDTO model);
}
