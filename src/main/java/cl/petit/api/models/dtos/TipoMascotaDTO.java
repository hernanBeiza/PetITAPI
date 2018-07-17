package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.TipoMascotaEntity;

public class TipoMascotaDTO {

    private Long idTipoMascota;
    private String nombre;
    private int valid;

    public TipoMascotaDTO() { }

    public TipoMascotaDTO(TipoMascotaEntity entity){
        this.idTipoMascota = entity.getIdTipoMascota();
        this.nombre = entity.getNombre();
        this.valid = entity.getValid();
    }

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
        return "TipoMascotaDTO{" +
                "idTipoMascota=" + idTipoMascota +
                ", nombre='" + nombre + '\'' +
                ", valid=" + valid +
                '}';
    }
}
