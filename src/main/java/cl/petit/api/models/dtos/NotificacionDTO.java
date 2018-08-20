package cl.petit.api.models.dtos;

import cl.petit.api.models.entities.NotificacionEntity;

public class NotificacionDTO {

    private Long idNotificacion;
    private UsuarioDTO usuario;
    private String titulo;
    private String imagen;
    private String mensaje;
    private String fecha;
    private int valid;

    public NotificacionDTO() { }

    public NotificacionDTO(NotificacionEntity entity){
        this.idNotificacion = entity.getIdNotificacion();
        this.usuario = new UsuarioDTO(entity.getUsuarioEntity());
        this.titulo = entity.getTitulo();
        this.imagen = entity.getImagen();
        this.mensaje = entity.getMensaje();
        this.fecha = entity.getFecha();
        this.valid = entity.getValid();
    }

    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
        return "NotificacionDTO{" +
                "idNotificacion=" + idNotificacion +
                ", usuario=" + usuario +
                ", titulo='" + titulo + '\'' +
                ", imagen='" + imagen + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fecha='" + fecha + '\'' +
                ", valid=" + valid +
                '}';
    }
}
