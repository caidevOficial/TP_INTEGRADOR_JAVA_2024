INSERT INTO Roles (descripcion) VALUES 
('Administrador'), 
('Cliente');

INSERT INTO Provincias (Descripcion) VALUES
('Buenos Aires'),
('Buenos Aires-GBA'),
('Capital Federal'),
('Catamarca'),
('Chaco'),
('Chubut'),
('Córdoba'),
('Corrientes'),
('Entre Ríos'),
('Formosa'),
('Jujuy'),
('La Pampa'),
('La Rioja'),
('Mendoza'),
('Misiones'),
('Neuquén'),
('Río Negro'),
('Salta'),
('San Juan'),
('San Luis'),
('Santa Cruz'),
('Santa Fe'),
('Santiago del Estero'),
('Tierra del Fuego'),
('Tucumán');

INSERT INTO Nacionalidades (Descripcion) VALUES
('Afganistán'),
('Islas Gland'),
('Albania'),
('Alemania'),
('Andorra'),
('Angola'),
('Anguilla'),
('Antártida'),
('Antigua y Barbuda'),
('Antillas Holandesas'),
('Arabia Saudí'),
('Argelia'),
('Argentina'),
('Armenia'),
('Aruba'),
('Australia'),
('Austria'),
('Azerbaiyán');

INSERT INTO Localidades (Descripcion) VALUES
('25 de Mayo'),
('3 de febrero'),
('A. Alsina'),
('A. Gonzáles Cháves'),
('Aguas Verdes'),
('Alberti'),
('Arrecifes'),
('Ayacucho'),
('Azul'),
('Bahía Blanca'),
('Balcarce'),
('Baradero'),
('Benito Juárez'),
('Berisso'),
('Bolívar'),
('Bragado'),
('Brandsen'),
('Campana'),
('Cañuelas'),
('Capilla del Señor'),
('Capitán Sarmiento'),
('Carapachay'),
('Carhue'),
('Cariló'),
('Carlos Casares'),
('Carlos Tejedor'),
('Carmen de Areco'),
('Carmen de Patagones'),
('Castelli'),
('Chacabuco');

INSERT INTO Usuarios (Id_rol, Nombre_usuario, Email, Pass, Eliminado) VALUES
(1, 'admin', 'admin@example.com', 'admin123', 0),
(2, 'juan', 'juan@gmail.com', 'user123', 0),
(2, 'maria', 'maria@gmail.com', 'user123', 0),
(2, 'carlos', 'carlos@gmail.com', 'user123', 0),
(2, 'laura', 'laura@gmail.com', 'user123', 0),
(2, 'pedro', 'pedro@gmail.com', 'user123', 0),
(2, 'ana', 'ana@gmail.com', 'user123', 0),
(2, 'javier', 'javier@gmail.com', 'user123', 0),
(2, 'juanita', 'juanita@gmail.com', 'user123', 0),
(2, 'fernando', 'fernando@gmail.com', 'user123', 0),
(2, 'silvia', 'silvia@gmail.com', 'user123', 0),
(2, 'roberto', 'roberto@gmail.com', 'user123', 0),
(2, 'nora', 'nora@gmail.com', 'user123', 0),
(2, 'gustavo', 'gustavo@gmail.com', 'user123', 0),
(2, 'rosa', 'rosa@gmail.com', 'user123', 0),
(2, 'luis', 'luis@gmail.com', 'user123', 0);

INSERT INTO EstadoPrestamo (Descripcion) VALUES
("Aceptado"),
("Rechazado"),
("Pendiente");

INSERT INTO Genero (descripcion) VALUES
('Masculino'), 
('Femenimo'), 
('Otros');

INSERT INTO TipoCuenta (Descripcion) VALUES 
('Caja de Ahorro'), 
('Cuenta Corriente');

INSERT INTO TipoMovimiento (Descripcion) VALUES 
('Alta de cuenta'),
('Alta de préstamo'),
('Pago de préstamo'),
('Transferencia');

INSERT INTO Concepto (Descripcion) VALUES 
('Varios'),
('Haberes'),
('Inversiones'),
('Alta'),
('Prestamo'),
('Alquiler'),
('Cuota'),
('Expensas'),
('Factura'),
('Seguro'),
('Honorarios');

