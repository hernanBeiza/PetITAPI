package cl.petit.api.services.IMP;

import cl.petit.api.persistence.daos.UsuarioDAO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.UsuarioEntity;
import cl.petit.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UsuarioServiceIMP implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public UsuarioDTO buscar(UsuarioDTO model) {
        UsuarioEntity entity = this.usuarioDAO.buscar(model);
        if(entity!=null){
            System.out.println(entity.toString());
            return new UsuarioDTO(entity);
        } else {
            return null;
        }
    }
}
