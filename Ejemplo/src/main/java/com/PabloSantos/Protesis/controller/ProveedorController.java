package com.PabloSantos.Protesis.controller;

import com.PabloSantos.Protesis.entity.Empleado;
import com.PabloSantos.Protesis.entity.Proveedor;
import com.PabloSantos.Protesis.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/proveedores")

public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }
    @GetMapping
    public List<Proveedor> getAllProveedores(){return proveedorService.getAllProveedores();}

    @PostMapping
    public ResponseEntity<Object> createProveedor(@Valid @RequestBody Proveedor proveedor){

        try{

            String email = proveedor.getEmail_proveedor().toLowerCase();
            Proveedor createdProveedor;

            if (!(email.endsWith("@gmail.com") || email.endsWith("@hotmail.com") || email.endsWith("@outlook.com"))) {
                throw new IllegalArgumentException("Solo se permiten correos de Gmail, Hotmail o Outlook.");
            }else{
                createdProveedor = proveedorService.saveProveedor(proveedor);
            }

            return new ResponseEntity<>(createdProveedor, HttpStatus.CREATED);

        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity<Object> updateProveedor(@Valid @RequestBody Proveedor proveedor){

        try{

            String email = proveedor.getEmail_proveedor().toLowerCase();
            Proveedor updatedProveedor;
            Integer id = proveedor.getId_proveedor();

            if (!(email.endsWith("@gmail.com") || email.endsWith("@hotmail.com") || email.endsWith("@outlook.com"))) {
                throw new IllegalArgumentException("Solo se permiten correos de Gmail, Hotmail o Outlook.");
            }else{
                updatedProveedor = proveedorService.updateProveedor(id, proveedor);
            }

            return new ResponseEntity<>(updatedProveedor, HttpStatus.CREATED);

        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping
    public  ResponseEntity<Object> deleteEmpleado(@Valid @RequestBody Proveedor proveedor){
        try{

            Integer id = proveedor.getId_proveedor();

            if (id == null) {
                return ResponseEntity.badRequest().body("Error: El ID del empleado es obligatorio en el JSON.");
            }else{
                proveedorService.deleteProveedor(id);
                return ResponseEntity.ok().body("Proveedor eliminado correctamente");
            }


        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}


