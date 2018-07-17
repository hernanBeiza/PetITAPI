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
@RequestMapping(path = "/api")
@RestController
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @RequestMapping(path="/comuna", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        System.out.println("ComunaController: obtener();");

        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<ComunaDTO> encontradas = this.comunaService.obtener();
        if (encontradas != null) {
            result.put("result", true);
            result.put("mensaje", "Comunas encontradas");
            result.put("razas", encontradas);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontró raza con estos datos...");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/comuna/{idComuna}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idComuna) {
        System.out.println("ComunaController: obtenerConID(); "+ idComuna);
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

}

