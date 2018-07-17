package cl.petit.api.persistence.daos.IMP;

import cl.petit.api.persistence.daos.UsuarioDAO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.models.entities.UsuarioEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Repository("UsuarioDAO")
public class UsuarioDAOIMP implements UsuarioDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UsuarioEntity buscar(UsuarioDTO model) {
        //Debe buscar por el nombre de la Entidad, no de la tabla de la DB
        String query = "SELECT u FROM UsuarioEntity AS u WHERE u.rut ='"+model.getRut()+"' AND u.password='"+model.getPassword()+"'";
        System.out.println(query);
        try{
            //Object result = entityManager.createQuery(query).getSingleResult();
            //System.out.println(result.toString());
            return (UsuarioEntity) entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public UsuarioEntity buscarPorNombre(String nombre) {
        String query = "SELECT u FROM UsuarioEntity AS u WHERE u.nombre LIKE '%"+nombre+"%'";
        System.out.println(query);
        try {
            return (UsuarioEntity)entityManager.createQuery(query).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public UsuarioEntity buscarPorRut(String rut) {
        String query = "SELECT u FROM UsuarioEntity AS u WHERE u.rut = "+rut;
        System.out.println(query);
        try {
            return (UsuarioEntity)entityManager.createQuery(query).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ArrayList<UsuarioEntity> obtener() {
        //String query = "SELECT u FROM UsuarioEntity AS u";
        String query = "SELECT u FROM UsuarioEntity u JOIN u.rolEntity";
        System.out.println(query);
        return (ArrayList<UsuarioEntity>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public UsuarioEntity obtenerConID(int idUsuario) {
        String query = "SELECT u FROM UsuarioEntity u WHERE idUsuario="+idUsuario;
        System.out.println(query);
        try {
            return (UsuarioEntity)entityManager.createQuery(query).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }
}
