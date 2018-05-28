package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.UsuarioEntity;

public interface UsuarioDAO {

    UsuarioEntity buscar(UsuarioDTO model);

    UsuarioEntity buscarPorNombre(String nombre);

    UsuarioEntity buscarPorRut(String rut);
}
