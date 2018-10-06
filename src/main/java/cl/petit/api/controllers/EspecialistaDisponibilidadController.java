package cl.petit.api.controllers;

import cl.petit.api.models.dtos.BloqueHorarioDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.models.dtos.EspecialistaDisponibilidadDTO;
import cl.petit.api.services.BloqueHorarioService;
import cl.petit.api.services.EspecialistaDisponibilidadService;
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


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequestMapping(path = "/api/disponibilidad")
@RestController
public class EspecialistaDisponibilidadController {

    private static final Logger logger = LogManager.getLogger(EspecialistaDisponibilidadController.class);

    @Autowired
    private EspecialistaDisponibilidadService especialistaDisponibilidadService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        logger.info("obtener();");
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<EspecialistaDisponibilidadDTO> encontrados = this.especialistaDisponibilidadService.obtener();
        if (encontrados != null) {
            result.put("result", true);
            result.put("mensaje", "Disponibilidad de especialista encontrados");
            result.put("bloques", encontrados);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontraron disponibiliades de especialistas");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idEspecialistaDisponibilidad}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idEspecialistaDisponibilidad) {
        logger.info("obtenerConID();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idEspecialistaDisponibilidad==null){
            enviar = false;
            errores +="ID de la disponibilidad";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            EspecialistaDisponibilidadDTO model = new EspecialistaDisponibilidadDTO();
            model.setIdEspecialistaDisponibilidad(idEspecialistaDisponibilidad);
            EspecialistaDisponibilidadDTO encontrado = this.especialistaDisponibilidadService.obtenerConID(model);
            if (encontrado != null) {
                result.put("result", true);
                result.put("mensaje", "Disponibilidad de horario encontrada");
                result.put("bloque", encontrado);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró disponibilidad de especialista con ese id...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(path="/especialista/{idEspecialista}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConIdEspecialista(@PathVariable Long idEspecialista) {
        logger.info("obtenerConIDEspecialista();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idEspecialista==null){
            enviar = false;
            errores +="ID del especialista";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            EspecialistaDTO model = new EspecialistaDTO();
            model.setIdEspecialista(idEspecialista);
            ArrayList<EspecialistaDisponibilidadDTO> encontrados = this.especialistaDisponibilidadService.obtenerConIDEspecialista(model);
            if (encontrados != null) {
                result.put("result", true);
                result.put("mensaje", "Disponibilidades de horario encontrada");
                result.put("disponibilidades", encontrados);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró disponibilidad de horas con ese id de especialista...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/buscar/{fecha}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConFecha(@PathVariable String fecha) {
        logger.info("obtenerConFecha();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(fecha==null){
            enviar = false;
            errores +="fecha";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            ArrayList<EspecialistaDisponibilidadDTO> encontrados = this.especialistaDisponibilidadService.obtenerConFecha(fecha);
            if (encontrados != null) {
                result.put("result", true);
                result.put("mensaje", "Disponibilidad de horario encontrada para el día " + fecha);
                result.put("disponibilidades", encontrados);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró disponibilidad de horario para el día " + fecha);
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/buscar/{idEspecialista}/{fecha}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConIDEspecialistaYFecha(@PathVariable Integer idEspecialista,
                                                                             @PathVariable String fecha) {
        logger.info("obtenerConIDEspecialistaYFecha();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idEspecialista==null){
            enviar = false;
            errores +="Id de especialista";
        }

        if(fecha==null){
            enviar = false;
            errores +="fecha";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            EspecialistaDTO model = new EspecialistaDTO();
            model.setIdEspecialista(idEspecialista.longValue());
            ArrayList<EspecialistaDisponibilidadDTO> encontrados = this.especialistaDisponibilidadService.obtenerConIDEpecialistaYFecha(model,fecha);
            if (encontrados != null) {
                result.put("result", true);
                result.put("mensaje", "Disponibilidad de horario encontrada para el día " + fecha);
                result.put("disponibilidades", encontrados);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró disponibilidad de horario para el día " + fecha);
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}

