package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Origen")
public class OrigenEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrigen")
    private Long idOrigen;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "valid")
    private int valid;

    public Long getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Long idOrigen) {
        this.idOrigen = idOrigen;
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
        return "OrigenEntity{" +
                "idOrigen=" + idOrigen +
                ", nombre='" + nombre + '\'' +
                ", valid=" + valid +
                '}';
    }
}