package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Raza")
public class RazaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idRaza")
    private Long idRaza;

    @Column(name="nombre")
    private String nombre;

    @Column(name="valid")
    private int valid;

    public Long getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(Long idRaza) {
        this.idRaza = idRaza;
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
        return "RazaEntity{" +
                "idRaza=" + idRaza +
                ", nombre='" + nombre + '\'' +
                ", valid=" + valid +
                '}';
    }
}