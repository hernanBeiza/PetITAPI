package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BloqueHorario")
public class BloqueHorarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBloqueHorario")
    private Long idBloqueHorario;

    @Column(name="horaInicio")
    private String horaInicio;

    @Column(name = "horaTermino")
    private String horaTermino;

    @Column(name = "valid")
    private int valid;

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
        return "BloqueHorarioEntity{" +
                "idBloqueHorario=" + idBloqueHorario +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaTermino='" + horaTermino + '\'' +
                ", valid=" + valid +
                '}';
    }
}