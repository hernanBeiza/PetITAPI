package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cita")
public class CitaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCita")
    private Long idCita;

    @ManyToOne
    @JoinColumn(name="rutMascota")
    private MascotaEntity mascotaEntity;

    @ManyToOne
    @JoinColumn(name="idEspecialista")
    private EspecialistaEntity especialistaEntity;

    @ManyToOne
    @JoinColumn(name="idOrigen")
    private OrigenEntity origenEntity;

    @Column(name = "valid")
    private int valid;

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public MascotaEntity getMascotaEntity() {
        return mascotaEntity;
    }

    public void setMascotaEntity(MascotaEntity mascotaEntity) {
        this.mascotaEntity = mascotaEntity;
    }

    public EspecialistaEntity getEspecialistaEntity() {
        return especialistaEntity;
    }

    public void setEspecialistaEntity(EspecialistaEntity especialistaEntity) {
        this.especialistaEntity = especialistaEntity;
    }

    public OrigenEntity getOrigenEntity() {
        return origenEntity;
    }

    public void setOrigenEntity(OrigenEntity origenEntity) {
        this.origenEntity = origenEntity;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "CitaEntity{" +
                "idCita=" + idCita +
                ", mascotaEntity=" + mascotaEntity +
                ", especialistaEntity=" + especialistaEntity +
                ", origenEntity=" + origenEntity +
                ", valid=" + valid +
                '}';
    }
}