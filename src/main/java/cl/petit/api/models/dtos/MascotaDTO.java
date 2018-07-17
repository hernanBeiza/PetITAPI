package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.MascotaEntity;
import cl.petit.api.models.entities.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MascotaDTO {

    private String rutMascota;
    @JsonProperty("TipoMascota")
    private TipoMascotaDTO tipoMascota;
    @JsonProperty("Raza")
    private RazaDTO raza;
    @JsonProperty("DuenoMascota")
    private DuenoMascotaDTO duenoMascota;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("peso")
    private int peso;
    @JsonProperty("edad")
    private int edad;
    @JsonProperty("valid")
    private int valid;

    public MascotaDTO() { }

    public MascotaDTO(MascotaEntity entity){
        this.rutMascota = entity.getRutMascota();
        this.tipoMascota = new TipoMascotaDTO(entity.getTipoMascota());
        this.raza = new RazaDTO(entity.getRazaEntity());
        this.duenoMascota = new DuenoMascotaDTO(entity.getDuenoMascota());
        this.nombre = entity.getNombre();
        this.peso = entity.getPeso();
        this.edad = entity.getEdad();
        this.valid = entity.getValid();
    }

}
