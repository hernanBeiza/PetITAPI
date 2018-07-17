package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.RolEntity;

public class RolDTO {

    private Long idRol;
    private String nombre;
    private int valid;

    public RolDTO() { }

    public RolDTO(RolEntity entity){
        this.idRol = entity.getIdRol();
        this.nombre = entity.getNombre();
        this.valid = entity.getValid();
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
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
        return "RolDTO{" +
                "idRol=" + idRol +
                ", nombre='" + nombre + '\'' +
                ", valid=" + valid +
                '}';
    }
}
