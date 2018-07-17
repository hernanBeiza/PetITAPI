package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.ComunaEntity;

public class ComunaDTO {

    private Long idComuna;
    private Long idProvincia;
    private String strDescripcion;
    private int valid;

    public ComunaDTO() { }

    public ComunaDTO(ComunaEntity entity){
        this.idComuna = entity.getIdComuna();
        this.idProvincia = entity.getIdProvincia();
        this.strDescripcion = entity.getStrDescripcion();
        this.valid = entity.getValid();
    }

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
        return "ComunaDTO{" +
                "idComuna=" + idComuna +
                ", idProvincia=" + idProvincia +
                ", strDescripcion='" + strDescripcion + '\'' +
                ", valid=" + valid +
                '}';
    }
}
