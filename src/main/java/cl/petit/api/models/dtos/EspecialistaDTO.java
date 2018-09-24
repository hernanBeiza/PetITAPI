package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.EspecialistaEntity;

public class EspecialistaDTO {

    private Long idEspecialista;
    private EspecialidadDTO especialidadDTO;
    private ComunaDTO comunaDTO;
    private String rut;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String direccion;
    private String telefono;
    private int valid;

    public EspecialistaDTO() { }

    public EspecialistaDTO(EspecialistaEntity entity){
        this.idEspecialista = entity.getIdEspecialista();
        this.especialidadDTO = new EspecialidadDTO(entity.getEspecialidad());
        this.comunaDTO = new ComunaDTO(entity.getComuna());
        this.nombres = entity.getNombres();
        this.apellidoPaterno = entity.getApellidoPaterno();
        this.apellidoMaterno = entity.getApellidoMaterno();
        this.correo = entity.getCorreo();
        this.direccion = entity.getDireccion();
        this.telefono = entity.getTelefono();
        this.valid = entity.getValid();
    }

    public Long getIdEspecialista() {
        return idEspecialista;
    }

    public void setIdEspecialista(Long idEspecialista) {
        this.idEspecialista = idEspecialista;
    }

    public EspecialidadDTO getEspecialidadDTO() {
        return especialidadDTO;
    }

    public void setEspecialidadDTO(EspecialidadDTO especialidadDTO) {
        this.especialidadDTO = especialidadDTO;
    }

    public ComunaDTO getComunaDTO() {
        return comunaDTO;
    }

    public void setComunaDTO(ComunaDTO comunaDTO) {
        this.comunaDTO = comunaDTO;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "EspecialistaDTO{" +
                "idEspecialista=" + idEspecialista +
                ", Especialidad=" + especialidadDTO +
                ", Comuna=" + comunaDTO +
                ", rut='" + rut + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", valid=" + valid +
                '}';
    }
}
