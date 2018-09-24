package cl.petit.api.controllers;

import cl.petit.api.models.dtos.EspecialidadDTO;
import cl.petit.api.models.dtos.EspecialistaDTO;
import cl.petit.api.services.EspecialidadService;
import cl.petit.api.services.EspecialistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequestMapping(path = "/api/especialista")
@RestController
public class EspecialistaController {

    @Autowired
    private EspecialistaService especialistaService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<EspecialistaDTO> encontrados = this.especialistaService.obtener();
        if (encontrados != null) {
            result.put("result", true);
            result.put("mensaje", "Especialistas encontrados");
            result.put("especialistas", encontrados);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontraron especialistas...");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idEspecialista}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idEspecialista) {
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idEspecialista==null){
            enviar = false;
            errores +="ID del especialista";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            EspecialistaDTO encontrado = this.especialistaService.obtenerConID(idEspecialista);
            if (encontrado != null) {
                result.put("result", true);
                result.put("mensaje", "Especialista encontrado");
                result.put("especialistas", encontrado);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró especialidad con estos datos...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(path="/especialidad/{idEspecialidad}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConIDEspecialidad(@PathVariable Long idEspecialidad) {
        String errores = "Te faltó:\n";
        boolean enviar = true;
        if(idEspecialidad==null){
            enviar = false;
            errores +="ID de la especialidad";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            ArrayList<EspecialistaDTO> encontrados = this.especialistaService.obtenerConIDEspecialidad(idEspecialidad);
            if (encontrados != null) {
                result.put("result", true);
                result.put("mensaje", "Especialistas encontrados");
                result.put("especialistas", encontrados);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontraron especialistas...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }

        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}

