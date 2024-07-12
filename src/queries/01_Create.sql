DROP DATABASE IF EXISTS bdBanco;
CREATE DATABASE bdBanco;
USE bdBanco;

DROP TRIGGER IF EXISTS generar_cuotas_despues_insertar_prestamo;
DROP TRIGGER IF EXISTS Before_Insert_Cliente;
DROP TRIGGER IF EXISTS Before_Insert_Cuentas;

DROP TABLE IF EXISTS bdBanco.Roles;
DROP TABLE IF EXISTS bdBanco.Nacionalidades;
DROP TABLE IF EXISTS bdBanco.Localidades;
DROP TABLE IF EXISTS bdBanco.Usuarios;
DROP TABLE IF EXISTS bdBanco.EstadoPrestamo;
DROP TABLE IF EXISTS bdBanco.TipoCuenta;
DROP TABLE IF EXISTS bdBanco.TipoMovimiento;
DROP TABLE IF EXISTS bdBanco.Concepto;
DROP TABLE IF EXISTS bdBanco.Clientes;
DROP TABLE IF EXISTS bdBanco.Cuentas;
DROP TABLE IF EXISTS bdBanco.Prestamos;
DROP TABLE IF EXISTS bdBanco.Cuotas;
DROP TABLE IF EXISTS bdBanco.Movimientos;
DROP TABLE IF EXISTS bdBanco.Trasnferencias;


CREATE TABLE IF NOT EXISTS Roles(
	Id INT AUTO_INCREMENT,
	Descripcion varchar(15) NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id)
);

CREATE TABLE IF NOT EXISTS Provincias(
	Id INT AUTO_INCREMENT,
	Descripcion varchar(30) NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id)
);

CREATE TABLE IF NOT EXISTS Nacionalidades(
	Id INT AUTO_INCREMENT,
	Descripcion varchar(30) NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id)
);

CREATE TABLE IF NOT EXISTS Localidades(
	Id INT AUTO_INCREMENT,
	Descripcion varchar(30) NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id)
);

CREATE TABLE IF NOT EXISTS Usuarios(
	Id INT AUTO_INCREMENT,
	Id_rol INT NOT NULL,
	Nombre_usuario VARCHAR(20) UNIQUE NOT NULL,
	Email VARCHAR(50) UNIQUE NOT NULL,
	Pass varchar(8) NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id),
	CONSTRAINT FK_Id_rol FOREIGN KEY (Id_rol) REFERENCES Roles(Id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS EstadoPrestamo(
	Id INT AUTO_INCREMENT,
	Descripcion varchar(20) NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id)
);

CREATE TABLE IF NOT EXISTS Genero(
	Id INT AUTO_INCREMENT,
	Descripcion varchar(20) NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id)
);

CREATE TABLE IF NOT EXISTS TipoCuenta(
	Id INT AUTO_INCREMENT,
	Descripcion varchar(20) NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id)
);

CREATE TABLE IF NOT EXISTS TipoMovimiento(
	Id INT AUTO_INCREMENT,
	Descripcion varchar(20) NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id)
);

CREATE TABLE IF NOT EXISTS Concepto(
	Id INT AUTO_INCREMENT,
	Descripcion varchar(20) NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id)
);

