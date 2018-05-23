package cl.petit.api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity // This tells Hibernate to make a table out of this class
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idusuario;
    private String user;
    private String pass;


    public UsuarioModel(Integer idusuario, String user, String pass) {
        this.idusuario = idusuario;
        this.user = user;
        this.pass = pass;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "idusuario=" + idusuario +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
