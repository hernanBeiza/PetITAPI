package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.CitaEntity;

public class CitaDTO {

    private Long idCita;
    private MascotaDTO mascotaDTO;
    private EspecialistaDTO especialistaDTO;
    private OrigenDTO origenDTO;
    private int valid;

    public CitaDTO() { }

    public CitaDTO(CitaEntity entity){
        this.idCita = entity.getIdCita();
        this.mascotaDTO = new MascotaDTO(entity.getMascotaEntity());
        this.especialistaDTO = new EspecialistaDTO(entity.getEspecialistaEntity());
        this.origenDTO = new OrigenDTO(entity.getOrigenEntity());
        this.valid = entity.getValid();
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public MascotaDTO getMascotaDTO() {
        return mascotaDTO;
    }

    public void setMascotaDTO(MascotaDTO mascotaDTO) {
        this.mascotaDTO = mascotaDTO;
    }

    public EspecialistaDTO getEspecialistaDTO() {
        return especialistaDTO;
    }

    public void setEspecialistaDTO(EspecialistaDTO especialistaDTO) {
        this.especialistaDTO = especialistaDTO;
    }

    public OrigenDTO getOrigenDTO() {
        return origenDTO;
    }

    public void setOrigenDTO(OrigenDTO origenDTO) {
        this.origenDTO = origenDTO;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "CitaDTO{" +
                "idCita=" + idCita +
                ", mascotaDTO=" + mascotaDTO +
                ", especialistaDTO=" + especialistaDTO +
                ", origenDTO=" + origenDTO +
                ", valid=" + valid +
                '}';
    }
}
