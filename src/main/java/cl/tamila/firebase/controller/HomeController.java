package cl.tamila.firebase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<?> index()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
        {
                put("estado","OK");
                put("mensaje","Ejemplo Firebase + Spring Boot 3");
            }
        });
    }
}
