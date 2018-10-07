package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.MascotaEntity;
import cl.petit.api.models.entities.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MascotaDTO {

    @JsonProperty("rutMascota")
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
        this.duenoMascota = new DuenoMascotaDTO(entity.getDuenoMascotaEntity());
        this.nombre = entity.getNombre();
        this.peso = entity.getPeso();
        this.edad = entity.getEdad();
        this.valid = entity.getValid();
    }

    public String getRutMascota() {
        return rutMascota;
    }

    public void setRutMascota(String rutMascota) {
        this.rutMascota = rutMascota;
    }

    public TipoMascotaDTO getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascotaDTO tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public RazaDTO getRaza() {
        return raza;
    }

    public void setRaza(RazaDTO raza) {
        this.raza = raza;
    }

    public DuenoMascotaDTO getDuenoMascota() {
        return duenoMascota;
    }

    public void setDuenoMascota(DuenoMascotaDTO duenoMascota) {
        this.duenoMascota = duenoMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "MascotaDTO{" +
                "rutMascota='" + rutMascota + '\'' +
                ", tipoMascota=" + tipoMascota +
                ", raza=" + raza +
                ", duenoMascota=" + duenoMascota +
                ", nombre='" + nombre + '\'' +
                ", peso=" + peso +
                ", edad=" + edad +
                ", valid=" + valid +
                '}';
    }
}
