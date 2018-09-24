package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.RazaDTO;
import cl.petit.api.models.dtos.TipoMascotaDTO;
import cl.petit.api.models.entities.RazaEntity;
import cl.petit.api.models.entities.TipoMascotaEntity;
import cl.petit.api.persistence.daos.RazaDAO;
import cl.petit.api.persistence.daos.TipoMascotaDAO;
import cl.petit.api.services.RazaService;
import cl.petit.api.services.TipoMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class RazaServiceIMP implements RazaService {

    @Autowired
    private RazaDAO razaDAO;

    @Override
    public ArrayList<RazaDTO> obtener() {
        ArrayList<RazaEntity> entities = this.razaDAO.obtener();
        if(entities!=null){
            System.out.println("Razas encontradas");
            ArrayList<RazaDTO> encontrados = new ArrayList<RazaDTO>();
            for (RazaEntity entity : entities) {
                RazaDTO dto = new RazaDTO(entity);
                System.out.println(dto.toString());
                encontrados.add(dto);
            }
            return encontrados;
        } else {
            System.out.println("No se encontraron mascotas");
            return null;
        }
    }

    @Override
    public RazaDTO obtenerConID(RazaDTO razaDTO) {
        RazaEntity entity = this.razaDAO.obtenerConID(razaDTO);
        if(entity!=null){
            System.out.println(entity.toString());
            return new RazaDTO(entity);
        } else {
            return null;
        }
    }

}
