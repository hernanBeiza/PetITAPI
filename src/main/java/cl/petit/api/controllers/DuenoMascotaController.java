package cl.petit.api.controllers;

import cl.petit.api.models.dtos.DuenoMascotaDTO;
import cl.petit.api.models.dtos.MascotaDTO;
import cl.petit.api.services.DuenoMascotaService;
import cl.petit.api.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(path = "/api")
@RestController
public class DuenoMascotaController {

    @Autowired
    private DuenoMascotaService duenoMascotaService;

    @RequestMapping(path="/dueno", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtener() {
        System.out.println("DuenoMascotaController: obtener();");
        Map<String, Object> result = new HashMap<String,Object>();
        ArrayList<DuenoMascotaDTO> duenos = this.duenoMascotaService.obtener();
        if(duenos !=null){
            if(duenos.size()>0){
                result.put("result",true);
                result.put("duenos",duenos);
                result.put("mensajes","Dueños de mascotas encontrados");
            } else {
                result.put("result",false);
                result.put("errores","No se encontraron mascotas");
            }
        } else {
            result.put("result",false);
            result.put("errores","No se encontraron dueños de mascotas");
        }
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/dueno/{rutDueno}", method={RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> obtenerConID(@PathVariable String rutDueno) {
        System.out.println("DuenoMascotaController: obtenerConID();");

        boolean enviar = true;
        String errores = "Te faltó:\n";
        if(rutDueno==null){
            enviar = false;
            errores +="Rut del dueño de mascota";
        }

        Map<String, Object> result = new HashMap<String,Object>();
        if(enviar){
            DuenoMascotaDTO model = new DuenoMascotaDTO();
            model.setRutDueno(rutDueno);
            DuenoMascotaDTO dto = this.duenoMascotaService.obtenerConRut(model);
            if (dto!=null){
                result.put("result",true);
                result.put("dueno",dto);
                result.put("mensajes","Dueño de mascota encontrado");
            } else {
                result.put("result",false);
                result.put("errores","No se encontró dueño con ese rut");
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

    @RequestMapping(path="/dueno", method={RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> guardar() {
        System.out.println("DuenoMascotaController: guardar();");

        Map<String, Object> result = new HashMap<String,Object>();
        /*
        ArrayList<DuenoMascotaDTO> duenos = this.duenoMascotaService.obtener();
        if(duenos !=null){
            if(duenos.size()>0){
                result.put("result",true);
                result.put("duenos",duenos);
                result.put("mensajes","Dueños de mascotas encontrados");
            } else {
                result.put("result",false);
                result.put("errores","No se encontraron mascotas");
            }
        } else {
            result.put("result",false);
            result.put("errores","No se encontraron dueños de mascotas");
        }
        */
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/dueno", method={RequestMethod.PUT}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> editar() {
        System.out.println("DuenoMascotaController: editar();");

        Map<String, Object> result = new HashMap<String,Object>();
        /*
        ArrayList<DuenoMascotaDTO> duenos = this.duenoMascotaService.obtener();
        if(duenos !=null){
            if(duenos.size()>0){
                result.put("result",true);
                result.put("duenos",duenos);
                result.put("mensajes","Dueños de mascotas encontrados");
            } else {
                result.put("result",false);
                result.put("errores","No se encontraron mascotas");
            }
        } else {
            result.put("result",false);
            result.put("errores","No se encontraron dueños de mascotas");
        }
        */
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/dueno", method={RequestMethod.DELETE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map<String,Object>> eliminar() {
        System.out.println("DuenoMascotaController: eliminar();");

        Map<String, Object> result = new HashMap<String,Object>();
        /*
        ArrayList<DuenoMascotaDTO> duenos = this.duenoMascotaService.obtener();
        if(duenos !=null){
            if(duenos.size()>0){
                result.put("result",true);
                result.put("duenos",duenos);
                result.put("mensajes","Dueños de mascotas encontrados");
            } else {
                result.put("result",false);
                result.put("errores","No se encontraron mascotas");
            }
        } else {
            result.put("result",false);
            result.put("errores","No se encontraron dueños de mascotas");
        }
        */

        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }



}
