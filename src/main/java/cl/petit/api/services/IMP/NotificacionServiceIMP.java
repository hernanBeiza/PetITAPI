package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.NotificacionDTO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.NotificacionEntity;
import cl.petit.api.persistence.daos.NotificacionDAO;
import cl.petit.api.services.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Transactional
@Service
public class NotificacionServiceIMP implements NotificacionService {

    @Autowired
    private NotificacionDAO notificacionDAO;

    @Override
    public ArrayList<NotificacionDTO> obtener() {
        ArrayList<NotificacionEntity> entities = this.notificacionDAO.obtener();
        if(entities!=null){
            System.out.println("Notificaciones encontradas:" + entities.size());
            ArrayList<NotificacionDTO> encontradas = new ArrayList<NotificacionDTO>();
            for (NotificacionEntity entity : entities) {
                NotificacionDTO dto = new NotificacionDTO(entity);
                encontradas.add(dto);
            }
            return encontradas;
        } else {
            return null;
        }
    }

    @Override
    public NotificacionDTO obtenerConID(int idNotificacion) {
        NotificacionEntity entity = this.notificacionDAO.obtenerConID(idNotificacion);
        if(entity!=null){
            NotificacionDTO dto = new NotificacionDTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<NotificacionDTO> obtenerConIDUsuario(int idUsuario) {
        ArrayList<NotificacionEntity> entities = this.notificacionDAO.obtenerConIDUsuario(idUsuario);
        if(entities!=null){
            System.out.println("Notificaciones encontradas:" + entities.size());
            ArrayList<NotificacionDTO> encontradas = new ArrayList<NotificacionDTO>();
            for (NotificacionEntity entity : entities) {
                NotificacionDTO dto = new NotificacionDTO(entity);
                encontradas.add(dto);
            }
            return encontradas;
        } else {
            return null;
        }
    }

    @Override
    public boolean guardar(NotificacionDTO model) {
        boolean result = this.notificacionDAO.guardar(model);
        return result;
    }

    @Override
    public boolean editar(NotificacionDTO model) {
        boolean result = this.notificacionDAO.editar(model);
        return result;
    }

    @Override
    public boolean eliminar(NotificacionDTO model){
        boolean result = this.notificacionDAO.eliminar(model);
        return result;
    }

    @Override
    public boolean marcarComoLeida(NotificacionDTO model) {
        boolean result = this.notificacionDAO.marcarComoLeida(model);
        return result;
    }
}
