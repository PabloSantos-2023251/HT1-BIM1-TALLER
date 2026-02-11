package com.PabloSantos.Protesis.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "Protesis")

public class Protesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_protesis")
    private Integer id_protesis;

    @Column(name = "nombre_protesis")
    private String nombre_protesis;

    @Column(name = "precio_venta")
    private double precio_venta;

    @Column(name = "id_proveedor")
    private  Integer id_proveedor;

    public Integer getId_protesis() {
        return id_protesis;
    }

    public void setId_protesis(Integer id_protesis) {
        this.id_protesis = id_protesis;
    }

    public String getNombre_protesis() {
        return nombre_protesis;
    }

    public void setNombre_protesis(String nombre_protesis) {
        this.nombre_protesis = nombre_protesis;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
}
