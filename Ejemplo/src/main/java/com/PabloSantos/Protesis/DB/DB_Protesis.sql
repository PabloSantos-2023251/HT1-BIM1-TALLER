DROP DATABASE IF EXISTS DBTiendaProtesis_in5cm;
CREATE DATABASE DBTiendaProtesis_in5cm;
USE DBTiendaProtesis_in5cm;

-- 1. ESTRUCTURA DE TABLAS (4 ENTIDADES)

CREATE TABLE Proveedores (
    id_proveedor INT AUTO_INCREMENT NOT NULL,
    nombre_proveedor VARCHAR(60) NOT NULL,
    telefono_proveedor INT NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    email_proveedor VARCHAR(100) NOT NULL,
    PRIMARY KEY PK_id_proveedor (id_proveedor)
);

CREATE TABLE Empleados (
    id_empleado INT AUTO_INCREMENT NOT NULL,
    nombre_empleado VARCHAR(60) NOT NULL,
    apellido_empleado VARCHAR(60) NOT NULL,
    puesto_empleado VARCHAR(20) NULL,
    email_empleado VARCHAR(100) NOT NULL,
    PRIMARY KEY PK_id_empleado (id_empleado)
);

CREATE TABLE Protesis (
    id_protesis INT AUTO_INCREMENT NOT NULL,
    nombre_protesis VARCHAR(60) NOT NULL,
    precio_venta DOUBLE NOT NULL,
    id_proveedor INT NOT NULL,
    PRIMARY KEY PK_id_protesis (id_protesis),
    CONSTRAINT FK_protesis_proveedor FOREIGN KEY (id_proveedor) 
        REFERENCES Proveedores(id_proveedor) ON DELETE CASCADE
);

CREATE TABLE Ventas (
    id_venta INT AUTO_INCREMENT NOT NULL,
    fecha_venta DATE NOT NULL,
    cantidad INT NOT NULL,
    total DOUBLE NOT NULL,
    id_empleado INT NOT NULL,
    id_protesis INT NOT NULL,
    PRIMARY KEY PK_id_venta (id_venta),
    CONSTRAINT FK_ventas_empleado FOREIGN KEY (id_empleado) 
        REFERENCES Empleados(id_empleado) ON DELETE CASCADE,
    CONSTRAINT FK_ventas_protesis FOREIGN KEY (id_protesis) 
        REFERENCES Protesis(id_protesis) ON DELETE CASCADE
);

-- 2. INSERCIÓN DE 10 REGISTROS POR ENTIDAD

INSERT INTO Proveedores (nombre_proveedor, telefono_proveedor, direccion, email_proveedor) VALUES 
('BioTech', 555011, 'Calle A 1-23', 'info@bt.com'), ('TitanCorp', 555022, 'Ave B 4-56', 'sale@tc.com'),
('MedSupp', 555033, 'Ruta C 7-89', 'cont@ms.com'), ('ProLabs', 555044, 'Diag D 2-10', 'lab@pl.com'),
('Silicex', 555055, 'Calle E 3-11', 'mkt@sx.com'), ('AlumPro', 555066, 'Ave F 5-12', 'eng@ap.com'),
('KevlarCo', 555077, 'Ruta G 6-13', 'vent@kc.com'), ('ResinX', 555088, 'Diag H 8-14', 'prod@rx.com'),
('NanoSys', 555099, 'Calle I 9-15', 'tech@ns.com'), ('GomaInd', 555100, 'Ave J 1-16', 'corp@gi.com');

INSERT INTO Empleados (nombre_empleado, apellido_empleado, puesto_empleado, email_empleado) VALUES 
('Juan', 'Perez', 'Vendedor', 'jp@tp.com'), ('Ana', 'Lopez', 'Gerente', 'al@tp.com'),
('Luis', 'Gomez', 'Tecnico', 'lg@tp.com'), ('Marta', 'Ruiz', 'Vendedor', 'mr@tp.com'),
('Jose', 'Sosa', 'Tecnico', 'js@tp.com'), ('Elena', 'Sanz', 'Asistente', 'es@tp.com'),
('Raul', 'Diaz', 'Vendedor', 'rd@tp.com'), ('Sofia', 'Mar', 'Gerente', 'sm@tp.com'),
('Diego', 'Paz', 'Tecnico', 'dp@tp.com'), ('Rosa', 'Gil', 'Asistente', 'rg@tp.com');