INSERT INTO Clientes (Nombre, Apellido, DNI, CUIL, Direccion, Id_Localidad, Id_Provincia, Id_Nacionalidad, Id_genero, Fecha_nacimiento, Telefono, Id_usuario) VALUES
('Juan', 'Pérez', '12345678', '20123456789', 'Calle 123', 1, 1, 13, 1, '1990-01-15', '123456789', 2),
('María', 'López', '87654321', '27876543213', 'Calle 456', 2, 2, 13, 2, '1985-08-20', '987654321', 3),
('Carlos', 'González', '11223344', '20112233445', 'Calle 789', 3, 3, 13, 1, '1988-03-10', '1122334455', 4),
('Laura', 'Martínez', '55667788', '20556677886', 'Avenida XYZ', 4, 4, 13, 2, '1995-07-18', '5566778899', 5),
('Pedro', 'Rodríguez', '99887766', '20998877669', 'Calle 567', 5, 5, 13, 1, '1980-12-25', '9988776655', 6),
('Ana', 'Fernández', '33221144', '20332211449', 'Calle 456', 6, 6, 13, 2, '1992-04-30', '3322114455', 7),
('Javier', 'Díaz', '77665544', '20776655449', 'Calle 678', 7, 7, 13, 1, '1987-09-15', '7766554477', 8),
('Rosa', 'López', '88776655', '20887766559', 'Avenida ABC', 8, 8, 13, 2, '1993-02-20', '8877665599', 9),
('Luis', 'Ramírez', '11559977', '21115599779', 'Calle 789', 9, 9, 13, 1, '1975-06-05', '1155997766', 10),
('Claudia', 'Sánchez', '33557799', '21335577995', 'Avenida XYZ', 10, 10, 13, 2, '1986-11-28', '3355779988', 11),
('Andrés', 'Gómez', '44998877', '21449988779', 'Calle 456', 11, 11, 13, 1, '1978-08-12', '4499887766', 12),
('Patricia', 'Torres', '66221144', '21662211449', 'Calle 678', 12, 12, 13, 2, '1990-03-15', '6622114455', 13),
('Carlos', 'Martínez', '33445566', '20334455669', 'Calle 789', 13, 13, 13, 1, '1993-04-05', '3344556677', 14),
('Sofía', 'García', '77881122', '20778811225', 'Avenida ABC', 14, 14, 13, 2, '1989-11-10', '7788112299', 15),
('Juanita', 'Rodriguez', '77874122', '20775561225', 'Avenida Siempre Viva', 15, 15, 13, 2, '1989-11-10', '7788112299', 16);

-- Insertar datos en la tabla Cuentas
INSERT INTO Cuentas (Id_cliente, Nro_cuenta, CBU, Id_tipo_cuenta)
VALUES 
(1, '1110005703028', '5977383411100065258199', 1),
(2, '1110005703098', '2292254811100051177666', 2),
(3, '1110008490654', '2292254811100063325233', 2),
(4, '1234567890456', '4398259411100098418935', 1),
(5, '1110000173334', '2210006311100026915345', 2),
(6, '1110005588977', '5461739511100037615575', 1),
(7, '1110005643172', '4345353711100078241494', 2),
(8, '1234567890890', '9876543210987654321880', 1),
(9, '1110001698907', '1433326211100079352661', 2),
(10, '1234567870012', '0445420911100042942103', 1),
(11, '1110002377449', '1016525411100040753303', 2),
(12, '1234567890234', '2288873611100074285456', 1),
(13, '9876543210345', '2833903011100043031990', 2),
(14, '1110000539458', '7325859911100052001984', 1),
(15, '9876543210567', '2377593611100057030286', 2);

INSERT INTO Movimientos (Id_cuenta, Id_tipo_movimiento, Id_concepto, Fecha_movimiento, Monto_movimiento) VALUES
(1, 1, 4, '2024-05-16', 10000),
(2, 1, 4, '2024-05-16', 10000),
(3, 1, 4, '2024-05-16', 10000),
(4, 1, 4, '2024-05-16', 10000),
(5, 1, 4, '2024-05-16', 10000),
(6, 1, 4, '2024-05-16', 10000),
(7, 1, 4, '2024-05-16', 10000),
(8, 1, 4, '2024-05-16', 10000),
(9, 1, 4, '2024-05-16', 10000),
(10, 1, 4, '2024-03-16', 10000),
(11, 1, 4, '2024-03-16', 10000),
(12, 1, 4, '2024-03-16', 10000),
(13, 1, 4, '2024-03-16', 10000),
(14, 1, 4, '2024-03-16', 10000),
(15, 1, 4, '2024-03-16', 10000),
(5, 4, 1, '2024-02-16', 26000),
(6, 4, 1, '2024-02-16', 26000),
(7, 4, 1, '2024-02-16', 26000),
(8, 4, 1, '2024-02-16', 26000),
(9, 4, 1, '2024-02-16', 26000),
(10, 4, 1, '2024-05-16', 26000),
(11, 4, 1, '2024-05-16', 26000),
(12, 4, 1, '2024-05-16', 26000),
(13, 4, 1, '2024-05-16', 26000),
(1, 4, 2, '2024-01-16', 1000000),
(2, 4, 2, '2024-01-16', 1000000),
(3, 4, 2, '2024-01-16', 1000000),
(4, 4, 2, '2024-01-16', 1000000),
(5, 4, 2, '2024-01-16', 1000000),
(6, 4, 2, '2024-01-16', 1000000),
(14, 4, 2, '2024-04-16', 1000000),
(15, 4, 2, '2024-04-16', 1000000),
(13, 4, 2, '2024-04-16', 1000000);

