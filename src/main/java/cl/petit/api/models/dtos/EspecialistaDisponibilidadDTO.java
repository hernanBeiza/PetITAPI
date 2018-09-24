package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.BloqueHorarioEntity;
import cl.petit.api.models.entities.EspecialistaDisponibilidadEntity;

public class EspecialistaDisponibilidadDTO {

    private Long idEspecialistaDisponibilidad;
    private EspecialistaDTO especialistaDTO;
    private BloqueHorarioDTO bloqueHorarioDTO;
    private String fecha;
    private int valid;

    public EspecialistaDisponibilidadDTO() { }

    public EspecialistaDisponibilidadDTO(EspecialistaDisponibilidadEntity entity){
        this.idEspecialistaDisponibilidad = entity.getIdEspecialistaDisponibilidad();
        this.especialistaDTO = new EspecialistaDTO(entity.getEspecialista());
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

    public EspecialistaDTO getEspecialistaDTO() {
        return especialistaDTO;
    }

    public void setEspecialistaDTO(EspecialistaDTO especialistaDTO) {
        this.especialistaDTO = especialistaDTO;
    }

    public BloqueHorarioDTO getBloqueHorarioDTO() {
        return bloqueHorarioDTO;
    }

    public void setBloqueHorarioDTO(BloqueHorarioDTO bloqueHorarioDTO) {
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
                ", especialistaDTO=" + especialistaDTO +
                ", bloqueHorarioDTO=" + bloqueHorarioDTO +
                ", fecha='" + fecha + '\'' +
                ", valid=" + valid +
                '}';
    }
}
