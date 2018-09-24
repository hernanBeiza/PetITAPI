package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.RazaDTO;
import cl.petit.api.models.entities.RazaEntity;
import cl.petit.api.persistence.daos.RazaDAO;
import cl.petit.api.persistence.daos.RolDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("RazaDAO")
public class RazaDAOIMP implements RazaDAO {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public ArrayList<RazaEntity> obtener() {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT r FROM RazaEntity AS r";
        System.out.println(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<RazaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public RazaEntity obtenerConID(RazaDTO razaDTO) {
        String query = "SELECT r FROM RazaEntity AS r WHERE r.idRaza = "+razaDTO.getIdRaza();
        System.out.println(query);
        try {
            return (RazaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

}
