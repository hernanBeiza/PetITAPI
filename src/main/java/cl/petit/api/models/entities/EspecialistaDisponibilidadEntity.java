package cl.petit.api.models.entities;

import cl.petit.api.models.dtos.BloqueHorarioDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "EspecialistaDisponibilidad")
public class EspecialistaDisponibilidadEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEspecialistaDisponibilidad")
    private Long idEspecialistaDisponibilidad;
    @Column(name = "idEspecialista")
    private Long idEspecialista;
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

    public Long getIdEspecialista() {
        return idEspecialista;
    }

    public void setIdEspecialista(Long idEspecialista) {
        this.idEspecialista = idEspecialista;
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
                ", idEspecialista=" + idEspecialista +
                ", bloqueHorario=" + bloqueHorario +
                ", fecha='" + fecha + '\'' +
                ", valid=" + valid +
                '}';
    }

}