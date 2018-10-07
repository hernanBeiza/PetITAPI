package cl.petit.api.services.IMP;

import cl.petit.api.persistence.daos.UsuarioDAO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.UsuarioEntity;
import cl.petit.api.services.UsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class UsuarioServiceIMP implements UsuarioService {
    private static final Logger logger = LogManager.getLogger(UsuarioServiceIMP.class);

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public ArrayList<UsuarioDTO> obtener() {
        ArrayList<UsuarioEntity> entities = this.usuarioDAO.obtener();
        if(entities!=null){
            logger.info("Usuarios encontrados: " + entities.size());
            ArrayList<UsuarioDTO> encontrados = new ArrayList<UsuarioDTO>();
            for (UsuarioEntity entity : entities) {
                UsuarioDTO dto = new UsuarioDTO(entity);
                encontrados.add(dto);
            }
            return encontrados;
        } else {
            logger.warn("No se encontraron usuarios");
            return null;
        }
    }

    @Override
    public UsuarioDTO obtenerConID(Long idUsuario) {
        UsuarioEntity entity = this.usuarioDAO.obtenerConID(idUsuario);
        if(entity!=null){
            logger.info("Usuario encontrado");
            //System.out.println(entity);
            UsuarioDTO dto = new UsuarioDTO(entity);
            return dto;
        } else {
            logger.warn("No se encontró usuario con id " + idUsuario);
            return null;
        }
    }

    @Override
    public UsuarioDTO buscar(String rut, String contrasena) {
        UsuarioEntity entity = this.usuarioDAO.buscar(rut,contrasena);
        if(entity!=null){
            logger.info("Usuario encontrado: " + entity.toString());
            return new UsuarioDTO(entity);
        } else {
            logger.warn("No se encontró usuario con esos datos");
            return null;
        }
    }

    @Override
    public UsuarioDTO buscarPorNombre(String nombre) {
        UsuarioEntity entity = this.usuarioDAO.buscarPorNombre(nombre);
        if(entity!=null){
            UsuarioDTO dto = new UsuarioDTO(entity);
            return dto;
        } else {
            logger.warn("No se encontró usuario con ese nombre");
            return null;
        }
    }

    @Override
    public UsuarioDTO buscarPorRut(String rut) {
        UsuarioEntity entity = this.usuarioDAO.buscarPorRut(rut);
        if(entity!=null){
            UsuarioDTO dto = new UsuarioDTO(entity);
            return dto;
        } else {
            logger.warn("No se encontró usuario con ese rut");
            return null;
        }
    }

    @Override
    public boolean guardar(UsuarioDTO model) {
        boolean result = this.usuarioDAO.guardar(model);
        return result;
    }

    @Override
    public boolean editar(UsuarioDTO model) {
        boolean result = this.usuarioDAO.editar(model);
        return result;
    }

    @Override
    public boolean eliminar(UsuarioDTO model){
        boolean result = this.usuarioDAO.eliminar(model);
        return result;
    }
}
