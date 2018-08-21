package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Region")
public class RegionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRegion")
    private Long idRegion;

    @Column(name="strDescripcion")
    private String strDescripcion;

    @Column(name = "strRomano")
    private String strRomano;

    @Column(name = "numProvincias")
    private Long numProvincias;

    @Column(name = "numComunas")
    private String numComunas;

    @Column(name = "valid")
    private int valid;

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

    public String getStrRomano() {
        return strRomano;
    }

    public void setStrRomano(String strRomano) {
        this.strRomano = strRomano;
    }

    public Long getNumProvincias() {
        return numProvincias;
    }

    public void setNumProvincias(Long numProvincias) {
        this.numProvincias = numProvincias;
    }

    public String getNumComunas() {
        return numComunas;
    }

    public void setNumComunas(String numComunas) {
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
        return "RegionEntity{" +
                "idRegion=" + idRegion +
                ", strDescripcion='" + strDescripcion + '\'' +
                ", strRomano='" + strRomano + '\'' +
                ", numProvincias=" + numProvincias +
                ", numComunas='" + numComunas + '\'' +
                ", valid=" + valid +
                '}';
    }
}