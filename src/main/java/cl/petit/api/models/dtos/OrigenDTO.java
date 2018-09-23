package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.OrigenEntity;

public class OrigenDTO {

    private Long idOrigen;
    private String nombre;
    private int valid;

    public OrigenDTO() { }

    public OrigenDTO(OrigenEntity entity){
        this.idOrigen = entity.getIdOrigen();
        this.nombre = entity.getNombre();
        this.valid = entity.getValid();
    }

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
        return "OrigenDTO{" +
                "idOrigen=" + idOrigen +
                ", nombre='" + nombre + '\'' +
                ", valid=" + valid +
                '}';
    }
}
