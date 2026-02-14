package com.PabloSantos.Protesis.service;

import com.PabloSantos.Protesis.entity.Ventas;
import com.PabloSantos.Protesis.repository.VentasRepository;
import org.springframework.stereotype.Service;
import java.util.List;

public interface VentasService {
    List<Ventas> getAllVentas();
    Ventas saveVenta(Ventas venta);
    Ventas updateVenta(Integer id, Ventas venta);
    void deleteVenta(Integer id);
}