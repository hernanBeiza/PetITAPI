package cl.petit.api.controllers;

import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.models.dtos.RazaDTO;
import cl.petit.api.services.ComunaService;
import cl.petit.api.services.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequestMapping(path = "/api/comuna")
@RestController
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<ComunaDTO> encontradas = this.comunaService.obtener();
        if (encontradas != null) {
            result.put("result", true);
            result.put("mensaje", "Comunas encontradas");
            result.put("razas", encontradas);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontró comuna con estos datos...");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idComuna}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idComuna) {
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idComuna==null){
            enviar = false;
            errores +="ID de la comuna";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            ComunaDTO encontrada = this.comunaService.obtenerConID(idComuna);
            if (encontrada != null) {
                result.put("result", true);
                result.put("mensaje", "Comuna encontrada");
                result.put("raza", encontrada);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró comuna con estos datos...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(path="/provincia/{idProvincia}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConIDProvincia(@PathVariable Long idProvincia) {
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idProvincia==null){
            enviar = false;
            errores +="ID de la provincia";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            ArrayList<ComunaDTO> encontradas = this.comunaService.obtenerConIDProvincia(idProvincia);
            if (encontradas != null) {
                result.put("result", true);
                result.put("mensaje", "Comunas encontradas de la provincia " + idProvincia);
                result.put("comunas", encontradas);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontraron comunas de la provincia " + idProvincia);
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}

