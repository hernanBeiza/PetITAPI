package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.DuenoMascotaEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DuenoMascotaDTO {
    @JsonProperty("rutDueno")
    private String rutDueno;
    @JsonProperty("Usuario")
    private UsuarioDTO usuario;
    @JsonProperty("nombres")
    private String nombres;
    @JsonProperty("apellidoPaterno")
    private String apellidoPaterno;
    @JsonProperty("apellidoMaterno")
    private String apellidoMaterno;
    @JsonProperty("comuna")
    private ComunaDTO comuna;
    @JsonProperty("direccion")
    private String direccion;
    @JsonProperty("telefono")
    private String telefono;
    @JsonProperty("correo")
    private String correo;
    @JsonProperty("valid")
    private int valid;

    public DuenoMascotaDTO() { }

    public DuenoMascotaDTO(DuenoMascotaEntity entity){
        this.rutDueno = entity.getRutDueno();
        this.usuario = new UsuarioDTO(entity.getUsuarioEntity());
        this.nombres = entity.getNombres();
        this.apellidoPaterno = entity.getApellidoPaterno();
        this.apellidoMaterno = entity.getApellidoMaterno();
        this.comuna = new ComunaDTO(entity.getComuna());
        this.direccion = entity.getDireccion();
        this.telefono = entity.getTelefono();
        this.correo = entity.getCorreo();
        this.valid = entity.getValid();
    }

    public String getRutDueno() {
        return rutDueno;
    }

    public void setRutDueno(String rutDueno) {
        this.rutDueno = rutDueno;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public ComunaDTO getComuna() {
        return comuna;
    }

    public void setComuna(ComunaDTO comuna) {
        this.comuna = comuna;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "DuenoMascotaDTO{" +
                "rutDueno='" + rutDueno + '\'' +
                ", usuario=" + usuario +
                ", nombres='" + nombres + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", comuna=" + comuna +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", valid=" + valid +
                '}';
    }
}
