package cl.petit.api.persistence.daos;

import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.UsuarioEntity;

import java.util.ArrayList;

public interface UsuarioDAO {

    ArrayList<UsuarioEntity> obtener();

    UsuarioEntity obtenerConID(Long idUsuario);

    UsuarioEntity buscar(String rut, String contrasena);

    UsuarioEntity buscarPorNombre(String nombre);

    UsuarioEntity buscarPorRut(String rut);

    boolean guardar(UsuarioDTO model);

    boolean editar(UsuarioDTO model);

    boolean eliminar(UsuarioDTO model);
}