CREATE TABLE IF NOT EXISTS Clientes(
	Id INT AUTO_INCREMENT,
	Nombre VARCHAR(50) NOT NULL,
	Apellido VARCHAR(50) NOT NULL,
	DNI VARCHAR(8) UNIQUE NOT NULL,
	CUIL VARCHAR(11) UNIQUE NOT NULL,
	Direccion VARCHAR(50) NOT NULL,
	Id_Localidad INT NOT NULL,
	Id_Provincia INT NOT NULL,
	Id_Nacionalidad INT NOT NULL,
	Id_Genero INT NOT NULL,
	Fecha_nacimiento DATE NULL,
	Telefono VARCHAR(50) NOT NULL,
	Id_usuario INT NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id),
	CONSTRAINT FK_Id_usuario
	FOREIGN KEY (Id_usuario) 
	REFERENCES Usuarios(Id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT,
	CONSTRAINT FK_Id_Genero
	FOREIGN KEY (Id_Genero) 
	REFERENCES Genero(Id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT,
	CONSTRAINT FK_Id_Localidad
	FOREIGN KEY (Id_Localidad) 
	REFERENCES Localidades(Id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT,
	CONSTRAINT FK_Id_Provincia
	FOREIGN KEY (Id_Provincia) 
	REFERENCES Provincias(Id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT,
	CONSTRAINT FK_Id_Nacionalidad
	FOREIGN KEY (Id_Nacionalidad) 
	REFERENCES Nacionalidades(Id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS Cuentas(
	Id INT AUTO_INCREMENT,
	Id_cliente INT NOT NULL,
	Fecha_creacion DATE NOT NULL DEFAULT (CURRENT_DATE),
	Nro_cuenta VARCHAR(13) UNIQUE,
	CBU VARCHAR(22) UNIQUE,
	Saldo DECIMAL(20, 2) DEFAULT 10000 CHECK(Saldo >= 0),
	Id_tipo_cuenta INT NOT NULL,
	Eliminado BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id),
	CONSTRAINT FK_Id_cliente
	FOREIGN KEY (Id_cliente) 
	REFERENCES Clientes(Id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT,
	CONSTRAINT FK_Id_tipo_cuenta
	FOREIGN KEY (Id_tipo_cuenta) 
	REFERENCES TipoCuenta(Id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS Prestamos(
	Id INT AUTO_INCREMENT,
	Id_cuenta INT NOT NULL,
	Fecha_pedido DATE NOT NULL DEFAULT (CURRENT_DATE),
	Id_estado INT NOT NULL,
	Monto_solicitado DECIMAL(20, 2) NOT NULL,
	Monto_cuota DECIMAL(20, 2) NOT NULL,
	Cantidad_cuotas INT NOT NULL,
	PRIMARY KEY(Id),
	CONSTRAINT FK_Id_cuenta_Prestamo 
	FOREIGN KEY (Id_cuenta) 
	REFERENCES Cuentas(Id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT,
	CONSTRAINT FK_Id_estado
	FOREIGN KEY (Id_estado)
	REFERENCES EstadoPrestamo(Id) 
	ON DELETE RESTRICT
	ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS Cuotas(
	Id INT AUTO_INCREMENT,
	Id_prestamo INT NOT NULL,
	Fecha_pago DATE NOT NULL DEFAULT (CURRENT_DATE),
	Numero_cuota INT NOT NULL,
	Paga BOOLEAN NOT NULL DEFAULT 0,
	PRIMARY KEY(Id),
	CONSTRAINT FK_Id_prestamo 
	FOREIGN KEY (Id_prestamo) 
	REFERENCES Prestamos(Id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT
);

CREATE TABLE Movimientos (
  Id INT NOT NULL AUTO_INCREMENT,
  Id_cuenta INT NOT NULL,
  Id_tipo_movimiento INT(11) NOT NULL,
  Id_concepto INT NOT NULL,
  Fecha_movimiento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  Monto_movimiento DECIMAL(20,2) NOT NULL,
  PRIMARY KEY (Id),
  KEY FK_Id_Cuenta_Movimientos (Id_cuenta),
  KEY FK_Id_tipo_movimiento (Id_tipo_movimiento),
  KEY FK_Id_concepto (Id_concepto),
  CONSTRAINT FK_Id_Cuenta_Movimientos FOREIGN KEY (Id_cuenta) REFERENCES Cuentas (Id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT,
  CONSTRAINT FK_Id_concepto
  FOREIGN KEY (Id_concepto) REFERENCES Concepto (Id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT,
  CONSTRAINT FK_Id_tipo_movimiento
  FOREIGN KEY (Id_tipo_movimiento) REFERENCES TipoMovimiento (Id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT
);

CREATE TABLE Transferencias (
  Id INT AUTO_INCREMENT,
  Id_movimiento INT NOT NULL,
  Id_cuenta_destino INT NOT NULL,
  PRIMARY KEY (Id),
  KEY FK_Id_Transferencia_Movimientos (Id_movimiento),
  KEY FK_Id_Transferencia_Cuentas (Id_cuenta_destino),
  CONSTRAINT FK_Id_Transferencia_Cuentas
  FOREIGN KEY (Id_cuenta_destino) REFERENCES Cuentas (Id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT,
  CONSTRAINT FK_Id_Transferencia_Movimientos
  FOREIGN KEY (Id_movimiento) REFERENCES Movimientos (Id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT
);
