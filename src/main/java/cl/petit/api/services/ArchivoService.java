package cl.petit.api.services;

import cl.petit.api.models.dtos.NotificacionDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivoService {

    String guardar(MultipartFile archivo);

    boolean eliminar(String nombrearchivo);

    Resource cargarComoRecurso(String nombreArchivo);

}
