package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.ComunaEntity;
import cl.petit.api.models.entities.EspecialidadEntity;

public class EspecialidadDTO {

    private Long idEspecialidad;
    private String nombre;
    private int valid;

    public EspecialidadDTO() { }

    public EspecialidadDTO(EspecialidadEntity entity){
        this.idEspecialidad = entity.getIdEspecialidad();
        this.nombre = entity.getNombre();
        this.valid = entity.getValid();
    }

    public Long getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Long idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
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
        return "EspecialidadDTO{" +
                "idEspecialidad=" + idEspecialidad +
                ", nombre='" + nombre + '\'' +
                ", valid=" + valid +
                '}';
    }
}
