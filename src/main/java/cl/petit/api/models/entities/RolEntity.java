package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "Rol")
public class RolEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idRol")
    private Long idRol;

    @Column(name="nombre")
    private String nombre;

    @Column(name="valid")
    private int valid;

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
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
        return "RolEntity{" +
                "idRol=" + idRol +
                ", nombre='" + nombre + '\'' +
                ", valid=" + valid +
                '}';
    }
}