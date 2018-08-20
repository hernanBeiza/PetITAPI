package cl.petit.api.controllers;

import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.services.MascotaService;
import cl.petit.api.services.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(path = "/api/mascota")
@RestController
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @RequestMapping(path="", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        System.out.println("MascotaController: obtener();");
        Map<String, Object> result = new HashMap<String,Object>();
        ArrayList<MascotaDTO> mascotas = this.mascotaService.obtener();
        if(mascotas.size()>0){
            result.put("result",true);
            result.put("mascotas",mascotas);
            result.put("mensajes","Mascotas encontradas");
        } else {
            result.put("result",false);
            result.put("errores","No se encontraron mascotas");
        }
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/{idmascota}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Integer idmascota) {
        System.out.println("MascotaController: obtenerConID();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idmascota==null){
            enviar = false;
            errores +="ID de mascota";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            /*
            UsuarioDTO model = new UsuarioDTO();
            model.setUser(usuario);
            model.setPass(contrasena);
            UsuarioDTO encontrado = this.usuarioService.buscar(model);

            if(encontrado!=null){
                result.put("result",true);
                result.put("mensaje","Bienvenido al sistema...");
                result.put("mascota",encontrado);

            } else {
                result.put("result",false);
                result.put("errores","No se encontró usuario con estos datos...");
            }
            */
            result.put("result",true);
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            System.out.println(errores);
            result.put("result",false);
            result.put("errores",errores);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }





}
