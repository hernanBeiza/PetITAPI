package cl.petit.api.controllers;

import cl.petit.api.models.dtos.BloqueHorarioDTO;
import cl.petit.api.models.dtos.ComunaDTO;
import cl.petit.api.services.BloqueHorarioService;
import cl.petit.api.services.ComunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequestMapping(path = "/api/horario")
@RestController
public class BloqueHorarioController {

    @Autowired
    private BloqueHorarioService bloqueHorarioService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<BloqueHorarioDTO> encontrados = this.bloqueHorarioService.obtener();
        if (encontrados != null) {
            result.put("result", true);
            result.put("mensaje", "Bloques de horarios encontrados");
            result.put("bloques", encontrados);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontró bloque horario con esos datos...");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idBloqueHorario}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idBloqueHorario) {
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idBloqueHorario==null){
            enviar = false;
            errores +="ID de la comuna";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            BloqueHorarioDTO model = new BloqueHorarioDTO();
            model.setIdBloqueHorario(idBloqueHorario);
            BloqueHorarioDTO encontrado = this.bloqueHorarioService.obtenerConID(model);
            if (encontrado != null) {
                result.put("result", true);
                result.put("mensaje", "Bloque horario encontrado");
                result.put("bloque", encontrado);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró bloque horario con ese id...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}

