package cl.petit.api.controllers;

import cl.petit.api.models.dtos.RolDTO;
import cl.petit.api.models.dtos.UsuarioDTO;
import cl.petit.api.services.UsuarioService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
public class UsuarioController {

    private static final Logger logger = LogManager.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(path="/login", method={RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> login(@RequestParam(value="rut",required=false) String rut,
                                                    @RequestParam(value="password",required=false) String password,
                                                    HttpSession session) {
        logger.debug("login();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(rut==null){
            enviar = false;
            errores +="Usuario";
        }
        if(password==null){
            enviar = false;
            errores +="Contraseña";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            UsuarioDTO model = new UsuarioDTO();
            model.setRut(rut);
            model.setPassword(password);
            UsuarioDTO encontrado = this.usuarioService.buscar(model.getRut(),model.getPassword());
            if(encontrado!=null){
                result.put("result",true);
                result.put("mensaje","Bienvenido al sistema de PetIT...");
                result.put("usuario",encontrado);
                // Guardar en la sessión
                //session.setAttribute("usuario",encontrado);
                // Generar Token
                String jwtToken = Jwts.builder().setSubject(rut).claim("roles", "user").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "SECRETKEY").compact();
                System.out.println(jwtToken);
                // Token que expira una hora después de la creación
                /*
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.HOUR_OF_DAY, 1);
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date horaExpiracion = cal.getTime();
                logger.info(dateFormat.format(horaExpiracion));
                String jwtToken = Jwts.builder().setSubject(rut).claim("roles", "user").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "SECRETKEY").setExpiration(horaExpiracion).compact();
                */
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
        logger.debug("verificarToken()");
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
        logger.debug("UsuarioController: logout()");
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
        logger.debug("UsuarioController: obtener();");
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

    @RequestMapping(path="/api/usuario/{idUsuario}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable int idUsuario) {
        logger.debug("UsuarioController: obtenerConID();");
        Map<String, Object> result = new HashMap<String,Object>();
        UsuarioDTO encontrado = this.usuarioService.obtenerConID(idUsuario);
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


    @RequestMapping(path="/api/usuario/rut/{rut}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> buscarPorRut(@PathVariable String rut) {
        logger.debug("UsuarioController: buscarPorRut();");
        Map<String, Object> result = new HashMap<String,Object>();
        UsuarioDTO encontrado = this.usuarioService.buscarPorRut(rut);
        if(encontrado!=null){
            result.put("result",true);
            result.put("mensaje","Se encontraro usuario con ese rut");
            result.put("usuario",encontrado);
        } else {
            result.put("result",false);
            result.put("errores","No se encontraró usuario con ese rut");
        }

        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(path="/api/usuario/nombre/{nombre}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> buscarPorNombre(@PathVariable String nombre) {
        logger.debug("UsuarioController: buscarPorNombre();");
        Map<String, Object> result = new HashMap<String,Object>();
        UsuarioDTO encontrado = this.usuarioService.buscarPorNombre(nombre);
        if(encontrado!=null){
            result.put("result",true);
            result.put("mensaje","Se encontró usuario con ese nombre");
            result.put("usuario",encontrado);
        } else {
            result.put("result",false);
            result.put("errores","No se encontró usuario con ese nombre");
        }

        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }



    @RequestMapping(path="/api/usuario", method={RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> guardar(@RequestParam(value="idRol",required=false) Integer idRol,
                                                      @RequestParam(value="nombre",required=false) String nombre,
                                                      @RequestParam(value="rut",required=false) String rut,
                                                      @RequestParam(value="password",required=false) String password,
                                                    HttpSession session) {
        logger.debug("UsuarioController: guardar();");
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idRol==null){
            enviar = false;
            errores +="\nidRol";
        }
        if(nombre==null){
            enviar = false;
            errores +="\nNombre";
        }
        if(rut==null){
            enviar = false;
            errores +="\nRut";
        }
        if(password==null){
            enviar = false;
            errores +="\nContraseña";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            RolDTO rolModel = new RolDTO();
            rolModel.setIdRol(idRol.longValue());
            UsuarioDTO model = new UsuarioDTO();
            model.setRol(rolModel);
            model.setNombre(nombre);
            model.setRut(rut);
            model.setPassword(password);
            model.setValid(1);
            boolean guardado = this.usuarioService.guardar(model);
            if(guardado){
                result.put("result",true);
                result.put("mensaje","Usuario guardado correctamente");
            } else {
                result.put("result",false);
                result.put("errores","No se pudo guardar el usuario");
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

    @RequestMapping(path="/api/usuario/{idUsuario}", method={RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> editar(@PathVariable(value="idUsuario",required=false) Integer idUsuario,
                                                        @RequestParam(value="idRol",required=false) Integer idRol,
                                                        @RequestParam(value="nombre",required=false) String nombre,
                                                        @RequestParam(value="rut",required=false) String rut,
                                                        @RequestParam(value="password",required=false) String password,
                                                        @RequestParam(value="valid",required=false) Integer valid,
                                                     HttpSession session) {
        logger.debug("UsuarioController: editar();");
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idUsuario==null){
            enviar = false;
            errores +="idUsuario";
        }
        if(idRol==null){
            enviar = false;
            errores +="idRol";
        }
        if(nombre==null){
            enviar = false;
            errores +="Nombre";
        }
        if(rut==null){
            enviar = false;
            errores +="Rut";
        }
        if(password==null){
            enviar = false;
            errores +="Contraseña";
        }
        if(valid==null){
            enviar = false;
            errores +="Valid";
        }
        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            RolDTO rolModel = new RolDTO();
            rolModel.setIdRol(Long.valueOf(idRol));
            UsuarioDTO model = new UsuarioDTO();
            model.setIdUsuario(Long.valueOf(idUsuario));
            model.setRol(rolModel);
            model.setNombre(nombre);
            model.setRut(rut);
            model.setPassword(password);
            model.setValid(valid);
            boolean guardado = this.usuarioService.editar(model);
            if(guardado){
                result.put("result",true);
                result.put("mensaje","Usuario editado correctamente");
            } else {
                result.put("result",false);
                result.put("errores","No se pudo editar el usuario");
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


    @RequestMapping(path="/api/usuario/{idUsuario}", method={RequestMethod.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> eliminar(@PathVariable Integer idUsuario) {
        logger.debug("UsuarioController: eliminar();");
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(idUsuario==null){
            enviar = false;
            errores +="idUsuario";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            UsuarioDTO model = new UsuarioDTO();
            model.setIdUsuario(Long.valueOf(idUsuario));
            boolean eliminado = this.usuarioService.eliminar(model);
            if(eliminado){
                result.put("result",true);
                result.put("mensaje","Usuario eliminado correctamente...");
            } else {
                result.put("result",false);
                result.put("errores","No se pudo eliminar el usuario con estos datos...");
            }
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            result.put("result",false);
            result.put("errores",errores);
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }

    }

}

