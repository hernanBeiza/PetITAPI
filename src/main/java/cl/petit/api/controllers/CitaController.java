package cl.petit.api.controllers;

import cl.petit.api.models.dtos.*;
import cl.petit.api.services.CitaService;
import cl.petit.api.services.OrigenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequestMapping(path = "/api/cita")
@RestController
public class CitaController {

    @Autowired
    private CitaService citaService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<CitaDTO> encontradas = this.citaService.obtener();
        if (encontradas != null) {
            result.put("result", true);
            result.put("mensaje", "Citas  encontradas");
            result.put("citas", encontradas);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontraron origenes");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idCita}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idCita) {
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idCita==null){
            enviar = false;
            errores +="ID de la cita";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            CitaDTO buscar = new CitaDTO();
            buscar.setIdCita(idCita);
            CitaDTO encontrada = this.citaService.obtenerConID(buscar);
            if (encontrada != null) {
                result.put("result", true);
                result.put("mensaje", "Cita encontrada");
                result.put("cita", encontrada);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró cita con estos datos...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/mascota/{rutMascota}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> obtenerConRutMascota(@PathVariable String rutMascota){
        boolean enviar = true;
        String errores = "Te faltó: \n";
        if(rutMascota==null) {
            enviar = false;
            errores += "Rut de la mascota";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            MascotaDTO mascotaDTO = new MascotaDTO();
            mascotaDTO.setRutMascota(rutMascota);
            CitaDTO encontrada = this.citaService.obtenerConRutMascota(mascotaDTO);
            if (encontrada != null) {
                result.put("result", true);
                result.put("mensaje", "Cita encontrada");
                result.put("cita", encontrada);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró cita con estos datos...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/especialista/{idEspecialista}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> obtenerConIDEspecialista(@PathVariable Integer idEspecialista){
        boolean enviar = true;
        String errores = "Te faltó: \n";
        if(idEspecialista==null) {
            enviar = false;
            errores += "ID del especialista";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            EspecialistaDTO especialistaDTO = new EspecialistaDTO();
            especialistaDTO.setIdEspecialista(idEspecialista.longValue());
            CitaDTO encontrada = this.citaService.obtenerConIDEspecialista(especialistaDTO);
            if (encontrada != null) {
                result.put("result", true);
                result.put("mensaje", "Cita encontrada");
                result.put("cita", encontrada);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró cita con estos datos...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path={"","/"}, method={RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> guardar(
        @RequestParam(value="rutMascota",required=false) String rutMascota,
        @RequestParam(value="idEspecialista",required=false) Integer idEspecialista,
        @RequestParam(value="idOrigen",required=false) Integer idOrigen){
        boolean enviar = true;
        String errores = "Te faltó: \n";
        if(rutMascota==null) {
            enviar = false;
            errores += "rut de la mascota\n";
        }
        if(idEspecialista==null) {
            enviar = false;
            errores += "ID del especialista\n";
        }
        if(idOrigen==null) {
            enviar = false;
            errores += "Origen de la cita\n";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            CitaDTO citaDTO = new CitaDTO();
            MascotaDTO mascotaDTO = new MascotaDTO();
            mascotaDTO.setRutMascota(rutMascota);
            citaDTO.setMascotaDTO(mascotaDTO);

            EspecialistaDTO especialistaDTO = new EspecialistaDTO();
            especialistaDTO.setIdEspecialista(idEspecialista.longValue());
            citaDTO.setEspecialistaDTO(especialistaDTO);

            OrigenDTO origenDTO = new OrigenDTO();
            origenDTO.setIdOrigen(idOrigen.longValue());
            citaDTO.setOrigenDTO(origenDTO);
            citaDTO.setValid(1);
            boolean guardada = this.citaService.guardar(citaDTO);
            if (guardada) {
                result.put("result", true);
                result.put("mensaje", "Cita guardada correctamente");
            } else {
                result.put("result", false);
                result.put("errores", "No se guardó la cita");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idCita}", method={RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> editar(
            @PathVariable Integer idCita,
            @RequestParam(value="rutMascota",required=false) String rutMascota,
            @RequestParam(value="idEspecialista",required=false) Integer idEspecialista,
            @RequestParam(value="idOrigen",required=false) Integer idOrigen,
            @RequestParam(value="valid",required=false) Integer valid
            ){
        boolean enviar = true;
        String errores = "Te faltó: \n";
        if(idCita==null) {
            enviar = false;
            errores += "id de la cita\n";
        }

        if(rutMascota==null) {
            enviar = false;
            errores += "rut de la mascota\n";
        }
        if(idEspecialista==null) {
            enviar = false;
            errores += "ID del especialista\n";
        }
        if(idOrigen==null) {
            enviar = false;
            errores += "Origen de la cita\n";
        }
        if(valid==null) {
            enviar = false;
            errores += "Valid de la cita\n";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setIdCita(idCita.longValue());
            MascotaDTO mascotaDTO = new MascotaDTO();
            mascotaDTO.setRutMascota(rutMascota);
            citaDTO.setMascotaDTO(mascotaDTO);

            EspecialistaDTO especialistaDTO = new EspecialistaDTO();
            especialistaDTO.setIdEspecialista(idEspecialista.longValue());
            citaDTO.setEspecialistaDTO(especialistaDTO);

            OrigenDTO origenDTO = new OrigenDTO();
            origenDTO.setIdOrigen(idOrigen.longValue());
            citaDTO.setOrigenDTO(origenDTO);
            citaDTO.setValid(valid);

            boolean editada = this.citaService.editar(citaDTO);
            if (editada) {
                result.put("result", true);
                result.put("mensaje", "Cita modificada correctamente");
            } else {
                result.put("result", false);
                result.put("errores", "No se modificó la cita");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(path="/{idCita}", method={RequestMethod.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer idCita){
            boolean enviar = true;
            String errores = "Te faltó: \n";
            if(idCita==null) {
                enviar = false;
                errores += "ID de la cita";
            }
            Map<String, Object> result = new HashMap<String,Object>();
            if(enviar) {
                CitaDTO citaDTO = new CitaDTO();
                citaDTO.setIdCita(idCita.longValue());
                boolean eliminada = this.citaService.eliminar(citaDTO);
                if (eliminada) {
                    result.put("result", true);
                    result.put("mensaje", "Cita eliminada");
                } else {
                    result.put("result", false);
                    result.put("errores", "No se eliminó la cita con esos datos");
                }
            } else {
                result.put("result", false);
                result.put("errores", errores);
            }
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        }
}

