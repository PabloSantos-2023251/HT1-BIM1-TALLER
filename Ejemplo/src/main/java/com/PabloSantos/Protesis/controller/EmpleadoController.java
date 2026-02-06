package com.PabloSantos.Protesis.controller;

import com.PabloSantos.Protesis.model.Empleado;
import com.PabloSantos.Protesis.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    private final EmpleadoService empleadoService;
    public EmpleadoController(EmpleadoService empleadoService){
        this.empleadoService = empleadoService;
    }
    @GetMapping
    public List<Empleado> getAllEmpleados(){return empleadoService.getAllEmpleados();}

    @PostMapping
    public ResponseEntity<Object> createEmpleado(@Valid @RequestBody Empleado empleado){
        try {

            String email = empleado.getEmail_empleado().toLowerCase();
            Empleado createdEmpleado;

            if (!(email.endsWith("@gmail.com") || email.endsWith("@hotmail.com") || email.endsWith("@outlook.com"))) {
                throw new IllegalArgumentException("Solo se permiten correos de Gmail, Hotmail o Outlook.");
            }else{
                createdEmpleado = empleadoService.saveEmpleado(empleado);
            }

            return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateEmpleado(@Valid @RequestBody Empleado empleado) {
        try {
            Integer id = empleado.getId_empleado();
            String email = empleado.getEmail_empleado().toLowerCase();
            Empleado updatedEmpleado;

            if (id == null) {
                return ResponseEntity.badRequest().body("Error: El ID del empleado es obligatorio en el JSON.");
            } else if (!(email.endsWith("@gmail.com") || email.endsWith("@hotmail.com") || email.endsWith("@outlook.com"))) {
                throw new IllegalArgumentException("Solo se permiten correos de Gmail, Hotmail o Outlook.");
            }else {
                updatedEmpleado = empleadoService.updateEmpleado(id, empleado);
            }

            return new ResponseEntity<>(updatedEmpleado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public  ResponseEntity<Object> deleteEmpleado(@Valid @RequestBody Empleado empleado){
        try{

            Integer id = empleado.getId_empleado();

            if (id == null) {
                return ResponseEntity.badRequest().body("Error: El ID del empleado es obligatorio en el JSON.");
            }
            empleadoService.deleteEmpleado(id);

            return ResponseEntity.ok().body("Empleado eliminado correctamente");

        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
