package cl.petit.api.services.IMP;

import cl.petit.api.config.ArchivoConfig;
import cl.petit.api.models.dtos.NotificacionDTO;
import cl.petit.api.models.entities.NotificacionEntity;
import cl.petit.api.persistence.daos.ComunaDAO;
import cl.petit.api.persistence.daos.NotificacionDAO;
import cl.petit.api.services.ArchivoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ArchivoServiceIMP implements ArchivoService {

    private final Path archivoRuta;
    private static final Logger logger = LogManager.getLogger(ArchivoServiceIMP.class);

    @Autowired
    public ArchivoServiceIMP(ArchivoConfig archivoConfig) {
        this.archivoRuta = Paths.get(archivoConfig.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.archivoRuta);
        } catch (Exception ex) {
            logger.error("No se pudo crear la carpeta en dónde se cargarán las imagenes");
            logger.error(ex.getLocalizedMessage());
            //throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String guardar(MultipartFile archivo) {
        logger.info("guardar();");
        // Normalize file name
        String nombrearchivo = StringUtils.cleanPath(archivo.getOriginalFilename());
        logger.info(nombrearchivo);

        try {
            // Check if the file's name contains invalid characters
            if(nombrearchivo.contains("..")) {
                logger.error("El archivo contiene un nombre inválido: " + nombrearchivo);
                //throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                return null;
            }

            // Copy file to the target location (Replacing existing file with the same name)
            logger.info("guardar archivo en la ruta");
            logger.info(this.archivoRuta);
            Path targetLocation = this.archivoRuta.resolve(nombrearchivo);

            Files.copy(archivo.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return nombrearchivo;

        } catch (IOException ex) {
            logger.error("No se pudo guardar el archivo " + nombrearchivo + ". Por favor, intenta de nuevo");
            logger.error(ex.getLocalizedMessage());
            //throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            return null;
        }

    }

    public boolean eliminar(String nombrearchivo) {
        logger.info("eliminar();");
        try {
            Path targetLocation = this.archivoRuta.resolve(nombrearchivo);
            logger.info(targetLocation.toString());
            Files.delete(targetLocation);
            return true;

        } catch (IOException ex){
            logger.error(ex.getLocalizedMessage());
            return false;
        }
    }

    public Resource cargarComoRecurso(String nombreArchivo) {
        logger.info("cargarComoRecurso();");
        logger.info(nombreArchivo);

        try {
            Path filePath = this.archivoRuta.resolve(nombreArchivo).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                logger.error("Archivo " + nombreArchivo +" no encontrado");
                //throw new MyFileNotFoundException("File not found " + fileName);
                return null;
            }
        } catch (MalformedURLException e) {
            logger.error("Archivo " + nombreArchivo +" no encontrado");
            logger.error(e.getLocalizedMessage());
            //throw new MyFileNotFoundException("File not found " + fileName, ex);
            return null;
        }
    }
}