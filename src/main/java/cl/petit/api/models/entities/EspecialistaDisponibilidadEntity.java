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
    @Column(name = "idEspecialista")
    private Long idEspecialista;
    @ManyToOne
    @JoinColumn(name="idBloqueHorario")
    private BloqueHorarioEntity bloqueHorarioEntity;

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

    public BloqueHorarioEntity getBloqueHorarioEntity() {
        return bloqueHorarioEntity;
    }

    public void setBloqueHorarioEntity(BloqueHorarioEntity bloqueHorarioEntity) {
        this.bloqueHorarioEntity = bloqueHorarioEntity;
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
                ", bloqueHorarioEntity=" + bloqueHorarioEntity +
                ", fecha='" + fecha + '\'' +
                ", valid=" + valid +
                '}';
    }
}