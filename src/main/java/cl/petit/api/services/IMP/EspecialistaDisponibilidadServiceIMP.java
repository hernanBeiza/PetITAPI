package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.BloqueHorarioDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.models.dtos.EspecialistaDisponibilidadDTO;
import cl.petit.api.models.entities.BloqueHorarioEntity;
import cl.petit.api.models.entities.EspecialistaDisponibilidadEntity;
import cl.petit.api.persistence.daos.BloqueHorarioDAO;
import cl.petit.api.persistence.daos.EspecialistaDisponibilidadDAO;
import cl.petit.api.services.BloqueHorarioService;
import cl.petit.api.services.EspecialistaDisponibilidadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class EspecialistaDisponibilidadServiceIMP implements EspecialistaDisponibilidadService {

    private static final Logger logger = LogManager.getLogger(EspecialistaDisponibilidadServiceIMP.class);

    @Autowired
    private EspecialistaDisponibilidadDAO especialistaDisponibilidadDAO;

    @Override
    public boolean guardar(EspecialistaDisponibilidadDTO especialistaDisponibilidadDTO) {
        return this.especialistaDisponibilidadDAO.guardar(especialistaDisponibilidadDTO);
    }

    @Override
    public boolean editar(EspecialistaDisponibilidadDTO especialistaDisponibilidadDTO) {
        return this.especialistaDisponibilidadDAO.editar(especialistaDisponibilidadDTO);
    }

    @Override
    public boolean eliminar(EspecialistaDisponibilidadDTO especialistaDisponibilidadDTO) {
        return this.especialistaDisponibilidadDAO.eliminar(especialistaDisponibilidadDTO);
    }

    @Override
    public ArrayList<EspecialistaDisponibilidadDTO> obtener() {
        ArrayList<EspecialistaDisponibilidadEntity> entities = this.especialistaDisponibilidadDAO.obtener();
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Especialistas disponibilidades encontrados");
                ArrayList<EspecialistaDisponibilidadDTO> encontrados = new ArrayList<EspecialistaDisponibilidadDTO>();
                for (EspecialistaDisponibilidadEntity entity : entities) {
                    EspecialistaDisponibilidadDTO dto = new EspecialistaDisponibilidadDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.warn("No se encontraron entidades de EspecialialistaDisponibilidad");
            return null;
        }
    }

    @Override
    public EspecialistaDisponibilidadDTO obtenerConID(EspecialistaDisponibilidadDTO especialistaDisponibilidadDTO) {
        EspecialistaDisponibilidadEntity entity = this.especialistaDisponibilidadDAO.obtenerConID(especialistaDisponibilidadDTO);
        if(entity!=null){
            return new EspecialistaDisponibilidadDTO(entity);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<EspecialistaDisponibilidadDTO> obtenerConIDEspecialista(EspecialistaDTO especialistaDTO) {
        ArrayList<EspecialistaDisponibilidadEntity> entities = this.especialistaDisponibilidadDAO.obtenerConIDEspecialista(especialistaDTO);
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Especialistas disponibilidades encontrados para el especialista con id " + especialistaDTO.getIdEspecialista());
                ArrayList<EspecialistaDisponibilidadDTO> encontrados = new ArrayList<EspecialistaDisponibilidadDTO>();
                for (EspecialistaDisponibilidadEntity entity : entities) {
                    EspecialistaDisponibilidadDTO dto = new EspecialistaDisponibilidadDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.warn("No se encontraron disponibilidad para este id de especialista " + especialistaDTO.getIdEspecialista());
            return null;
        }
    }

    @Override
    public ArrayList<EspecialistaDisponibilidadDTO> obtenerConFecha(String fecha) {
        ArrayList<EspecialistaDisponibilidadEntity> entities = this.especialistaDisponibilidadDAO.obtenerConFecha(fecha);
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Horarios disponibles encontrados para la fecha " + fecha);
                logger.info(entities);
                ArrayList<EspecialistaDisponibilidadDTO> encontrados = new ArrayList<EspecialistaDisponibilidadDTO>();
                for (EspecialistaDisponibilidadEntity entity : entities) {
                    EspecialistaDisponibilidadDTO dto = new EspecialistaDisponibilidadDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.warn("No se encontró disponibilidad de horario para el día " + fecha);
            return null;
        }
    }

    @Override
    public ArrayList<EspecialistaDisponibilidadDTO> obtenerConIDEpecialistaYFecha(EspecialistaDTO especialistaDTO, String fecha) {
        ArrayList<EspecialistaDisponibilidadEntity> entities = this.especialistaDisponibilidadDAO.obtenerConIDEspecialistaYFecha(especialistaDTO,fecha);
        if(entities!=null){
            if(entities.size()>0){
                logger.info("Horarios disponibles encontrados para la fecha " + fecha);
                logger.info(entities);
                ArrayList<EspecialistaDisponibilidadDTO> encontrados = new ArrayList<EspecialistaDisponibilidadDTO>();
                for (EspecialistaDisponibilidadEntity entity : entities) {
                    EspecialistaDisponibilidadDTO dto = new EspecialistaDisponibilidadDTO(entity);
                    encontrados.add(dto);
                }
                return encontrados;
            } else {
                return null;
            }
        } else {
            logger.warn("No se encontró disponibilidad de horario para el día " + fecha);
            return null;
        }
    }
}
