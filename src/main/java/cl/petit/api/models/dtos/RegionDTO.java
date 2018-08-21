package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.RegionEntity;

public class RegionDTO {

    private Long idRegion;
    private String strDescripcion;
    private String strRomano;
    private Long numProvincias;
    private String numComunas;
    private int valid;

    public RegionDTO() { }

    public RegionDTO(RegionEntity entity){
        this.idRegion = entity.getIdRegion();
        this.strDescripcion = entity.getStrDescripcion();
        this.strRomano = entity.getStrRomano();
        this.numProvincias = entity.getNumProvincias();
        this.numComunas = entity.getNumComunas();
        this.valid = entity.getValid();
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
        return "RegionDTO{" +
                "idRegion=" + idRegion +
                ", strDescripcion='" + strDescripcion + '\'' +
                ", strRomano='" + strRomano + '\'' +
                ", numProvincias=" + numProvincias +
                ", numComunas='" + numComunas + '\'' +
                ", valid=" + valid +
                '}';
    }
}
