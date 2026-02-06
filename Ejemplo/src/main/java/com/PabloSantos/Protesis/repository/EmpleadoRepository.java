package com.PabloSantos.Protesis.repository;

import com.PabloSantos.Protesis.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {

}
