package cl.petit.api.controllers;

import cl.petit.api.models.dtos.ProvinciaDTO;
import cl.petit.api.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequestMapping(path = "/api/provincia")
@RestController
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<ProvinciaDTO> encontradas = this.provinciaService.obtener();
        if (encontradas != null) {
            result.put("result", true);
            result.put("mensaje", "Provincias encontradas");
            result.put("provincias", encontradas);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontró provincia con estos datos...");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idProvincia}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idProvincia) {
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idProvincia==null){
            enviar = false;
            errores +="ID de la provincia";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            ProvinciaDTO encontrada = this.provinciaService.obtenerConID(idProvincia);
            if (encontrada != null) {
                result.put("result", true);
                result.put("mensaje", "Provincia encontrada");
                result.put("provincia", encontrada);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró provincia con estos datos...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(path="/region/{idRegion}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConIDRegion(@PathVariable Long idRegion) {
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idRegion==null){
            enviar = false;
            errores +="ID de la región";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            ArrayList<ProvinciaDTO> encontradas = this.provinciaService.obtenerConIDRegion(idRegion);
            if (encontradas != null) {
                result.put("result", true);
                result.put("mensaje", "Provincias encontradas de la región " + idRegion);
                result.put("provincias", encontradas);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontraron provincias de la región " + idRegion);
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}

