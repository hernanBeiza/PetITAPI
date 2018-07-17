package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.entities.DuenoMascotaEntity;
import cl.petit.api.persistence.daos.DuenoMascotaDAO;
import cl.petit.api.persistence.daos.MascotaDAO;
import cl.petit.api.services.DuenoMascotaService;
import cl.petit.api.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class DuenoMascotaServiceIMP implements DuenoMascotaService {

    @Autowired
    private DuenoMascotaDAO duenoMascotaDAO;

    @Override
    public ArrayList<DuenoMascotaDTO> obtener() {
        ArrayList<DuenoMascotaEntity> entities = this.duenoMascotaDAO.obtener();
        if(entities!=null){
            System.out.println("Dueños de mascotas encontrados");
            ArrayList<DuenoMascotaDTO> encontrados = new ArrayList<DuenoMascotaDTO>();
            for (DuenoMascotaEntity entity : entities) {
                DuenoMascotaDTO dto = new DuenoMascotaDTO(entity);
                System.out.println("DuenoMascotaServiceIMP: " + dto.toString());
                encontrados.add(dto);
            }
            return encontrados;
        } else {
            System.out.println("No se encontraron dueños de mascotas");
            return null;
        }
    }

    @Override
    public DuenoMascotaDTO obtenerConID(DuenoMascotaDTO dto) {
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

    @Override
    public DuenoMascotaDTO obtenerConRut(DuenoMascotaDTO model) {
        DuenoMascotaEntity entity = this.duenoMascotaDAO.obtenerConRut(model);
        if(entity!=null){
            return new DuenoMascotaDTO(entity);
        } else {
            return null;
        }
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
