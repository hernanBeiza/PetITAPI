package cl.petit.api.controllers;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.models.dtos.RazaDTO;
import cl.petit.api.models.dtos.TipoMascotaDTO;
import cl.petit.api.services.MascotaService;
import cl.petit.api.services.RazaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(MascotaController.class);

    @Autowired
    private MascotaService mascotaService;
    /*
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
    */

    @RequestMapping(path={"","/"}, method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConPagina(@RequestParam(value="pagina", required = false) Integer pagina) {
        logger.info("obtenerConPagina();");
        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(pagina==null){
            //enviar = false;
            //errores +="Página";
            pagina = 1;
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            ArrayList<MascotaDTO> mascotas = this.mascotaService.obtenerConPagina(pagina.longValue());
            if(mascotas.size()>0){
                result.put("result",true);
                result.put("mascotas",mascotas);
                result.put("mensajes","Mascotas encontradas");
            } else {
                result.put("result",false);
                result.put("errores","No se encontraron mascotas");
            }
            result.put("result",true);
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            System.out.println(errores);
            result.put("result",false);
            result.put("errores",errores);
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/buscar/{nombre}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> buscarPorNombre(@PathVariable String nombre) {
        logger.info("buscarPorNombre();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(nombre==null){
            enviar = false;
            errores +="Nombre de la mascota";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            MascotaDTO mascotaDTO = new MascotaDTO();
            mascotaDTO.setNombre(nombre);
            ArrayList<MascotaDTO> encontradas = this.mascotaService.buscarPorNombre(mascotaDTO);
            if(encontradas.size()>0){
                result.put("result",true);
                result.put("mascotas",encontradas);
                result.put("mensajes","Mascotas encontradas con nombre " + nombre);
            } else {
                result.put("result",false);
                result.put("errores","No se encontraron mascotas con rut " + nombre);
            }
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            System.out.println(errores);
            result.put("result",false);
            result.put("errores",errores);
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/{rutMascota}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConRut(@PathVariable String rutMascota) {
        logger.info("obtenerConRut();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(rutMascota==null){
            enviar = false;
            errores +="Rut de la mascota";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            MascotaDTO mascotaDTO = new MascotaDTO();
            mascotaDTO.setRutMascota(rutMascota);
            MascotaDTO mascotaEncontrada = this.mascotaService.obtenerConRut(mascotaDTO);
            if(mascotaEncontrada!=null){
                result.put("result",true);
                result.put("mascota",mascotaEncontrada);
                result.put("mensajes","Mascota encontrada con rut " + rutMascota);
            } else {
                result.put("result",false);
                result.put("errores","No se encontró mascota con rut " + rutMascota);
            }
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            System.out.println(errores);
            result.put("result",false);
            result.put("errores",errores);
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/dueno/{rutDueno}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConRutDueno(@PathVariable String rutDueno) {
        logger.info("obtenerConRutDueno();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(rutDueno==null){
            enviar = false;
            errores +="Rut del dueño";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            DuenoMascotaDTO duenoMascotaDTO = new DuenoMascotaDTO();
            duenoMascotaDTO.setRutDueno(rutDueno);
            ArrayList<MascotaDTO> mascotas = this.mascotaService.obtenerConRutDueno(duenoMascotaDTO);
            if(mascotas.size()>0){
                result.put("result",true);
                result.put("mascotas",mascotas);
                result.put("mensajes","Mascotas encontradas");
            } else {
                result.put("result",false);
                result.put("errores","No se encontraron mascotas");
            }
            result.put("result",true);
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
        } else {
            System.out.println(errores);
            result.put("result",false);
            result.put("errores",errores);
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="", method={RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> guardar(
            @RequestParam(value="rutMascota",required=false) String rutMascota,
            @RequestParam(value="idTipoMascota",required=false) Integer idTipoMascota,
            @RequestParam(value="idRaza",required=false) Integer idRaza,
            @RequestParam(value="rutDueno",required=false) String rutDueno,
            @RequestParam(value="nombre",required=false) String nombre,
            @RequestParam(value="peso",required=false) Integer peso,
            @RequestParam(value="edad",required=false) Integer edad){
        logger.info("guardar();");

        Map<String, Object> result = new HashMap<String,Object>();

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(rutMascota==null){
            enviar = false;
            errores +="rut de la mascota\n";
        }
        if(idTipoMascota==null){
            enviar = false;
            errores +="tipo de mascota\n";
        }

        if(idRaza==null){
            enviar = false;
            errores +="raza\n";
        }
        if(rutDueno==null){
            enviar = false;
            errores +="rut del dueño\n";
        }
        if(nombre==null){
            enviar = false;
            errores +="nombre de la mascota\n";
        }
        if(peso==null){
            enviar = false;
            errores +="peso\n";
        }
        if(edad==null){
            enviar = false;
            errores +="edad\n";
        }
        if(enviar) {

            MascotaDTO mascotaDTO = new MascotaDTO();
            mascotaDTO.setRutMascota(rutMascota);

            TipoMascotaDTO tipoMascotaDTO = new TipoMascotaDTO();
            tipoMascotaDTO.setIdTipoMascota(idTipoMascota.longValue());
            mascotaDTO.setTipoMascota(tipoMascotaDTO);

            RazaDTO razaDTO = new RazaDTO();
            razaDTO.setIdRaza(idRaza.longValue());
            mascotaDTO.setRaza(razaDTO);

            DuenoMascotaDTO duenoMascotaDTO = new DuenoMascotaDTO();
            duenoMascotaDTO.setRutDueno(rutDueno);
            mascotaDTO.setDuenoMascota(duenoMascotaDTO);

            mascotaDTO.setNombre(nombre);
            mascotaDTO.setPeso(peso);
            mascotaDTO.setEdad(edad);
            mascotaDTO.setValid(1);

            boolean guardada = this.mascotaService.guardar(mascotaDTO);
            if (guardada) {
                result.put("result", true);
                result.put("mensaje", "Mascota guardada correctamente");
            } else {
                result.put("result", false);
                result.put("errores", "No se pudo guardar la mascota");
            }
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } else {
            result.put("result",false);
            result.put("errores",errores);
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/{rutMascota}", method={RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> editar(
            @PathVariable(value="rutMascota",required=false) String rutMascota,
            @RequestParam(value="idTipoMascota",required=false) Integer idTipoMascota,
            @RequestParam(value="idRaza",required=false) Integer idRaza,
            @RequestParam(value="rutDueno",required=false) String rutDueno,
            @RequestParam(value="nombre",required=false) String nombre,
            @RequestParam(value="peso",required=false) Integer peso,
            @RequestParam(value="edad",required=false) Integer edad,
            @RequestParam(value="valid",required=false) Integer valid){
        logger.info("editar();");

        Map<String, Object> result = new HashMap<String,Object>();

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(rutMascota==null){
            enviar = false;
            errores +="rut de la mascota\n";
        }
        if(idTipoMascota==null){
            enviar = false;
            errores +="tipo de mascota\n";
        }

        if(idRaza==null){
            enviar = false;
            errores +="raza\n";
        }
        if(rutDueno==null){
            enviar = false;
            errores +="rut del dueño\n";
        }
        if(nombre==null){
            enviar = false;
            errores +="nombre de la mascota\n";
        }
        if(peso==null){
            enviar = false;
            errores +="peso\n";
        }
        if(edad==null){
            enviar = false;
            errores +="edad\n";
        }
        if(valid==null){
            enviar = false;
            errores +="valid\n";
        }
        if(enviar) {

            MascotaDTO mascotaDTO = new MascotaDTO();
            mascotaDTO.setRutMascota(rutMascota);

            TipoMascotaDTO tipoMascotaDTO = new TipoMascotaDTO();
            tipoMascotaDTO.setIdTipoMascota(idTipoMascota.longValue());
            mascotaDTO.setTipoMascota(tipoMascotaDTO);

            RazaDTO razaDTO = new RazaDTO();
            razaDTO.setIdRaza(idRaza.longValue());
            mascotaDTO.setRaza(razaDTO);

            DuenoMascotaDTO duenoMascotaDTO = new DuenoMascotaDTO();
            duenoMascotaDTO.setRutDueno(rutDueno);
            mascotaDTO.setDuenoMascota(duenoMascotaDTO);

            mascotaDTO.setNombre(nombre);
            mascotaDTO.setPeso(peso);
            mascotaDTO.setEdad(edad);
            mascotaDTO.setValid(valid);

            boolean editada = this.mascotaService.editar(mascotaDTO);
            if (editada) {
                result.put("result", true);
                result.put("mensaje", "Mascota editada correctamente");
            } else {
                result.put("result", false);
                result.put("errores", "No se pudo editar la mascota");
            }
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } else {
            result.put("result",false);
            result.put("errores",errores);
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/{rutMascota}", method={RequestMethod.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> eliminar(@PathVariable String rutMascota){
        logger.info("eliminar();");

        Map<String, Object> result = new HashMap<String,Object>();

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(rutMascota==null){
            enviar = false;
            errores +="rut de la mascota\n";
        }
        if(enviar) {
            MascotaDTO mascotaDTO = new MascotaDTO();
            mascotaDTO.setRutMascota(rutMascota);
            boolean eliminada = this.mascotaService.eliminar(mascotaDTO);
            if (eliminada) {
                result.put("result", true);
                result.put("mensaje", "Mascota eliminada correctamente");
            } else {
                result.put("result", false);
                result.put("errores", "No se pudo eliminar la mascota");
            }
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } else {
            result.put("result",false);
            result.put("errores",errores);
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }




}
