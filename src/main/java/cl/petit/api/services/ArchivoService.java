package cl.petit.api.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivoService {

    String guardar(MultipartFile archivo);

    Resource cargarComoRecurso(String nombreArchivo);

}
