package com.PabloSantos.Protesis.controller;

import com.PabloSantos.Protesis.entity.Protesis;
import com.PabloSantos.Protesis.service.ProtesisService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/protesis")
public class ProtesisController {

    private final ProtesisService protesisService;

    public ProtesisController(ProtesisService protesisService) {
        this.protesisService = protesisService;
    }

    @GetMapping
    public List<Protesis> getAllProtesis() {
        return protesisService.getAllProtesis();
    }

    @PostMapping
    public ResponseEntity<Object> createProtesis(@Valid @RequestBody Protesis protesis) {
        try {
            if (protesis.getPrecio_venta() <= 0) {
                throw new IllegalArgumentException("El precio de venta debe ser mayor a 0.");
            }
            Protesis createdProtesis = protesisService.saveProtesis(protesis);
            return new ResponseEntity<>(createdProtesis, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateProtesis(@Valid @RequestBody Protesis protesis) {
        try {
            Integer id = protesis.getId_protesis();
            if (id == null) {
                return ResponseEntity.badRequest().body("Error: El ID de la prótesis es obligatorio en el JSON.");
            }else if (protesis.getPrecio_venta() <= 0) {
                throw new IllegalArgumentException("El precio de venta debe ser mayor a 0.");
            }else{
                Protesis updatedProtesis = protesisService.updateProtesis(id, protesis);
                return new ResponseEntity<>(updatedProtesis, HttpStatus.OK);
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteProtesis(@Valid @RequestBody Protesis protesis) {
        try {
            Integer id = protesis.getId_protesis();
            if (id == null) {
                return ResponseEntity.badRequest().body("Error: El ID de la prótesis es obligatorio en el JSON.");
            }else {
                protesisService.deleteProtesis(id);
                return ResponseEntity.ok().body("Prótesis eliminada correctamente");
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}