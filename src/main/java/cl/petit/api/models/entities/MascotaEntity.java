package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Mascota")
public class MascotaEntity implements Serializable {
    //TODO Tener cuidado con la generación en el llave primaria, en este caso no es autoincremental y la maneja el usuario (rut único)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rutMascota")
    private String rutMascota;
    @ManyToOne
    @JoinColumn(name="idTipoMascota")
    private TipoMascotaEntity tipoMascota;
    @ManyToOne
    @JoinColumn(name="idRaza")
    private RazaEntity RazaEntity;
    @ManyToOne
    @JoinColumn(name="rutDueno")
    private DuenoMascotaEntity duenoMascota;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "peso")
    private int peso;
    @Column(name = "edad")
    private int edad;
    @Column(name = "valid")
    private int valid;

    public String getRutMascota() {
        return rutMascota;
    }

    public void setRutMascota(String rutMascota) {
        this.rutMascota = rutMascota;
    }

    public TipoMascotaEntity getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascotaEntity tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public cl.petit.api.models.entities.RazaEntity getRazaEntity() {
        return RazaEntity;
    }

    public void setRazaEntity(cl.petit.api.models.entities.RazaEntity razaEntity) {
        RazaEntity = razaEntity;
    }

    public DuenoMascotaEntity getDuenoMascota() {
        return duenoMascota;
    }

    public void setDuenoMascota(DuenoMascotaEntity duenoMascota) {
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
        return "MascotaEntity{" +
                "rutMascota='" + rutMascota + '\'' +
                ", tipoMascota=" + tipoMascota +
                ", RazaEntity=" + RazaEntity +
                ", duenoMascota=" + duenoMascota +
                ", nombre='" + nombre + '\'' +
                ", peso=" + peso +
                ", edad=" + edad +
                ", valid=" + valid +
                '}';
    }
}