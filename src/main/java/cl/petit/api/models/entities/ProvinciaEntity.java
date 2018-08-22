package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Provincia")
public class ProvinciaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProvincia")
    private Long idProvincia;

    @Column(name="idRegion")
    private Long idRegion;

    @Column(name = "strDescripcion")
    private String strDescripcion;

    @Column(name = "numComunas")
    private Long numComunas;

    @Column(name = "valid")
    private int valid;

    public Long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    public Long getNumComunas() {
        return numComunas;
    }

    public void setNumComunas(Long numComunas) {
        this.numComunas = numComunas;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "ProvinciaEntity{" +
                "idProvincia=" + idProvincia +
                ", idRegion=" + idRegion +
                ", strDescripcion='" + strDescripcion + '\'' +
                ", numComunas=" + numComunas +
                ", valid=" + valid +
                '}';
    }
}