package cl.petit.api.controllers;

import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.models.dtos.OrigenDTO;
import cl.petit.api.services.ComunaService;
import cl.petit.api.services.OrigenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequestMapping(path = "/api/origen")
@RestController
public class OrigenController {

    @Autowired
    private OrigenService origenService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<OrigenDTO> encontrados = this.origenService.obtener();
        if (encontrados != null) {
            result.put("result", true);
            result.put("mensaje", "Origenes  encontrados");
            result.put("origenes", encontrados);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontraron origenes");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idOrigen}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idOrigen) {
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idOrigen==null){
            enviar = false;
            errores +="ID del origen";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            OrigenDTO encontrada = this.origenService.obtenerConID(idOrigen);
            if (encontrada != null) {
                result.put("result", true);
                result.put("mensaje", "Origen encontrado");
                result.put("origen", encontrada);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró origen con estos datos...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}

