package cl.petit.api.controllers;

import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.services.DuenoMascotaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(path = "/api/dueno")
@RestController
public class DuenoMascotaController {

    private static final Logger logger = LogManager.getLogger(DuenoMascotaController.class);

    @Autowired
    private DuenoMascotaService duenoMascotaService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        logger.info("obtener();");
        Map<String, Object> result = new HashMap<String,Object>();
        ArrayList<DuenoMascotaDTO> duenos = this.duenoMascotaService.obtener();
        if(duenos !=null){
            if(duenos.size()>0){
                result.put("result",true);
                result.put("duenos",duenos);
                result.put("mensajes","Dueños de mascotas encontrados");
            } else {
                result.put("result",false);
                result.put("errores","No se encontraron dueños de mascotas");
            }
        } else {
            result.put("result",false);
            result.put("errores","No se encontraron dueños de mascotas");
        }
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/buscar/nombre/{nombre}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> buscarPorNombre(@PathVariable String nombre) {
        logger.info("buscarPorNombre();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(nombre==null){
            enviar = false;
            errores +="Nombre del dueño de mascota";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            DuenoMascotaDTO model = new DuenoMascotaDTO();
            model.setNombres(nombre);
            ArrayList<DuenoMascotaDTO> dto = this.duenoMascotaService.buscarPorNombre(model);
            if (dto!=null){
                result.put("result",true);
                result.put("dueno",dto);
                result.put("mensajes","Dueño de mascota encontrado");
            } else {
                result.put("result",false);
                result.put("errores","No se encontró dueño con ese rut");
            }
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            System.out.println(errores);
            result.put("result",false);
            result.put("errores",errores);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/buscar/rut/{rut}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConRut(@PathVariable String rut) {
        logger.info("obtenerConRut();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(rut==null){
            enviar = false;
            errores +="Rut del dueño de mascota";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            DuenoMascotaDTO model = new DuenoMascotaDTO();
            model.setRutDueno(rut);
            DuenoMascotaDTO duenoMascotaDTO = this.duenoMascotaService.obtenerConRut(model);
            if (duenoMascotaDTO!=null){
                result.put("result",true);
                result.put("dueno",duenoMascotaDTO);
                result.put("mensajes","Dueño de mascota encontrado");
            } else {
                result.put("result",false);
                result.put("errores","No se encontró dueño con ese rut");
            }
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            System.out.println(errores);
            result.put("result",false);
            result.put("errores",errores);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="", method={RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> guardar(
        @RequestParam(value="idUsuario",required=false) Integer idUsuario,
        @RequestParam(value="rut",required=false) String rut,
        @RequestParam(value="nombres",required=false) String nombres,
        @RequestParam(value="apellidoPaterno",required=false) String paterno,
        @RequestParam(value="apellidoMaterno",required=false) String materno,
        @RequestParam(value="idComuna",required=false) Integer idComuna,
        @RequestParam(value="direccion",required=false) String direccion,
        @RequestParam(value="telefono",required=false) String telefono,
        @RequestParam(value="correo",required=false) String correo) {
        logger.info("guardar();");

        Map<String, Object> result = new HashMap<String,Object>();

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idUsuario==null){
            enviar = false;
            errores +="idUsuario\n";
        }
        if(rut==null){
            enviar = false;
            errores +="rut\n";
        }

        if(nombres==null){
            enviar = false;
            errores +="Nombres\n";
        }
        if(paterno==null){
            enviar = false;
            errores +="Apellido Paterno\n";
        }
        if(materno==null){
            enviar = false;
            errores +="Apellido Materno\n";
        }
        if(idComuna==null){
            enviar = false;
            errores +="Comuna\n";
        }
        if(direccion==null){
            enviar = false;
            errores +="Dirección\n";
        }
        if(telefono==null){
            enviar = false;
            errores +="Teléfono\n";
        }
        if(correo==null){
            enviar = false;
            errores +="Correo\n";
        }
        if(enviar) {

            DuenoMascotaDTO duenoMascotaDTO = new DuenoMascotaDTO();
            duenoMascotaDTO.setRutDueno(rut);
            duenoMascotaDTO.setNombres(nombres);
            duenoMascotaDTO.setApellidoPaterno(paterno);
            duenoMascotaDTO.setApellidoMaterno(materno);

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setIdUsuario(idUsuario.longValue());
            duenoMascotaDTO.setUsuario(usuarioDTO);

            ComunaDTO comunaDTO = new ComunaDTO();
            comunaDTO.setIdComuna(idComuna.longValue());
            duenoMascotaDTO.setComuna(comunaDTO);

            duenoMascotaDTO.setDireccion(direccion);
            duenoMascotaDTO.setTelefono(telefono);
            duenoMascotaDTO.setCorreo(correo);
            duenoMascotaDTO.setValid(1);

            boolean guardado = this.duenoMascotaService.guardar(duenoMascotaDTO);
            if (guardado) {
                result.put("result", true);
                result.put("mensaje", "Dueño de mascota guardado correctamente");
            } else {
                result.put("result", false);
                result.put("errores", "No se pudo guardar el dueño de mascota");
            }
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } else {
            result.put("result",false);
            result.put("errores",errores);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/{rutDueno}", method={RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> editar(
        @PathVariable(value="rutDueno",required=false) String rut,
        @RequestParam(value="idUsuario",required=false) Integer idUsuario,
        @RequestParam(value="nombres",required=false) String nombres,
        @RequestParam(value="apellidoPaterno",required=false) String paterno,
        @RequestParam(value="apellidoMaterno",required=false) String materno,
        @RequestParam(value="idComuna",required=false) Integer idComuna,
        @RequestParam(value="direccion",required=false) String direccion,
        @RequestParam(value="telefono",required=false) String telefono,
        @RequestParam(value="correo",required=false) String correo,
        @RequestParam(value="valid",required=false) Integer valid) {
        logger.info("editar();");

        Map<String, Object> result = new HashMap<String, Object>();

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if (idUsuario == null) {
            enviar = false;
            errores += "idUsuario\n";
        }
        if (rut == null) {
            enviar = false;
            errores += "rut\n";
        }
        if (nombres == null) {
            enviar = false;
            errores += "Nombres\n";
        }
        if (paterno == null) {
            enviar = false;
            errores += "Apellido Paterno\n";
        }
        if (materno == null) {
            enviar = false;
            errores += "Apellido Materno\n";
        }
        if (idComuna == null) {
            enviar = false;
            errores += "Comuna\n";
        }
        if (direccion == null) {
            enviar = false;
            errores += "Dirección\n";
        }
        if (telefono == null) {
            enviar = false;
            errores += "Teléfono\n";
        }
        if (correo == null) {
            enviar = false;
            errores += "Correo\n";
        }
        if (valid == null) {
            enviar = false;
            errores += "Valid\n";
        }
        if (enviar) {

            DuenoMascotaDTO duenoMascotaDTO = new DuenoMascotaDTO();
            duenoMascotaDTO.setRutDueno(rut);
            duenoMascotaDTO.setNombres(nombres);
            duenoMascotaDTO.setApellidoPaterno(paterno);
            duenoMascotaDTO.setApellidoMaterno(materno);

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setIdUsuario(idUsuario.longValue());
            duenoMascotaDTO.setUsuario(usuarioDTO);

            ComunaDTO comunaDTO = new ComunaDTO();
            comunaDTO.setIdComuna(idComuna.longValue());
            duenoMascotaDTO.setComuna(comunaDTO);

            duenoMascotaDTO.setDireccion(direccion);
            duenoMascotaDTO.setTelefono(telefono);
            duenoMascotaDTO.setCorreo(correo);
            duenoMascotaDTO.setValid(valid);
            boolean guardado = this.duenoMascotaService.editar(duenoMascotaDTO);
            if (guardado) {
                result.put("result", true);
                result.put("mensaje", "Dueño de mascota editado correctamente");
            } else {
                result.put("result", false);
                result.put("errores", "No se pudo editar el dueño de mascota");
            }
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } else {
            result.put("result", false);
            result.put("errores", errores);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/{rutDueno}", method={RequestMethod.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> eliminar(@PathVariable String rutDueno) {
        logger.info("eliminar();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(rutDueno==null){
            enviar = false;
            errores +="rut del dueño";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            DuenoMascotaDTO model = new DuenoMascotaDTO();
            model.setRutDueno(rutDueno);
            boolean eliminado = this.duenoMascotaService.eliminar(model);
            if(eliminado){
                result.put("result",true);
                result.put("mensaje","Dueño de mascota eliminado correctamente...");
            } else {
                result.put("result",false);
                result.put("errores","No se pudo eliminar el dueño de mascota...");
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
