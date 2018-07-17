package cl.petit.api.services;

import cl.petit.api.models.dtos.UsuarioDTO;

import java.util.ArrayList;

public interface UsuarioService {

    ArrayList<UsuarioDTO> obtener();
    UsuarioDTO obtenerConID(int idusuario);

    UsuarioDTO iniciarSesion(UsuarioDTO model);
}
