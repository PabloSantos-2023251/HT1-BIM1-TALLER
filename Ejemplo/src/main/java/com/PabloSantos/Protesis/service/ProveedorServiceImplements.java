package com.PabloSantos.Protesis.service;

import com.PabloSantos.Protesis.entity.Empleado;
import com.PabloSantos.Protesis.entity.Proveedor;
import com.PabloSantos.Protesis.repository.ProveedoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImplements implements ProveedorService{

    private final ProveedoresRepository proveedoresRepository;

    public ProveedorServiceImplements(ProveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;
    }

    @Override
    public List<Proveedor> getAllProveedores() {
        return  proveedoresRepository.findAll();
    }

    @Override
    public Proveedor getProveedorById(Integer id) {
        return proveedoresRepository.findById(id).orElse(null);
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException {
        return proveedoresRepository.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(Integer id, Proveedor proveedor) {
        proveedor.setId_proveedor(id);
        return proveedoresRepository.save(proveedor);
    }

    @Override
    public void deleteProveedor(Integer id) {
        proveedoresRepository.deleteById(id);
    }

}
