package cl.petit.api.services;

import cl.petit.api.models.dtos.NotificacionDTO;
import cl.petit.api.models.dtos.UsuarioDTO;

import java.util.ArrayList;

public interface NotificacionService {

    ArrayList<NotificacionDTO> obtener();

    NotificacionDTO obtenerConID(NotificacionDTO notificacionDTO);

    ArrayList<NotificacionDTO> obtenerConIDUsuario(UsuarioDTO usuarioDTO);

    boolean guardar(NotificacionDTO notificacionDTO);

    boolean editar(NotificacionDTO notificacionDTO);

    boolean eliminar(NotificacionDTO notificacionDTO);

    boolean marcarComoLeida(NotificacionDTO notificacionDTO);
}
