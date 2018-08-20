package cl.petit.api.controllers;

import cl.petit.api.models.dtos.RolDTO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.services.RolService;
import cl.petit.api.services.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequestMapping(path = "/api")
@RestController
public class RolController {

    @Autowired
    private RolService rolService;

    @RequestMapping(path="/rol", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        System.out.println("RolController: obtener();");

        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<RolDTO> encontrados = this.rolService.obtener();
        if (encontrados != null) {
            result.put("result", true);
            result.put("mensaje", "Roles encontrados...");
            result.put("encontrados", encontrados);
        } else {
            result.put("result", false);
            result.put("errores", "No se encontraron roles en el sistema");
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/rol/{idRol}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Long idRol) {
        System.out.println("RolController: obtenerConID(); "+ idRol);
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idRol==null){
            enviar = false;
            errores +="ID del rol";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar) {
            RolDTO encontrado = this.rolService.obtenerConID(idRol);
            if (encontrado != null) {
                result.put("result", true);
                result.put("mensaje", "Rol encontrado");
                result.put("rol", encontrado);
            } else {
                result.put("result", false);
                result.put("errores", "No se encontró rol con estos datos...");
            }
        } else {
            result.put("result", false);
            result.put("errores", errores);
        }
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}

