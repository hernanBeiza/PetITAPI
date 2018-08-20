package cl.petit.api.controllers;

import cl.petit.api.models.dtos.RazaDTO;
import cl.petit.api.models.dtos.RolDTO;
import cl.petit.api.services.RazaService;
import cl.petit.api.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequestMapping(path = "/api/raza")
@RestController
public class RazaController {

    @Autowired
    private RazaService razaService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        System.out.println("RazaController: obtener();");

        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<RazaDTO> encontrados = this.razaService.obtener();
        if (encontrados != null) {
            result.put("result", true);
            result.put("mensaje", "Razas encontradas");
            result.put("razas", encontrados);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontró raza con estos datos...");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idRaza}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idRaza) {
        System.out.println("RazaController: obtenerConID(); "+ idRaza);
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idRaza==null){
            enviar = false;
            errores +="ID de la raza";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            RazaDTO encontrada = this.razaService.obtenerConID(idRaza);
            if (encontrada != null) {
                result.put("result", true);
                result.put("mensaje", "Raza encontrada");
                result.put("raza", encontrada);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró raza con estos datos...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}

