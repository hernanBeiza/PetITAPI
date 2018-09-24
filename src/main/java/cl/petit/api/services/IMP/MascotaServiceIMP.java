package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.MascotaEntity;
import cl.petit.api.models.entities.UsuarioEntity;
import cl.petit.api.persistence.daos.MascotaDAO;
import cl.petit.api.services.MascotaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class MascotaServiceIMP implements MascotaService {

    private static final Logger logger = LogManager.getLogger(MascotaServiceIMP.class);

    @Autowired
    private MascotaDAO mascotaDAO;

    @Override
    public ArrayList<MascotaDTO> obtener() {
        ArrayList<MascotaEntity> entities = this.mascotaDAO.obtener();
        if(entities!=null){
            System.out.println("Mascotas encontradas");
            ArrayList<MascotaDTO> encontradas = new ArrayList<MascotaDTO>();
            for (MascotaEntity entity : entities) {
                MascotaDTO dto = new MascotaDTO(entity);
                encontradas.add(dto);
            }
            return encontradas;
        } else {
            logger.warn("No se encontraron mascotas");
            return null;
        }
    }

    @Override
    public ArrayList<MascotaDTO> obtenerConPagina(Integer pagina) {
        ArrayList<MascotaEntity> entities = this.mascotaDAO.obtenerConPagina(pagina);
        if(entities!=null){
            System.out.println("Mascotas encontradas");
            ArrayList<MascotaDTO> encontradas = new ArrayList<MascotaDTO>();
            for (MascotaEntity entity : entities) {
                MascotaDTO dto = new MascotaDTO(entity);
                encontradas.add(dto);
            }
            return encontradas;
        } else {
            logger.warn("No se encontraron mascotas");
            return null;
        }
    }

    @Override
    public MascotaDTO obtenerConRut(MascotaDTO mascotaDTO) {
        MascotaEntity mascotaEntity = this.mascotaDAO.obtenerConRut(mascotaDTO);
        if(mascotaEntity!=null){
            MascotaDTO mascotaEncontrada = new MascotaDTO(mascotaEntity);
            return mascotaEncontrada;
        } else {
            logger.warn("No se encontró mascota con ese rut de mascota");
            return null;
        }
    }

    @Override
    public ArrayList<MascotaDTO> obtenerConRutDueno(DuenoMascotaDTO duenoMascotaDTO) {
        ArrayList<MascotaEntity> entities = this.mascotaDAO.obtenerConRutDueno(duenoMascotaDTO);
        if(entities!=null){
            ArrayList<MascotaDTO> encontradas = new ArrayList<MascotaDTO>();
            for (MascotaEntity entity : entities) {
                MascotaDTO dto = new MascotaDTO(entity);
                encontradas.add(dto);
            }
            return encontradas;
        } else {
            logger.warn("No se encontraron mascotas");
            return null;
        }
    }

    @Override
    public ArrayList<MascotaDTO> buscarPorNombre(MascotaDTO mascotaDTO) {
        ArrayList<MascotaEntity> entities = this.mascotaDAO.buscarPorNombre(mascotaDTO);
        if(entities!=null){
            ArrayList<MascotaDTO> encontradas = new ArrayList<MascotaDTO>();
            for (MascotaEntity entity : entities) {
                MascotaDTO dto = new MascotaDTO(entity);
                encontradas.add(dto);
            }
            return encontradas;
        } else {
            logger.warn("No se encontraron mascotas");
            return null;
        }
    }

    @Override
    public boolean guardar(MascotaDTO mascotaDTO) {
        return this.mascotaDAO.guardar(mascotaDTO);
    }

    @Override
    public boolean editar(MascotaDTO mascotaDTO) {
        return this.mascotaDAO.editar(mascotaDTO);
    }

    @Override
    public boolean eliminar(MascotaDTO mascotaDTO) {
        return this.mascotaDAO.eliminar(mascotaDTO);
    }
}
