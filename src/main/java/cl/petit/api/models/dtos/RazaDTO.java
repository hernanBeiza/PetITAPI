package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.RazaEntity;

public class RazaDTO {

    private Long idRaza;
    private String nombre;
    private int valid;

    public RazaDTO() { }

    public RazaDTO(RazaEntity entity){
        this.idRaza = entity.getIdRaza();
        this.nombre = entity.getNombre();
        this.valid = entity.getValid();
    }

    public Long getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(Long idRaza) {
        this.idRaza = idRaza;
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
        return "RazaDTO{" +
                "idRaza=" + idRaza +
                ", nombre='" + nombre + '\'' +
                ", valid=" + valid +
                '}';
    }
}
