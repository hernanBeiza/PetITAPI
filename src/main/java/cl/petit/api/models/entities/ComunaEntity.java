package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Comuna")
public class ComunaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComuna")
    private Long idComuna;

    @Column(name="idProvincia")
    private Long idProvincia;

    @Column(name = "strDescripcion")
    private String strDescripcion;

    @Column(name = "valid")
    private int valid;

    public Long getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Long idComuna) {
        this.idComuna = idComuna;
    }

    public Long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "ComunaEntity{" +
                "idComuna=" + idComuna +
                ", idProvincia=" + idProvincia +
                ", strDescripcion='" + strDescripcion + '\'' +
                ", valid=" + valid +
                '}';
    }

}