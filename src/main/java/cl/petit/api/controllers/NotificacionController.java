package cl.petit.api.controllers;

import cl.petit.api.models.dtos.NotificacionDTO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.services.ArchivoService;
import cl.petit.api.services.NotificacionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(path = "/api/notificacion")
@RestController
public class NotificacionController {

    private static final Logger logger = LogManager.getLogger(NotificacionController.class);

    @Autowired
    private NotificacionService notificacionService;
    //Servicio para guardar y obtener los archivos hacia y edesde el servidor
    @Autowired
    private ArchivoService archivoService;


    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        logger.info("NotificacionController: obtener();");
        Map<String, Object> result = new HashMap<String,Object>();
        ArrayList<NotificacionDTO> notificaciones = this.notificacionService.obtener();
        if(notificaciones!=null){
            if(notificaciones.size()>0){
                result.put("result",true);
                result.put("mensajes","Notificaciones encontradas");
                result.put("notificaciones",notificaciones);
            } else {
                result.put("result",false);
                result.put("errores","No se encontraron notificaciones");
            }
        } else {
            result.put("result",false);
            result.put("errores","No se encontraron notificaciones");
        }
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idNotificacion}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable int idNotificacion) {
        logger.info("NotificacionController: obtenerConID();");
        Map<String, Object> result = new HashMap<String,Object>();
        NotificacionDTO notificacion = this.notificacionService.obtenerConID(idNotificacion);
        if(notificacion!=null){
            result.put("result",true);
            result.put("mensajes","Notificación encontrada");
            result.put("tipos",notificacion);
        } else {
            result.put("result",false);
            result.put("errores","No se encontró notificación con ese id");
        }

        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/usuario/{idUsuario}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConUsuario(@PathVariable Integer idUsuario) {
        logger.info("NotificacionController: obtenerConUsuario();");
        Map<String, Object> result = new HashMap<String,Object>();
        ArrayList<NotificacionDTO> notificaciones = this.notificacionService.obtenerConIDUsuario(idUsuario);
        if(notificaciones.size()>0){
            result.put("result",true);
            result.put("mensajes","Notificaciones encontradas");
            result.put("notificaciones",notificaciones);
        } else {
            result.put("result",false);
            result.put("errores","No se encontraron notificaciones");
        }
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/marcar/{idNotificacion}", method={RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> marcarComoLeida(@PathVariable Integer idNotificacion) {
        logger.info("NotificacionController: marcarComoLeida();");
        Map<String, Object> result = new HashMap<String,Object>();

        NotificacionDTO model = new NotificacionDTO();
        model.setIdNotificacion(idNotificacion.longValue());

        boolean resultado = this.notificacionService.marcarComoLeida(model);
        if(resultado){
            result.put("result",true);
            result.put("mensajes","Notificación marcada como leída");
        } else {
            result.put("result",false);
            result.put("errores","La notificación no se marcó como leída");
        }

        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="", method={RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> guardar(@RequestParam(value="idUsuario",required=false) Integer idUsuario,
                                                      @RequestParam(value="titulo",required=false) String titulo,
                                                      //@RequestParam(value="imagen",required=false) String imagen,
                                                      @RequestParam(value="imagen", required=false) MultipartFile imagen,
                                                      @RequestParam(value="mensaje",required=false) String mensaje,
                                                      @RequestParam(value="fecha",required=false) String fecha) {
        logger.info("NotificacionController: guardar();");


        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idUsuario==null){
            enviar = false;
            errores +="idUsuario\n";
        }
        if(titulo==null){
            enviar = false;
            errores +="Título\n";
        }
        if(imagen==null){
            enviar = false;
            errores +="Imagen\n";
        }
        if(mensaje==null){
            enviar = false;
            errores +="Mensaje\n";
        }
        if(fecha==null){
            enviar = false;
            errores +="Fecha\n";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            logger.info(imagen.getOriginalFilename());
            logger.info(imagen.getSize());

            String rutaArchivo = archivoService.guardar(imagen);
            logger.info(rutaArchivo);

            UsuarioDTO usuarioModel = new UsuarioDTO();
            usuarioModel.setIdUsuario(idUsuario.longValue());
            NotificacionDTO model = new NotificacionDTO();
            model.setUsuario(usuarioModel);
            model.setTitulo(titulo);
            model.setImagen(rutaArchivo);
            model.setMensaje(mensaje);
            model.setFecha(fecha);
            model.setValid(2);
            boolean guardado = this.notificacionService.guardar(model);
            if (guardado) {
                result.put("result", true);
                result.put("mensaje", "Notificación guardada correctamente");
            } else {
                result.put("result", false);
                result.put("errores", "No se pudo guardar la notificación");
            }
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } else {
            result.put("result",false);
            result.put("errores",errores);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="", method={RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> editar(@RequestParam(value="idNotificacion",required=false) Integer idNotificacion,
                                                     @RequestParam(value="idUsuario",required=false) Integer idUsuario,
                                                      @RequestParam(value="titulo",required=false) String titulo,
                                                      @RequestParam(value="imagen",required=false) String imagen,
                                                      @RequestParam(value="mensaje",required=false) String mensaje,
                                                      @RequestParam(value="fecha",required=false) String fecha,
                                                     @RequestParam(value="valid",required=false) Integer valid) {
        logger.info("NotificacionController: editar();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idNotificacion==null){
            enviar = false;
            errores +="idNotificacion\n";
        }
        if(idUsuario==null){
            enviar = false;
            errores +="idUsuario\n";
        }
        if(titulo==null){
            enviar = false;
            errores +="Título\n";
        }
        if(imagen==null){
            enviar = false;
            errores +="Imagen\n";
        }
        if(mensaje==null){
            enviar = false;
            errores +="Mensaje\n";
        }
        if(fecha==null){
            enviar = false;
            errores +="Fecha\n";
        }
        if(valid==null){
            enviar = false;
            errores +="Valid\n";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            UsuarioDTO usuarioModel = new UsuarioDTO();
            usuarioModel.setIdUsuario(idUsuario.longValue());
            NotificacionDTO model = new NotificacionDTO();
            model.setIdNotificacion(idNotificacion.longValue());
            model.setUsuario(usuarioModel);
            model.setTitulo(titulo);
            model.setImagen(imagen);
            model.setMensaje(mensaje);
            model.setFecha(fecha);
            model.setValid(valid);
            boolean guardado = this.notificacionService.editar(model);
            if (guardado) {
                result.put("result", true);
                result.put("mensaje", "Notificación editada correctamente");
            } else {
                result.put("result", false);
                result.put("errores", "No se pudo editar la notificación");
            }
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } else {
            result.put("result",false);
            result.put("errores",errores);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(path="/{idNotificacion}", method={RequestMethod.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> eliminar(@PathVariable Integer idNotificacion) {
        logger.debug("NotificacionController: eliminar();");
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idNotificacion==null){
            enviar = false;
            errores +="idNotificacion";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            NotificacionDTO model = new NotificacionDTO();
            model.setIdNotificacion(Long.valueOf(idNotificacion));
            boolean eliminado = this.notificacionService.eliminar(model);
            if(eliminado){
                result.put("result",true);
                result.put("mensaje","Notificación eliminada correctamente...");
            } else {
                result.put("result",false);
                result.put("errores","No se pudo eliminar la notificación con estos datos...");
            }
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            result.put("result",false);
            result.put("errores",errores);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }

    }

}
