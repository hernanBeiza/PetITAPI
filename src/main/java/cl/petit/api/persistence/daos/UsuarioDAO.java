package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.UsuarioEntity;

import java.util.ArrayList;

public interface UsuarioDAO {

    ArrayList<UsuarioEntity> obtener();
    UsuarioEntity obtenerConID(int idUsuario);

    UsuarioEntity buscar(UsuarioDTO model);

    UsuarioEntity buscarPorNombre(String nombre);

    UsuarioEntity buscarPorRut(String rut);
}
