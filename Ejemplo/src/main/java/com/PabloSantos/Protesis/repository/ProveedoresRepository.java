package com.PabloSantos.Protesis.repository;


import com.PabloSantos.Protesis.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedor,Integer> {
}
