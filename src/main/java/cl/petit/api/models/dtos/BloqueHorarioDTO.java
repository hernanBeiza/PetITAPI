package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.BloqueHorarioEntity;
import cl.petit.api.models.entities.ComunaEntity;

import java.util.Date;

public class BloqueHorarioDTO {

    private Long idBloqueHorario;
    private String horaInicio;
    private String horaTermino;
    private int valid;

    public BloqueHorarioDTO() { }

    public BloqueHorarioDTO(BloqueHorarioEntity entity){
        this.idBloqueHorario = entity.getIdBloqueHorario();
        this.horaInicio = entity.getHoraInicio();
        this.horaTermino = entity.getHoraTermino();
        this.valid = entity.getValid();
    }

    public Long getIdBloqueHorario() {
        return idBloqueHorario;
    }

    public void setIdBloqueHorario(Long idBloqueHorario) {
        this.idBloqueHorario = idBloqueHorario;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "BloqueHorarioDTO{" +
                "idBloqueHorario=" + idBloqueHorario +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaTermino='" + horaTermino + '\'' +
                ", valid=" + valid +
                '}';
    }
}
