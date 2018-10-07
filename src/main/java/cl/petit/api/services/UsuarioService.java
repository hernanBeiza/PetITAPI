package cl.petit.api.services;

import cl.petit.api.models.dtos.UsuarioDTO;

import java.util.ArrayList;

public interface UsuarioService {

    ArrayList<UsuarioDTO> obtener();

    UsuarioDTO obtenerConID(Long idUsuario);

    UsuarioDTO buscar(String rut, String contrasena);

    UsuarioDTO buscarPorNombre(String nombre);

    UsuarioDTO buscarPorRut(String rut);

    boolean guardar(UsuarioDTO model);

    boolean editar(UsuarioDTO model);

    boolean eliminar(UsuarioDTO model);
}
