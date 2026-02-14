package com.PabloSantos.Protesis.controller;

import com.PabloSantos.Protesis.entity.Ventas;
import com.PabloSantos.Protesis.service.VentasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    public List<Ventas> getAllVentas() {
        return ventasService.getAllVentas();
    }

    @PostMapping
    public ResponseEntity<Object> createVenta(@Valid @RequestBody Ventas venta) {
        try {
            // Validación de campos no vacíos o nulos
            if (venta.getFecha_venta() == null ||
                    venta.getCantidad() == null || venta.getCantidad() <= 0 ||
                    venta.getTotal() == null || venta.getTotal() <= 0 ||
                    venta.getId_empleado() == null ||
                    venta.getId_protesis() == null) {

                return ResponseEntity.badRequest().body("Error: Todos los campos son obligatorios. Cantidad y Total deben ser mayores a 0.");
            }

            Ventas createdVenta = ventasService.saveVenta(venta);
            return new ResponseEntity<>(createdVenta, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear la venta: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateVenta(@Valid @RequestBody Ventas venta) {
        try {
            Integer id = venta.getId_venta();

            if (id == null) {
                return ResponseEntity.badRequest().body("Error: El ID de la venta es obligatorio.");
            }

            if (venta.getFecha_venta() == null ||
                    venta.getCantidad() == null || venta.getCantidad() <= 0 ||
                    venta.getTotal() == null || venta.getTotal() <= 0 ||
                    venta.getId_empleado() == null ||
                    venta.getId_protesis() == null) {

                return ResponseEntity.badRequest().body("Error: Datos incompletos o inválidos para la actualización.");
            }

            Ventas updatedVenta = ventasService.updateVenta(id, venta);
            return new ResponseEntity<>(updatedVenta, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteVenta(@RequestBody Ventas venta) {
        Integer id = venta.getId_venta();
        if (id == null) {
            return ResponseEntity.badRequest().body("Error: El ID de la venta es obligatorio.");
        }
        ventasService.deleteVenta(id);
        return ResponseEntity.ok().body("Venta eliminada correctamente.");
    }
}