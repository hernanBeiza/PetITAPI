package cl.petit.api.Controllers;

import cl.petit.api.Models.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RestController
public class UsuarioController {

    @RequestMapping(path="/login", method={RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> login(@RequestParam(value="usuario",required=false) String usuario,
                                                    @RequestParam(value="contrasena",required=false) String contrasena,
                                                    HttpSession session) {
        System.out.println("login();");
        System.out.println(usuario);
        System.out.println(contrasena);

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(usuario==null){
            enviar = false;
            errores +="Usuario";
        }
        if(contrasena==null){
            enviar = false;
            errores +="Contraseña";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            // TODO: Hacer validación contra DB

            result.put("result",true);
            result.put("mensaje","Bienvenido al sistema...");
            UsuarioModel model = new UsuarioModel(1,usuario,null);
            result.put("usuario",model);
            result.put("token","asdfasdfasdf");
            // Guardar en la sessión
            session.setAttribute("usuario",model);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            System.out.println(errores);
            result.put("result",false);
            result.put("errores",errores);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(path="/session", method = {RequestMethod.GET,RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> verificarSession(@RequestParam(value="token",required=false) String token,  HttpSession session){
        System.out.println("verificarSession()");
        Map<String, Object> result = new HashMap<String,Object>();

        if(session.getAttribute("usuario")!=null){
            UsuarioModel model = (UsuarioModel)session.getAttribute("usuario");
            System.out.println(model.toString());
            result.put("result",true);
            result.put("usuario",model);

            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            result.put("result",false);
            result.put("errores","No existe usuario, inicia sesión de nuevo");

            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/logout", method = {RequestMethod.GET,RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> logout(HttpSession session){
        System.out.println("logout()");
        session.invalidate();
        Map<String, Object> result = new HashMap<String,Object>();
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

}

