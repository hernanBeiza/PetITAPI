package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="usuario")
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long idusuario;

    @Column(name="user")
    private String user;

    @Column(name="pass")
    private String pass;

    @Column(name="nombre")
    private String nombre;

    @Column(name="valid")
    private int valid;

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "idusuario=" + idusuario +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", nombre='" + nombre + '\'' +
                ", valid=" + valid +
                '}';
    }
}
