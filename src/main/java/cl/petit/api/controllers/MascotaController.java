package cl.petit.api.controllers;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
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


    @RequestMapping(path="/pag/{pagina}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConPagina(@PathVariable Integer pagina) {
        System.out.println("MascotaController: obtenerConRutDueno();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(pagina==null){
            enviar = false;
            errores +="Página";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            ArrayList<MascotaDTO> mascotas = this.mascotaService.obtenerConPagina(pagina);
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
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/{rutMascota}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConRut(@PathVariable String rutMascota) {
        System.out.println("MascotaController: obtenerConRut();");

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
                result.put("mensajes","Mascota encontrada con rut" + rutMascota);
            } else {
                result.put("result",false);
                result.put("errores","No se encontró mascota con rut " + rutMascota);
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

    @RequestMapping(path="/dueno/{rutDueno}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConRutDueno(@PathVariable String rutDueno) {
        System.out.println("MascotaController: obtenerConRutDueno();");

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
            // Add any additional props that you want to add
            return new ResponseEntity<Map<String,Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }



}
