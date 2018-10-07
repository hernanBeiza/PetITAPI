package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.RolDTO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.RolEntity;
import cl.petit.api.models.entities.UsuarioEntity;
import cl.petit.api.persistence.daos.IMP.NotificacionDAOIMP;
import cl.petit.api.persistence.daos.RolDAO;
import cl.petit.api.persistence.daos.UsuarioDAO;
import cl.petit.api.services.RolService;
import cl.petit.api.services.UsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class RolServiceIMP implements RolService {

    private static final Logger logger = LogManager.getLogger(RolServiceIMP.class);

    @Autowired
    private RolDAO rolDAO;

    @Override
    public ArrayList<RolDTO> obtener() {
        ArrayList<RolEntity> entities = this.rolDAO.obtener();
        if(entities!=null){
            logger.info("Roles encontrados");
            ArrayList<RolDTO> encontrados = new ArrayList<RolDTO>();
            for (RolEntity entity : entities) {
                RolDTO dto = new RolDTO(entity);
                encontrados.add(dto);
            }
            return encontrados;

        } else {
            return null;
        }
    }

    @Override
    public RolDTO obtenerConID(Long idRol) {
        RolEntity entity = this.rolDAO.obtenerConID(idRol);
        if(entity!=null){
            logger.info("Rol encontrado");
            return new RolDTO(entity);
        } else {
            return null;
        }
    }

}
