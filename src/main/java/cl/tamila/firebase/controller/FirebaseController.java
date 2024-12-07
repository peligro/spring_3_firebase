package cl.tamila.firebase.controller;

import cl.tamila.firebase.dto.CotizacionesDto;
import cl.tamila.firebase.servicios.CotizacionesServiceBueno;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/firebase")
public class FirebaseController {

    @Autowired
    private CotizacionesServiceBueno service;
    @GetMapping("")
    public ResponseEntity<?> index()
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable("id") String id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.listarPorId(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody CotizacionesDto dto)
    {
        if(this.service.existeCorreo(dto.getCorreo()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("mensaje","Ocurrió un error inesperado ");
                }
            });
        }
        if(this.service.guardar(dto)==false)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("mensaje","Ocurrió un error inesperado 2");
                }
            });
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
                {
                    put("mensaje","Se creó el registro exitosamente ");
                }
            });
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CotizacionesDto dto, @PathVariable("id") String id)
    {
        if(this.service.editar(dto, id)==false)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("mensaje","Ocurrió un error inesperado 2");
                }
            });
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("mensaje","Se modificó el registro exitosamente ");
                }
            });
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable("id") String id)
    {
        if(this.service.eliminar(id)==false)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("mensaje","Ocurrió un error inesperado 2");
                }
            });
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("mensaje","Se eliminó el registro exitosamente ");
                }
            });
        }
    }
}
