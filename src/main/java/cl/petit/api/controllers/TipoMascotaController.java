package cl.petit.api.controllers;

import cl.petit.api.models.dtos.TipoMascotaDTO;
import cl.petit.api.services.TipoMascotaService;
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
public class TipoMascotaController {

    @Autowired
    private TipoMascotaService tipoMascotaService;

    @RequestMapping(path="/tipo", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        System.out.println("TipoMascotaController: obtener();");
        Map<String, Object> result = new HashMap<String,Object>();
        ArrayList<TipoMascotaDTO> tipos = this.tipoMascotaService.obtener();
        if(tipos.size()>0){
            result.put("result",true);
            result.put("mensajes","Tipos de mascota encontrados");
            result.put("tipos",tipos);
        } else {
            result.put("result",false);
            result.put("errores","No se encontraron tipos de mascotas");
        }

        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/tipo/{idmascota}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable Integer idmascota) {
        System.out.println("TipoMascotaController: obtenerConID();");

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
