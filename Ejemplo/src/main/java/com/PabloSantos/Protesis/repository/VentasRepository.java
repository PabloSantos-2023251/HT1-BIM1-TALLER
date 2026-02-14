package com.PabloSantos.Protesis.repository;

import com.PabloSantos.Protesis.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Integer> { }