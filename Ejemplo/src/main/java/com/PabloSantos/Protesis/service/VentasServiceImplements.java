package com.PabloSantos.Protesis.service;

import com.PabloSantos.Protesis.entity.Ventas;
import com.PabloSantos.Protesis.repository.VentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class VentasServiceImplements implements VentasService {
    private final VentasRepository ventasRepository;

    public VentasServiceImplements(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<Ventas> getAllVentas() { return ventasRepository.findAll(); }

    @Override
    public Ventas saveVenta(Ventas venta) { return ventasRepository.save(venta); }

    @Override
    public Ventas updateVenta(Integer id, Ventas venta) {
        venta.setId_venta(id);
        return ventasRepository.save(venta);
    }

    @Override
    public void deleteVenta(Integer id) { ventasRepository.deleteById(id); }
}
