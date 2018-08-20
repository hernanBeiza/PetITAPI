package cl.petit.api.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Notificacion")
public class NotificacionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotificacion")
    private Long idNotificacion;
    /*
    @Column(name = "idRol")
    private Long idRol;
    */
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioEntity usuarioEntity;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "valid")
    private int valid;

    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
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
        return "NotificacionEntity{" +
                "idNotificacion=" + idNotificacion +
                ", usuarioEntity=" + usuarioEntity +
                ", titulo='" + titulo + '\'' +
                ", imagen='" + imagen + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fecha='" + fecha + '\'' +
                ", valid=" + valid +
                '}';
    }
}