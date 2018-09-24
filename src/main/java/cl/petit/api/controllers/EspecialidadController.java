package cl.petit.api.controllers;

import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.models.dtos.EspecialidadDTO;
import cl.petit.api.services.ComunaService;
import cl.petit.api.services.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequestMapping(path = "/api/especialidad")
@RestController
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<EspecialidadDTO> encontradas = this.especialidadService.obtener();
        if (encontradas != null) {
            result.put("result", true);
            result.put("mensaje", "Especialidades encontradas");
            result.put("especialidades", encontradas);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontraron especialidades...");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idEspecialidad}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idEspecialidad) {
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idEspecialidad==null){
            enviar = false;
            errores +="ID de la especialidad";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            EspecialidadDTO encontrada = this.especialidadService.obtenerConID(idEspecialidad);
            if (encontrada != null) {
                result.put("result", true);
                result.put("mensaje", "Especialidad encontrada");
                result.put("raza", encontrada);
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
}

