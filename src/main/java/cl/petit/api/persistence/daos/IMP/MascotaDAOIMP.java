package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.entities.MascotaEntity;
import cl.petit.api.models.entities.RolEntity;
import cl.petit.api.persistence.daos.MascotaDAO;
import cl.petit.api.persistence.daos.RolDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("MascotaDAO")
public class MascotaDAOIMP implements MascotaDAO {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public ArrayList<MascotaEntity> obtener() {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT r FROM MascotaEntity AS r";
        System.out.println(query);
        try {
            return (ArrayList<MascotaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public MascotaEntity obtenerConID(MascotaDTO dto) {
        /*
        String query = "SELECT r FROM MascotaEntity AS r WHERE r.idRol = "+dto;
        System.out.println(query);
        try {
            return (RolEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        */
        return null;
    }

}
