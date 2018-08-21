package cl.petit.api.controllers;

import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.models.dtos.RegionDTO;
import cl.petit.api.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequestMapping(path = "/api/region")
@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<RegionDTO> encontradas = this.regionService.obtener();
        if (encontradas != null) {
            result.put("result", true);
            result.put("mensaje", "Regiones encontradas");
            result.put("regiones", encontradas);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontró región con estos datos...");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idRegion}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idRegion) {
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idRegion==null){
            enviar = false;
            errores +="ID de la región";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            RegionDTO encontrada = this.regionService.obtenerConID(idRegion);
            if (encontrada != null) {
                result.put("result", true);
                result.put("mensaje", "Región encontrada");
                result.put("raza", encontrada);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró región con estos datos...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}