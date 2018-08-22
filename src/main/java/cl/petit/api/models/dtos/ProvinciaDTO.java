package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.ProvinciaEntity;

public class ProvinciaDTO {

    private Long idProvincia;
    private Long idRegion;
    private String strDescripcion;
    private Long numComunas;
    private int valid;

    public ProvinciaDTO() { }

    public ProvinciaDTO(ProvinciaEntity entity){
        this.idProvincia = entity.getIdProvincia();
        this.idRegion = entity.getIdRegion();
        this.strDescripcion = entity.getStrDescripcion();
        this.numComunas = entity.getNumComunas();
        this.valid = entity.getValid();
    }

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
        return "ProvinciaDTO{" +
                "idProvincia=" + idProvincia +
                ", idRegion=" + idRegion +
                ", strDescripcion='" + strDescripcion + '\'' +
                ", numComunas=" + numComunas +
                ", valid=" + valid +
                '}';
    }
}
