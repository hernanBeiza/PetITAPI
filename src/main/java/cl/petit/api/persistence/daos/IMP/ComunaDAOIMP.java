package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.RazaEntity;
import cl.petit.api.persistence.daos.ComunaDAO;
import cl.petit.api.persistence.daos.RazaDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository("ComunaDAO")
public class ComunaDAOIMP implements ComunaDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<ComunaEntity> obtener() {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT r FROM ComunaEntity AS r";
        System.out.println(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<ComunaEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ComunaEntity obtenerConID(Long idComuna) {
        String query = "SELECT r FROM ComunaEntity AS r WHERE r.idComuna = "+idComuna;
        System.out.println(query);
        try {
            return (ComunaEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

}
