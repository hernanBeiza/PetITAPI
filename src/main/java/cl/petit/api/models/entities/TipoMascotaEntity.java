package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TipoMascota")
public class TipoMascotaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoMascota")
    private Long idTipoMascota;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "valid")
    private int valid;

    public Long getIdTipoMascota() {
        return idTipoMascota;
    }

    public void setIdTipoMascota(Long idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
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
        return "TipoMascotaEntity{" +
                "idTipoMascota=" + idTipoMascota +
                ", nombre='" + nombre + '\'' +
                ", valid=" + valid +
                '}';
    }
}