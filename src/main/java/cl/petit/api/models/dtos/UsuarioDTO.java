package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.UsuarioEntity;

public class UsuarioDTO {

    private Long idusuario;
    private String user;
    private String pass;
    private String nombre;
    private int valid;

    public UsuarioDTO() { }

    public UsuarioDTO(UsuarioEntity entity){
        this.idusuario = entity.getIdusuario();
        this.user = entity.getUser();
        //this.pass = entity.getPass();
        this.nombre = entity.getNombre();
        this.valid = entity.getValid();
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
