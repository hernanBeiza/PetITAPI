package cl.petit.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(path = "/api")
@RestController
public class MascotaController {

    @RequestMapping(path="/mascota/{idmascota}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
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
