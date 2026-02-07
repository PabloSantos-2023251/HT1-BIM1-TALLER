drop database if exists DBTiendaProtesis_in5cm;
create database DBTiendaProtesis_in5cm;
use DBTiendaProtesis_in5cm;

-- 1. ESTRUCTURA DE TABLAS (4 ENTIDADES)

create table Proveedores (
    id_proveedor int auto_increment not null,
    nombre_proveedor varchar(60) not null,
    telefono_proveedor int not null,
    direccion varchar(100) not null,
    email_proveedor varchar(100) not null,
    primary key PK_id_proveedor (id_proveedor)
);

create table Empleados (
    id_empleado int auto_increment not null,
    nombre_empleado varchar(60) not null,
    apellido_empleado varchar(60) not null,
    puesto_empleado varchar(20) null,
    email_empleado varchar(100) not null,
    primary key PK_id_empleado (id_empleado)
);

create table Protesis (
    id_protesis int auto_increment not null,
    nombre_protesis varchar(60) not null,
    precio_venta double not null,
    id_proveedor int not null,
    primary key PK_id_protesis (id_protesis),
    constraint FK_protesis_proveedor foreign key (id_proveedor) 
        references Proveedores(id_proveedor) on delete cascade
);

create table Ventas (
    id_venta int auto_increment not null,
    fecha_venta date not null,
    cantidad int not null,
    total double not null,
    id_empleado int not null,
    id_protesis int not null,
    primary key PK_id_venta (id_venta),
    constraint FK_ventas_empleado foreign key (id_empleado) 
        references Empleados(id_empleado) on delete cascade,
    constraint FK_ventas_protesis foreign key (id_protesis) 
        references Protesis(id_protesis) on delete cascade
);



-- PROCEDIMIENTOS ALMACENADOS

delimiter //

create procedure sp_ListarProtesis()
begin
    select * from Protesis;
end //

create procedure sp_CrearProtesis(in nom varchar(60), in prec double, in prov int)
begin
    insert into Protesis (nombre_protesis, precio_venta, id_proveedor) values (nom, prec, prov);
end //

create procedure sp_ActualizarProtesis(in id_bus int, in nue_prec double)
begin
    update Protesis set precio_venta = nue_prec where id_protesis = id_bus;
end //

create procedure sp_EliminarProtesis(in id_bus int)
begin
    delete from Protesis where id_protesis = id_bus;
end //

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

delimiter ;

-- INSERTS usando procedimientos almacenados

-- Proveedores
call sp_CrearProveedor('BioTech', 555011, 'Calle A 1-23', 'info@bt.com');
call sp_CrearProveedor('TitanCorp', 555022, 'Ave B 4-56', 'sale@tc.com');
call sp_CrearProveedor('MedSupp', 555033, 'Ruta C 7-89', 'cont@ms.com');
call sp_CrearProveedor('ProLabs', 555044, 'Diag D 2-10', 'lab@pl.com');
call sp_CrearProveedor('Silicex', 555055, 'Calle E 3-11', 'mkt@sx.com');
call sp_CrearProveedor('AlumPro', 555066, 'Ave F 5-12', 'eng@ap.com');
call sp_CrearProveedor('KevlarCo', 555077, 'Ruta G 6-13', 'vent@kc.com');
call sp_CrearProveedor('ResinX', 555088, 'Diag H 8-14', 'prod@rx.com');
call sp_CrearProveedor('NanoSys', 555099, 'Calle I 9-15', 'tech@ns.com');
call sp_CrearProveedor('GomaInd', 555100, 'Ave J 1-16', 'corp@gi.com');

-- Empleados
call sp_CrearEmpleado('Juan', 'Perez', 'Vendedor', 'jp@tp.com');
call sp_CrearEmpleado('Ana', 'Lopez', 'Gerente', 'al@tp.com');
call sp_CrearEmpleado('Luis', 'Gomez', 'Tecnico', 'lg@tp.com');
call sp_CrearEmpleado('Marta', 'Ruiz', 'Vendedor', 'mr@tp.com');
call sp_CrearEmpleado('Jose', 'Sosa', 'Tecnico', 'js@tp.com');
call sp_CrearEmpleado('Elena', 'Sanz', 'Asistente', 'es@tp.com');
call sp_CrearEmpleado('Raul', 'Diaz', 'Vendedor', 'rd@tp.com');
call sp_CrearEmpleado('Sofia', 'Mar', 'Gerente', 'sm@tp.com');
call sp_CrearEmpleado('Diego', 'Paz', 'Tecnico', 'dp@tp.com');
call sp_CrearEmpleado('Rosa', 'Gil', 'Asistente', 'rg@tp.com');

-- Protesis
call sp_CrearProtesis('Mano Zeus', 1550, 1);
call sp_CrearProtesis('Pierna Run', 850, 2);
call sp_CrearProtesis('Brazo Flex', 420, 3);
call sp_CrearProtesis('Pie Solid', 315, 4);
call sp_CrearProtesis('Dedo Real', 125, 5);
call sp_CrearProtesis('Rodilla H', 940, 6);
call sp_CrearProtesis('Codo Mech', 210, 7);
call sp_CrearProtesis('Socket S', 185, 8);
call sp_CrearProtesis('Tobillo P', 560, 9);
call sp_CrearProtesis('Brazo Kid', 290, 10);

-- Ventas
call sp_CrearVenta('2026-01-01', 1, 1550, 1, 1);
call sp_CrearVenta('2026-01-02', 2, 1700, 2, 2);
call sp_CrearVenta('2026-01-03', 1, 420, 3, 3);
call sp_CrearVenta('2026-01-04', 3, 945, 4, 4);
call sp_CrearVenta('2026-01-05', 1, 125, 5, 5);
call sp_CrearVenta('2026-01-06', 2, 1880, 6, 6);
call sp_CrearVenta('2026-01-07', 1, 210, 7, 7);
call sp_CrearVenta('2026-01-08', 4, 740, 8, 8);
call sp_CrearVenta('2026-01-09', 1, 560, 9, 9);
call sp_CrearVenta('2026-01-10', 2, 580, 10, 10);

