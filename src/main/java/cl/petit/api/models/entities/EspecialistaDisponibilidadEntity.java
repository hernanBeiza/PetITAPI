package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EspecialistaDisponibilidad")
public class EspecialistaDisponibilidadEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEspecialistaDisponibilidad")
    private Long idEspecialistaDisponibilidad;
    @ManyToOne
    @JoinColumn(name="idEspecialista")
    private EspecialistaEntity especialista;
    @ManyToOne
    @JoinColumn(name="idBloqueHorario")
    private BloqueHorarioEntity bloqueHorario;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "valid")
    private int valid;

    public Long getIdEspecialistaDisponibilidad() {
        return idEspecialistaDisponibilidad;
    }

    public void setIdEspecialistaDisponibilidad(Long idEspecialistaDisponibilidad) {
        this.idEspecialistaDisponibilidad = idEspecialistaDisponibilidad;
    }

    public EspecialistaEntity getEspecialista() {
        return especialista;
    }

    public void setEspecialista(EspecialistaEntity especialista) {
        this.especialista = especialista;
    }

    public BloqueHorarioEntity getBloqueHorario() {
        return bloqueHorario;
    }

    public void setBloqueHorario(BloqueHorarioEntity bloqueHorario) {
        this.bloqueHorario = bloqueHorario;
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
        return "EspecialistaDisponibilidadEntity{" +
                "idEspecialistaDisponibilidad=" + idEspecialistaDisponibilidad +
                ", especialista=" + especialista +
                ", bloqueHorario=" + bloqueHorario +
                ", fecha='" + fecha + '\'' +
                ", valid=" + valid +
                '}';
    }
}