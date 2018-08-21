package cl.petit.api.services.IMP;

import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.dtos.TipoMascotaDTO;
import cl.petit.api.models.entities.TipoMascotaEntity;
import cl.petit.api.persistence.daos.TipoMascotaDAO;
import cl.petit.api.services.TipoMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service
public class TipoMascotaServiceIMP implements TipoMascotaService {

    @Autowired
    private TipoMascotaDAO tipoMascotaDAO;

    @Override
    public ArrayList<TipoMascotaDTO> obtener() {
        ArrayList<TipoMascotaEntity> entities = this.tipoMascotaDAO.obtener();
        if(entities!=null){
            System.out.println("Tipo de mascotas encontradas");
            ArrayList<TipoMascotaDTO> encontrados = new ArrayList<TipoMascotaDTO>();
            for (TipoMascotaEntity entity : entities) {
                TipoMascotaDTO dto = new TipoMascotaDTO(entity);
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
    public TipoMascotaDTO obtenerConID(TipoMascotaDTO model) {
        TipoMascotaEntity entity = this.tipoMascotaDAO.obtenerConID(model);
        if(entity!=null){
            System.out.println("Tipo de mascota encontrada");
            TipoMascotaDTO encontrado = new TipoMascotaDTO(entity);
            return encontrado;
        } else {
            System.out.println("No se encontraron mascotas");
            return null;
        }
    }

}
