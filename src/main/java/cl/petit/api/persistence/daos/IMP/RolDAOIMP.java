package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.RolEntity;
import cl.petit.api.models.entities.UsuarioEntity;
import cl.petit.api.persistence.daos.RolDAO;
import cl.petit.api.persistence.daos.UsuarioDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Repository("RolDAO")
public class RolDAOIMP implements RolDAO {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public ArrayList<RolEntity> obtener() {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT r FROM RolEntity AS r";
        System.out.println(query);
        try {
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (ArrayList<RolEntity>) entityManager.createQuery(query).getResultList();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public RolEntity obtenerConID(Long idRol) {
        String query = "SELECT r FROM RolEntity AS r WHERE r.idRol = "+idRol;
        System.out.println(query);
        try {
            return (RolEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

}
