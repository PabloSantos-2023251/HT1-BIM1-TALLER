package com.PabloSantos.Protesis.service;

import java.util.List;

import com.PabloSantos.Protesis.entity.Proveedor;
import org.springframework.stereotype.Service;

@Service
public interface ProveedorService {

    List<Proveedor> getAllProveedores();
    Proveedor getProveedorById(Integer id);
    Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException;
    Proveedor updateProveedor(Integer id, Proveedor proveedor);
    void deleteProveedor(Integer id);

}