INSERT INTO Protesis (nombre_protesis, precio_venta, id_proveedor) VALUES 
('Mano Zeus', 1550, 1), ('Pierna Run', 850, 2), ('Brazo Flex', 420, 3), ('Pie Solid', 315, 4), ('Dedo Real', 125, 5), 
('Rodilla H', 940, 6), ('Codo Mech', 210, 7), ('Socket S', 185, 8), ('Tobillo P', 560, 9), ('Brazo Kid', 290, 10);

INSERT INTO Ventas (fecha_venta, cantidad, total, id_empleado, id_protesis) VALUES 
('2026-01-01', 1, 1550, 1, 1), ('2026-01-02', 2, 1700, 2, 2), ('2026-01-03', 1, 420, 3, 3),
('2026-01-04', 3, 945, 4, 4), ('2026-01-05', 1, 125, 5, 5), ('2026-01-06', 2, 1880, 6, 6),
('2026-01-07', 1, 210, 7, 7), ('2026-01-08', 4, 740, 8, 8), ('2026-01-09', 1, 560, 9, 9),
('2026-01-10', 2, 580, 10, 10);

-- 3. PROCEDIMIENTOS ALMACENADOS (ENTIDAD PROTESIS)

DELIMITER //

-- Listar
CREATE PROCEDURE sp_ListarProtesis()
BEGIN
    SELECT * FROM Protesis;
END //

-- Crear
CREATE PROCEDURE sp_CrearProtesis(IN nom VARCHAR(60), IN prec DOUBLE, IN prov INT)
BEGIN
    INSERT INTO Protesis (nombre_protesis, precio_venta, id_proveedor) VALUES (nom, prec, prov);
END //

-- Actualizar
CREATE PROCEDURE sp_ActualizarProtesis(IN id_bus INT, IN nue_prec DOUBLE)
BEGIN
    UPDATE Protesis SET precio_venta = nue_prec WHERE id_protesis = id_bus;
END //

-- Eliminar
CREATE PROCEDURE sp_EliminarProtesis(IN id_bus INT)
BEGIN
    DELETE FROM Protesis WHERE id_protesis = id_bus;
END //

DELIMITER ;

DELIMITER //

-- PROCEDIMIENTOS PARA PROVEEDORES
create procedure sp_ListarProveedores()
begin
    select * from Proveedores;
end //

create procedure sp_CrearProveedor(in nom varchar(60), in tel int, in dir varchar(100), in mail varchar(100))
begin
    insert into Proveedores (nombre_proveedor, telefono_proveedor, direccion, email_proveedor) 
    values (nom, tel, dir, mail);
end //

create procedure sp_ActualizarProveedor(in id_bus int, in nue_tel int)
begin
    update Proveedores set telefono_proveedor = nue_tel where id_proveedor = id_bus;
end //

create procedure sp_EliminarProveedor(in id_bus int)
begin
    delete from Proveedores where id_proveedor = id_bus;
end //

-- PROCEDIMIENTOS PARA EMPLEADOS
create procedure sp_ListarEmpleados()
begin
    select * from Empleados;
end //

create procedure sp_CrearEmpleado(in nom varchar(60), in ape varchar(60), in pue varchar(20), in mail varchar(100))
begin
    insert into Empleados (nombre_empleado, apellido_empleado, puesto_empleado, email_empleado) 
    values (nom, ape, pue, mail);
end //

create procedure sp_ActualizarEmpleado(in id_bus int, in nue_pue varchar(20))
begin
    update Empleados set puesto_empleado = nue_pue where id_empleado = id_bus;
end //

create procedure sp_EliminarEmpleado(in id_bus int)
begin
    delete from Empleados where id_empleado = id_bus;
end //

-- PROCEDIMIENTOS PARA VENTAS
create procedure sp_ListarVentas()
begin
    select * from Ventas;
end //

create procedure sp_CrearVenta(in fec date, in cant int, in tot double, in id_emp int, in id_prot int)
begin
    insert into Ventas (fecha_venta, cantidad, total, id_empleado, id_protesis) 
    values (fec, cant, tot, id_emp, id_prot);
end //

create procedure sp_ActualizarVenta(in id_bus int, in nue_tot double)
begin
    update Ventas set total = nue_tot where id_venta = id_bus;
end //

create procedure sp_EliminarVenta(in id_bus int)
begin
    delete from Ventas where id_venta = id_bus;
end //

DELIMITER ;