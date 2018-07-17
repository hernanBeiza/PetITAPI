package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.entities.DuenoMascotaEntity;
import cl.petit.api.persistence.daos.DuenoMascotaDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("DuenoMascotaDAO")
public class DuenoMascotaDAOIMP implements DuenoMascotaDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<DuenoMascotaEntity> obtener() {
        System.out.println("DuenoMascotaDAOIMP: obtener();");
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT dm FROM DuenoMascotaEntity AS dm";
        System.out.println(query);
        try {
            return (ArrayList<DuenoMascotaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public DuenoMascotaEntity obtenerConRut(DuenoMascotaDTO dto) {
        System.out.println("DuenoMascotaDAOIMP: obtenerConRut();");
        String query = "SELECT r FROM DuenoMascotaEntity AS r WHERE r.rutDueno = '"+dto.getRutDueno()+"'";
        System.out.println(query);
        try {
            return (DuenoMascotaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

}
