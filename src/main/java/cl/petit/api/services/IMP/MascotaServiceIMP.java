package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.MascotaEntity;
import cl.petit.api.models.entities.UsuarioEntity;
import cl.petit.api.persistence.daos.MascotaDAO;
import cl.petit.api.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class MascotaServiceIMP implements MascotaService {

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
            System.out.println("No se encontraron mascotas");
            return null;
        }
    }

    @Override
    public MascotaDTO obtenerConID(MascotaDTO dto) {
        return null;
        /*
        MascotaEntity entity = this.mascotaDAO.obtenerConID(model);
        if(entity!=null){
            System.out.println(entity.toString());
            return new MascotaDTO(entity);
        } else {
            return null;
        }
        */
    }

    /*
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

    @Override
    public ArrayList<UsuarioDTO> obtener() {
        ArrayList<UsuarioEntity> entities = this.usuarioDAO.obtener();
        if(entities!=null){
            System.out.println("Usuarios encontrados");
            ArrayList<UsuarioDTO> encontrados = new ArrayList<UsuarioDTO>();
            for (UsuarioEntity entity : entities) {
                UsuarioDTO dto = new UsuarioDTO(entity);
                System.out.println(dto.toString());
                encontrados.add(dto);
            }
            return encontrados;
        } else {
            System.out.println("No se encontraron usuarios");
            return null;
        }
    }
    */

}
