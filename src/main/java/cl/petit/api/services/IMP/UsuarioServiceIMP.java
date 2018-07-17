package cl.petit.api.services.IMP;

import cl.petit.api.persistence.daos.UsuarioDAO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.UsuarioEntity;
import cl.petit.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class UsuarioServiceIMP implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public UsuarioDTO iniciarSesion(UsuarioDTO model) {
        UsuarioEntity entity = this.usuarioDAO.buscar(model);
        if(entity!=null){
            System.out.println(entity.toString());
            return new UsuarioDTO(entity);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<UsuarioDTO> obtener() {
        ArrayList<UsuarioEntity> entities = this.usuarioDAO.obtener();
        if(entities!=null){
            System.out.println("Usuarios encontrados");
            ArrayList<UsuarioDTO> encontrados = new ArrayList<UsuarioDTO>();
            for (UsuarioEntity entity : entities) {
                UsuarioDTO dto = new UsuarioDTO(entity);
                //System.out.println(dto.toString());
                encontrados.add(dto);
            }
            return encontrados;
        } else {
            System.out.println("No se encontraron usuarios");
            return null;
        }
    }

    @Override
    public UsuarioDTO obtenerConID(int idusuario) {
        UsuarioEntity entity = this.usuarioDAO.obtenerConID(idusuario);
        if(entity!=null){
            System.out.println("Usuario encontrado");
            //System.out.println(entity);
            UsuarioDTO dto = new UsuarioDTO(entity);
            return dto;
        } else {
            System.out.println("No se encontraron usuarios");
            return null;
        }

    }
}
