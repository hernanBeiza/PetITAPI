package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.models.dtos.RazaDTO;
import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.RazaEntity;
import cl.petit.api.persistence.daos.ComunaDAO;
import cl.petit.api.persistence.daos.RazaDAO;
import cl.petit.api.services.ComunaService;
import cl.petit.api.services.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class ComunaServiceIMP implements ComunaService {

    @Autowired
    private ComunaDAO comunaDAO;

    @Override
    public ArrayList<ComunaDTO> obtener() {
        ArrayList<ComunaEntity> entities = this.comunaDAO.obtener();
        if(entities!=null){
            System.out.println("Comunas encontradas");
            ArrayList<ComunaDTO> encontrados = new ArrayList<ComunaDTO>();
            for (ComunaEntity entity : entities) {
                ComunaDTO dto = new ComunaDTO(entity);
                encontrados.add(dto);
            }
            return encontrados;
        } else {
            System.out.println("No se encontraron comunas");
            return null;
        }
    }

    @Override
    public ComunaDTO obtenerConID(Long idComuna) {
        ComunaEntity entity = this.comunaDAO.obtenerConID(idComuna);
        if(entity!=null){
            System.out.println(entity.toString());
            return new ComunaDTO(entity);
        } else {
            return null;
        }
    }

}
