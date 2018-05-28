package cl.petit.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")

public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String,Object>>saludar() {
        Map <String, Object> result = new HashMap<>();
        result.put("result",true);
        result.put("mensajes","Bienvenido a la API de PetIT");
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

}