package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.UsuarioEntity;
import com.fasterxml.jackson.annotation.*;

public class UsuarioDTO {

    private Long idUsuario;
    @JsonProperty("rol")
    private RolDTO rol;
    private String nombre;
    private String rut;
    @JsonIgnore
    private String password;
    private int valid;

    public UsuarioDTO() { }

    public UsuarioDTO(UsuarioEntity entity){
        this.idUsuario = entity.getIdUsuario();
        this.rol = new RolDTO(entity.getRolEntity());
        this.nombre = entity.getNombre();
        this.rut = entity.getRut();
        this.password = entity.getPassword();
        this.valid = entity.getValid();
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "idUsuario=" + idUsuario +
                ", rol=" + rol +
                ", nombre='" + nombre + '\'' +
                ", rut='" + rut + '\'' +
                ", password='" + password + '\'' +
                ", valid=" + valid +
                '}';
    }
}
