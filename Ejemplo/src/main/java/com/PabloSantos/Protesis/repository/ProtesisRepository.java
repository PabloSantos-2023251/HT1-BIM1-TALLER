package com.PabloSantos.Protesis.repository;

import com.PabloSantos.Protesis.entity.Protesis;
import com.PabloSantos.Protesis.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtesisRepository extends JpaRepository<Protesis,Integer>{ }