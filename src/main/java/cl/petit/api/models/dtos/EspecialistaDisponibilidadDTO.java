package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.BloqueHorarioEntity;
import cl.petit.api.models.entities.EspecialistaDisponibilidadEntity;

import java.util.ArrayList;

public class EspecialistaDisponibilidadDTO {

    private Long idEspecialistaDisponibilidad;
    private Long idEspecialista;
    private BloqueHorarioDTO bloqueHorarioDTO;
    private String fecha;
    private int valid;

    public EspecialistaDisponibilidadDTO() { }

    public EspecialistaDisponibilidadDTO(EspecialistaDisponibilidadEntity entity){
        System.out.println(entity);
        this.idEspecialistaDisponibilidad = entity.getIdEspecialistaDisponibilidad();
        this.idEspecialista = entity.getIdEspecialista();
        this.bloqueHorarioDTO = new BloqueHorarioDTO(entity.getBloqueHorario());
        this.fecha = entity.getFecha();
        this.valid = entity.getValid();
    }

    public Long getIdEspecialistaDisponibilidad() {
        return idEspecialistaDisponibilidad;
    }

    public void setIdEspecialistaDisponibilidad(Long idEspecialistaDisponibilidad) {
        this.idEspecialistaDisponibilidad = idEspecialistaDisponibilidad;
    }

    public Long getIdEspecialista() {
        return idEspecialista;
    }

    public void setIdEspecialista(Long idEspecialista) {
        this.idEspecialista = idEspecialista;
    }

    public BloqueHorarioDTO getBloqueHorarioDTO() {
        return bloqueHorarioDTO;
    }

    public void setBloquesHorarioDTO(BloqueHorarioDTO bloqueHorarioDTO) {
        this.bloqueHorarioDTO = bloqueHorarioDTO;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "EspecialistaDisponibilidadDTO{" +
                "idEspecialistaDisponibilidad=" + idEspecialistaDisponibilidad +
                ", idEspecialista=" + idEspecialista +
                ", bloqueHorarioDTO=" + bloqueHorarioDTO +
                ", fecha='" + fecha + '\'' +
                ", valid=" + valid +
                '}';
    }
}
