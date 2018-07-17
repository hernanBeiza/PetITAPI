package cl.petit.api.controllers;

import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.services.UsuarioService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(path="/login", method={RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> login(@RequestParam(value="rut",required=false) String rut,
                                                    @RequestParam(value="contrasena",required=false) String contrasena,
                                                    HttpSession session) {
        System.out.println("UsuarioController: login();");
        System.out.println(rut);
        System.out.println(contrasena);

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(rut==null){
            enviar = false;
            errores +="Usuario";
        }
        if(contrasena==null){
            enviar = false;
            errores +="Contraseña";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            UsuarioDTO model = new UsuarioDTO();
            model.setRut(rut);
            model.setPassword(contrasena);
            UsuarioDTO encontrado = this.usuarioService.iniciarSesion(model);
            if(encontrado!=null){
                result.put("result",true);
                result.put("mensaje","Bienvenido al sistema de PetIT...");
                result.put("usuario",encontrado);
                // Guardar en la sessión
                //session.setAttribute("usuario",encontrado);
                // Generar Token
                String jwtToken = Jwts.builder().setSubject(rut).claim("roles", "user").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "SECRETKEY").compact();
                System.out.println(jwtToken);
                // Add any additional props that you want to add
                result.put("token",jwtToken);

            } else {
                result.put("result",false);
                result.put("errores","No se encontró usuario con estos datos...");
            }

            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            System.out.println(errores);
            result.put("result",false);
            result.put("errores",errores);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(path="/verificar", method = {RequestMethod.GET,RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> verificarToken(@RequestHeader("authorization") String authHeader){
        System.out.println("verificarToken()");
        Map<String, Object> result = new HashMap<String,Object>();
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Missing or invalid Authorization header");
            result.put("result",false);
            result.put("errores","Missing or invalid Authorization header");
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.FORBIDDEN);
        } else {

            final String token = authHeader.substring(7);
            System.out.println(token);

            try {
                final Claims claims = Jwts.parser().setSigningKey("SECRETKEY").parseClaimsJws(token).getBody();

                result.put("result",true);
                result.put("mensaje","Token válido");
                return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);

            } catch (final SignatureException e) {
                System.out.println("Invalid token");
                System.out.println(e.getLocalizedMessage());

                result.put("result",false);
                result.put("errores","Token inválido");
                return new ResponseEntity<Map<String,Object>>(result, HttpStatus.FORBIDDEN);

            }
        }

    }
    /*
    @RequestMapping(path="/session", method = {RequestMethod.GET,RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> verificarSession(@RequestParam(value="token",required=false) String token,  HttpSession session){
        System.out.println("UsuarioController: verificarSession()");
        Map<String, Object> result = new HashMap<String,Object>();

        if(session.getAttribute("usuario")!=null){
            UsuarioDTO model = (UsuarioDTO)session.getAttribute("usuario");
            //System.out.println(model.toString());
            result.put("result",true);
            result.put("usuario",model);
            result.put("mensaje","Bienvenido "+model.getNombre());
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            result.put("result",false);
            result.put("errores","No existe sesión iniciada");

            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }
    */
    @RequestMapping(path="/logout", method = {RequestMethod.GET,RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> logout(HttpSession session){
        System.out.println("UsuarioController: logout()");
        Map<String, Object> result = new HashMap<String,Object>();
        /*
        if(session.getAttribute("usuario")!=null){
            session.invalidate();
            result.put("result",true);
            result.put("mensajes","Adiós");
        } else {
            result.put("result",false);
            result.put("errores","No existe sesión iniciada");
        }
        */
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/api/usuario", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        System.out.println("UsuarioController: obtener();");
        Map<String, Object> result = new HashMap<String,Object>();
        ArrayList<UsuarioDTO> encontrados = this.usuarioService.obtener();
        if(encontrados!=null){
            result.put("result",true);
            result.put("mensaje","Se encontraron usuarios");
            result.put("usuarios",encontrados);
        } else {
            result.put("result",false);
            result.put("errores","No se encontraron usuarios registrados");
        }

        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/api/usuario/{idusuario}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable int idusuario) {
        System.out.println("UsuarioController: obtenerConID();");
        Map<String, Object> result = new HashMap<String,Object>();
        UsuarioDTO encontrado = this.usuarioService.obtenerConID(idusuario);
        if(encontrado!=null){
            result.put("result",true);
            result.put("mensaje","Se encontraron usuarios");
            result.put("usuario",encontrado);
        } else {
            result.put("result",false);
            result.put("errores","No se encontró usuario registrado");
        }

        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

}

