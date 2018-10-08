package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.CitaDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.entities.CitaEntity;
import cl.petit.api.persistence.daos.CitaDAO;
import cl.petit.api.services.CitaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class CitaServiceIMP implements CitaService {

    private static final Logger logger = LogManager.getLogger(CitaServiceIMP.class);

    @Autowired
    private CitaDAO citaDAO;

    @Override
    public ArrayList<CitaDTO> obtener() {
        ArrayList<CitaEntity> entities = this.citaDAO.obtener();
        if(entities!=null){
            if(entities.size()>0){
                ArrayList<CitaDTO> encontradas = new ArrayList<CitaDTO>();
                for (CitaEntity entity : entities) {
                    CitaDTO dto = new CitaDTO(entity);
                    encontradas.add(dto);
                }
                return encontradas;
            } else {
                return null;
            }
        } else {
            logger.warn("No se encontraron citas");
            return null;
        }
    }

    @Override
    public CitaDTO obtenerConID(CitaDTO citaDTO) {
        CitaEntity entity = this.citaDAO.obtenerConID(citaDTO);
        if(entity!=null){
            return new CitaDTO(entity);
        } else {
            logger.warn("No se encontró cita con id " + citaDTO.getIdCita());
            return null;
        }
    }

    @Override
    public CitaDTO obtenerConRutMascota(MascotaDTO mascotaDTO) {
        CitaEntity entity = this.citaDAO.obtenerConRutMascota(mascotaDTO);
        if(entity!=null){
            return new CitaDTO(entity);
        } else {
            logger.warn("No se encontró cita con rut mascota " + mascotaDTO.getRutMascota());
            return null;
        }
    }

    @Override
    public CitaDTO obtenerConIDEspecialista(EspecialistaDTO especialistaDTO) {
        CitaEntity entity = this.citaDAO.obtenerConIDEspecialista(especialistaDTO);
        if(entity!=null){
            return new CitaDTO(entity);
        } else {
            logger.warn("No se encontró cita con id de especialista " + especialistaDTO.getIdEspecialista());
            return null;
        }
    }

    @Override
    public CitaDTO cambiarEstado(CitaDTO citaDTO) {
        CitaEntity entity = this.citaDAO.cambiarEstado(citaDTO);
        if(entity!=null){
            return new CitaDTO(entity);
        } else {
            logger.warn("NO se pudo cambiar el estado");
            return null;
        }
    }

    @Override
    public boolean guardar(CitaDTO citaDTO) {
        return this.citaDAO.guardar(citaDTO);
    }

    @Override
    public boolean editar(CitaDTO citaDTO) {
        return this.citaDAO.editar(citaDTO);
    }

    @Override
    public boolean eliminar(CitaDTO citaDTO) {
        return this.citaDAO.eliminar(citaDTO);
    }
}
