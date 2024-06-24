package queries;

public class Queries {
	
	public String queryBuscarId = "SELECT Id FROM Clientes WHERE DNI = ?;";
	public String buscarIdCliente = "SELECT c.Id FROM Clientes AS c WHERE c.Id_usuario = ?;";
	
	public String altaCuenta = "UPDATE Cuentas SET Eliminado = 0 WHERE Id = ?;";
	public String bajaCuenta = 	 "UPDATE Cuentas SET Eliminado = 1 WHERE Id = ?;";
	public String crearCuenta =  "INSERT INTO Cuentas (Id_cliente, Nro_cuenta, CBU, Id_tipo_cuenta) VALUES(?, ?, ?, ?);";
	public String cuentaPorCbu = "" +
			 "SELECT c.Id AS Id," + 
			 "	cl.Id AS IdCliente," + 
			 "	c.Fecha_creacion AS FechaCreacion," + 
			 "	c.Nro_cuenta AS numeroCuenta," + 
			 "	c.CBU As CBU," + 
			 "	c.Saldo AS Saldo," + 
			 "	tc.Id As IdTipoCuenta," + 
			 "	tc.Descripcion As DescripcionTipo," + 
			 "	c.Eliminado AS Eliminado " + 
			 "FROM Cuentas c " + 
			 "INNER JOIN Clientes cl ON cl.Id = c.Id_cliente " + 
			 "INNER JOIN TipoCuenta tc ON tc.Id = c.Id_tipo_cuenta " + 
			 "WHERE CBU = ?;";
	public String editarCuenta = "UPDATE Cuentas SET Nro_cuenta = ?, CBU = ?, Saldo = ?, Id_tipo_cuenta = ? WHERE Id = ?;";
	public String getCuentasBuscar = "" +
			 "SELECT c.Id AS Id," + 
			 "	cl.Id AS IdCliente," + 
			 "	cl.Nombre AS Nombre," + 
			 "	cl.Apellido AS Apellido," + 
			 "	cl.DNI As DNI," + 
			 "	cl.CUIL AS CUIL," + 
			 "	c.Fecha_creacion AS FechaCreacion," + 
			 "	c.Nro_cuenta AS numeroCuenta," + 
			 "	c.CBU AS CBU," + 
			 "	c.Saldo AS Saldo," + 
			 "	tc.Id As IdTipoCuenta," + 
			 "	tc.Descripcion AS nombreTipoCuenta," + 
			 "	c.Eliminado AS Eliminado" + 
			 "FROM Cuentas AS c " + 
			 "INNER JOIN Clientes cl ON cl.Id = c.Id_cliente " + 
			 "INNER JOIN TipoCuenta tc ON tc.Id = c.Id_tipo_cuenta " + 
			 "WHERE c.Id LIKE ? " + 
			 "OR cl.Nombre LIKE ? " + 
			 "OR cl.Apellido LIKE ? " + 
			 "OR cl.DNI LIKE ? " + 
			 "OR c.Fecha_creacion LIKE ? " + 
			 "OR c.Nro_cuenta LIKE ? " + 
			 "OR c.CBU LIKE ? " + 
			 "OR c.Saldo LIKE ? " + 
			 "OR tc.Descripcion LIKE ?;";
	public String getCuentasClientes =  "" +
			"SELECT c.Id AS Id," + 
			"	cl.Id AS IdCliente," + 
			"	cl.Nombre AS Nombre," + 
			"	cl.Apellido AS Apellido," + 
			"	cl.DNI As DNI," + 
			"	cl.CUIL AS CUIL," + 
			"	c.Fecha_creacion AS FechaCreacion," + 
			"	c.Nro_cuenta AS numeroCuenta," + 
			"	c.CBU AS CBU," + 
			"	c.Saldo AS Saldo," + 
			"	tc.Id As IdTipoCuenta," + 
			"	tc.Descripcion AS nombreTipoCuenta," + 
			"	c.Eliminado AS Eliminado" + 
			"FROM Cuentas AS c " + 
			"INNER JOIN Clientes cl ON cl.Id = c.Id_cliente " + 
			"INNER JOIN TipoCuenta tc ON tc.Id = c.Id_tipo_cuenta " + 
			"WHERE cl.Id = ? " + 
			"AND c.Eliminado = 0;";
	public String getCuentas =  "" +
			"SELECT c.Id AS Id," + 
			"	cl.Id AS IdCliente," + 
			"	cl.Nombre AS Nombre," + 
			"	cl.Apellido AS Apellido," + 
			"	cl.DNI As DNI," + 
			"	cl.CUIL AS CUIL," + 
			"	c.Fecha_creacion AS FechaCreacion," + 
			"	c.Nro_cuenta AS numeroCuenta," + 
			"	c.CBU AS CBU," + 
			"	c.Saldo AS Saldo," + 
			"	tc.Id As IdTipoCuenta," + 
			"	tc.Descripcion AS nombreTipoCuenta," + 
			"	c.Eliminado AS Eliminado " + 
			"FROM Cuentas AS c " + 
			"INNER JOIN Clientes cl ON cl.Id = c.Id_cliente " + 
			"INNER JOIN TipoCuenta tc ON tc.Id = c.Id_tipo_cuenta ";
	public String idPorIdCliente = "SELECT c.Id AS Id, cl.Id AS IdCliente FROM Cuentas AS cINNER JOIN Clientes AS cl ON cl.Id = c.Id_cliente WHERE cl.Id = ?;";
	public String restarSaldo = "UPDATE Cuentas SET Saldo = Saldo - ? WHERE Id = ?;";
	public String sumarSaldo = "UPDATE Cuentas SET Saldo = Saldo + ? WHERE Id = ?;";
	public String ultimoId = "SELECT MAX(c.Id) + 1 AS Id FROM Cuentas AS c;";	
	public String altaCuentaMovimiento = "INSERT INTO Movimientos (Id_cuenta, Id_tipo_movimiento, Id_concepto, Monto_movimiento)VALUES (?, ?, ?, ?);";
	public String getMovimientos =  "" +
			"SELECT m.Id AS Id," + 
			"	c.Id AS IdCuenta," + 
			"	c.Saldo AS Saldo," + 
			"	tm.Id AS IdTipoMovimiento," + 
			"	tm.Descripcion AS DescripcionMovimiento," + 
			"	con.Id AS IdConcepto," + 
			"	con.Descripcion AS DescripcionConcepto," + 
			"	m.Fecha_movimiento AS FechaMovimiento," + 
			"	m.Monto_movimiento AS MontoMovimiento" + 
			"FROM Movimientos AS m " + 
			"INNER JOIN Cuentas c ON c.Id = m.Id_cuenta " + 
			"INNER JOIN TipoMovimiento tm ON tm.Id = m.Id_tipo_movimiento " + 
			"INNER JOIN Concepto con ON con.Id = m.Id_concepto " + 
			"WHERE c.Id = ?;";
	public String getMovimientosBuscar = "" +
			"SELECT m.Id AS Id," + 
			 "	c.Id AS IdCuenta," + 
			 "	c.Saldo AS Saldo," + 
			 "	tm.Id AS IdTipoMovimiento," + 
			 "	tm.Descripcion AS DescripcionMovimiento," + 
			 "	con.Id AS IdConcepto," + 
			 "	con.Descripcion AS DescripcionConcepto," + 
			 "	m.Fecha_movimiento AS FechaMovimiento," + 
			 "	m.Monto_movimiento AS MontoMovimiento" + 
			 "FROM Movimientos AS m " + 
			 "INNER JOIN Cuentas c ON c.Id = m.Id_cuenta " + 
			 "INNER JOIN TipoMovimiento tm ON tm.Id = m.Id_tipo_movimiento " + 
			 "INNER JOIN Concepto con ON con.Id = m.Id_concepto " + 
			 "WHERE c.Id = ? " + 
			 "AND (" + 
			 "tm.Descripcion LIKE ? " + 
		  	 "OR con.Descripcion LIKE ? " + 
			 "OR m.Fecha_movimiento LIKE ? " + 
			 "OR m.Monto_movimiento LIKE ? " + 
			 ");";
	public String getImporteTotal = "" +
			"SELECT " +
			"SUM(m.Monto_movimiento) AS importeTotal " +
			"FROM Movimientos AS m " +
			"INNER JOIN TipoMovimiento tm " +
			"	ON tm.Id = m.Id_tipo_movimiento " +
			"WHERE tm.Id = ? AND MONTH(m.Fecha_movimiento) = ?;";
	public String getCoutasPrestamo =   "" +
			"SELECT c.Id AS Id," + 
			"	p.Id AS IdPrestamo," + 
			"	p.Monto_cuota AS MontoCouta," + 
			"	c.Fecha_pago AS FechaPago," + 
			"	c.Numero_cuota AS NumeroCuota," + 
			"	c.Paga As Paga" + 
			"FROM Coutas AS c " + 
			"INNER JOIN Prestamos p ON p.Id = c.Id_prestamo " + 
			"WHERE c.Id_prestamo = ? " + 
			"AND c.Paga = 0 " + 
			"AND DATE(c.Fecha_pago) <= DATE(NOW());";
	public String setCuotaPrestamo = "UPDATE Coutas SET Paga = 1 WHERE Id = ?;";
	
	public String getClientes = "" +
			"SELECT c.Id AS Id, " + 
			"    c.Nombre AS Nombre, " + 
			"    c.Apellido AS Apellido, " + 
			"    c.DNI AS DNI, " + 
			"    c.CUIL AS CUIL, " + 
			"    c.Direccion AS Direccion, " + 
			"    c.Eliminado AS Eliminado, " + 
			"    l.Id AS IdLocalidad, " + 
			"    l.Descripcion AS nombreLocalidad, " + 
			"    l.Eliminado AS eliminadoLocalidad, " + 
			"    p.Id AS IdProvincia, " + 
			"    p.Descripcion AS nombreProvincia, " + 
			"    p.Eliminado AS eliminadoProvincia, " + 
			"    n.Id AS IdNacionalidad, " + 
			"    n.Descripcion AS nombreNacionalidad, " + 
			"    l.Eliminado AS eliminadoNacionalidad, " + 
			"    g.Id AS IdGenero, " + 
			"    g.Descripcion AS genero, " + 
			"    g.Eliminado AS eliminadoGenero, " + 
			"    c.Fecha_nacimiento As FechaNacimiento, " + 
			"    c.Telefono As Telefono, " + 
			"    u.Id AS IdUsuario, " + 
			"    r.Id as IdRol, " + 
			"    r.Descripcion AS nombreRol, " + 
			"    u.Nombre_usuario AS nombreUsuario, " + 
			"    u.Email AS Email " + 
			"FROM Clientes c " + 
			"    INNER JOIN Localidades l ON l.Id = c.Id_Localidad " + 
			"    INNER JOIN Provincias p ON p.Id = c.Id_Provincia " + 
			"    INNER JOIN Nacionalidades n ON n.Id = c.Id_Nacionalidad " + 
			"    INNER JOIN Genero g ON g.Id = c.Id_Genero " + 
			"    INNER JOIN Usuarios u ON u.Id = c.Id_usuario " + 
			"    INNER JOIN Roles r On u.Id_rol = r.Id;";
	
	public String getCliente = "" +
			"SELECT c.Id AS Id, " + 
			"    c.Nombre AS Nombre, " + 
			"    c.Apellido AS Apellido, " + 
			"    c.DNI AS DNI, " + 
			"    c.CUIL AS CUIL, " + 
			"    c.Direccion AS Direccion, " + 
			"    c.Eliminado AS Eliminado, " + 
			"    l.Id AS IdLocalidad, " + 
			"    l.Descripcion AS nombreLocalidad, " + 
			"    l.Eliminado AS eliminadoLocalidad, " + 
			"    p.Id AS IdProvincia, " + 
			"    p.Descripcion AS nombreProvincia, " + 
			"    p.Eliminado AS eliminadoProvincia, " + 
			"    n.Id AS IdNacionalidad, " + 
			"    n.Descripcion AS nombreNacionalidad, " + 
			"    l.Eliminado AS eliminadoNacionalidad, " + 
			"    g.Id AS IdGenero, " + 
			"    g.Descripcion AS genero, " + 
			"    g.Eliminado AS eliminadoGenero, " + 
			"    c.Fecha_nacimiento As FechaNacimiento, " + 
			"    c.Telefono As Telefono, " + 
			"    u.Id AS IdUsuario, " + 
			"    r.Id as IdRol, " + 
			"    r.Descripcion AS nombreRol, " + 
			"    u.Nombre_usuario AS nombreUsuario, " + 
			"    u.Email AS Email " + 
			"FROM Clientes c " + 
			"    INNER JOIN Localidades l ON l.Id = c.Id_Localidad " + 
			"    INNER JOIN Provincias p ON p.Id = c.Id_Provincia " + 
			"    INNER JOIN Nacionalidades n ON n.Id = c.Id_Nacionalidad " + 
			"    INNER JOIN Genero g ON g.Id = c.Id_Genero " + 
			"    INNER JOIN Usuarios u ON u.Id = c.Id_usuario " + 
			"    INNER JOIN Roles r On u.Id_rol = r.Id " + 
			"WHERE c.Id = ?;";
	
	public String getClientesPorTermino = "" +
			"SELECT c.Id AS Id, " + 
			"    c.Nombre AS Nombre, " + 
			"    c.Apellido AS Apellido, " + 
			"    c.DNI AS DNI, " + 
			"    c.CUIL AS CUIL, " + 
			"    c.Direccion AS Direccion, " + 
			"    c.Eliminado AS Eliminado, " + 
			"    l.Id AS IdLocalidad, " + 
			"    l.Descripcion AS nombreLocalidad, " + 
			"    l.Eliminado AS eliminadoLocalidad, " + 
			"    p.Id AS IdProvincia, " + 
			"    p.Descripcion AS nombreProvincia, " + 
			"    p.Eliminado AS eliminadoProvincia, " + 
			"    n.Id AS IdNacionalidad, " + 
			"    n.Descripcion AS nombreNacionalidad, " + 
			"    l.Eliminado AS eliminadoNacionalidad, " + 
			"    g.Id AS IdGenero, " + 
			"    g.Descripcion AS genero, " + 
			"    g.Eliminado AS eliminadoGenero, " + 
			"    c.Fecha_nacimiento As FechaNacimiento, " + 
			"    c.Telefono As Telefono, " + 
			"    u.Id AS IdUsuario, " + 
			"    r.Id as IdRol, " + 
			"    r.Descripcion AS nombreRol, " + 
			"    u.Nombre_usuario AS nombreUsuario, " + 
			"    u.Email AS Email " + 
			"FROM Clientes c " + 
			"    INNER JOIN Localidades l ON l.Id = c.Id_Localidad " + 
			"    INNER JOIN Provincias p ON p.Id = c.Id_Provincia " + 
			"    INNER JOIN Nacionalidades n ON n.Id = c.Id_Nacionalidad " + 
			"    INNER JOIN Genero g ON g.Id = c.Id_Genero " + 
			"    INNER JOIN Usuarios u ON u.Id = c.Id_usuario " + 
			"    INNER JOIN Roles r On u.Id_rol = r.Id " + 
			"WHERE c.Id LIKE ? " + 
			"    OR c.DNI LIKE ? " + 
			"    OR c.Nombre LIKE ? " + 
			"    OR c.Apellido LIKE ? " + 
			"    OR c.Telefono LIKE ? " + 
			"    OR c.Direccion LIKE ? " + 
			"    OR l.Descripcion LIKE ? " + 
			"    OR p.Descripcion LIKE ? " + 
			"    OR n.Descripcion LIKE ? " + 
			"    OR g.Descripcion LIKE ? " + 
			"    OR c.Fecha_nacimiento LIKE ?;";
	
	public String crearCliente = "" +
			"INSERT INTO Clientes ( " + 
			"        Nombre, " + 
			"        Apellido, " + 
			"        DNI, " + 
			"        CUIL, " + 
			"        Direccion, " + 
			"        Id_Localidad, " + 
			"        Id_Provincia, " + 
			"        Id_Nacionalidad, " + 
			"        Id_Genero, " + 
			"        Fecha_nacimiento, " + 
			"        Telefono, " + 
			"        Id_usuario " + 
			"    ) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	public String bajaCliente = "UPDATE Clientes  SET Eliminado = 1 WHERE DNI = ?;";
	
	public String altaCliente = "UPDATE Clientes  SET Eliminado = 0 WHERE DNI = ?;";
	
	public String editarCliente = "UPDATE Clientes " + 
			"SET Nombre = ?, " + 
			"    Apellido = ?, " + 
			"    DNI = ?, " + 
			"    CUIL = ?, " + 
			"    Direccion = ?, " + 
			"    Id_Localidad = ?, " + 
			"    Id_Provincia = ?, " + 
			"    Id_Nacionalidad = ?, " + 
			"    Id_Genero = ?, " + 
			"    Fecha_nacimiento = ?, " + 
			"    Telefono = ? " + 
			"WHERE Id = ?;";
	
	public String getPrestamos = "" +
			"SELECT p.Id AS Id, " + 
			"    c.Id AS IdCuenta, " + 
			"    c.Nro_cuenta AS numeroCuenta, " + 
			"    c.CBU AS CBUCuenta, " + 
			"    p.Fecha_pedido AS FechaPedido, " + 
			"    ep.Id AS IdEstado, " + 
			"    ep.Descripcion AS DescripcionEstado, " + 
			"    p.Monto_solicitado AS montoSolicitado, " + 
			"    p.Monto_cuota AS montoCuota, " + 
			"    p.Cantidad_coutas AS cantCoutas " + 
			"FROM Prestamos p " + 
			"    INNER JOIN Cuentas c ON c.Id = p.Id " + 
			"    INNER JOIN EstadoPrestamo ep ON ep.Id = p.Id_estado;";
	
	public String getPrestamosPendientes = "" +
			"SELECT p.Id AS Id, " + 
			"    c.Id AS IdCuenta, " + 
			"    c.Nro_cuenta AS numeroCuenta, " + 
			"    c.CBU AS CBUCuenta, " + 
			"    p.Fecha_pedido AS FechaPedido, " + 
			"    ep.Id AS IdEstado, " + 
			"    ep.Descripcion AS DescripcionEstado, " + 
			"    p.Monto_solicitado AS montoSolicitado, " + 
			"    p.Monto_cuota AS montoCuota, " + 
			"    p.Cantidad_coutas AS cantCoutas " + 
			"FROM Prestamos p " + 
			"    INNER JOIN Cuentas c ON c.Id = p.Id_cuenta " + 
			"    INNER JOIN EstadoPrestamo ep ON ep.Id = p.Id_estado " + 
			"WHERE ep.Id = 3;";
	
	public String getPrestamosAprobados = "" +
			"SELECT p.Id AS Id, " + 
			"    c.Id AS IdCuenta, " + 
			"    c.Nro_cuenta AS numeroCuenta, " + 
			"    c.CBU AS CBUCuenta, " + 
			"    p.Fecha_pedido AS FechaPedido, " + 
			"    ep.Id AS IdEstado, " + 
			"    ep.Descripcion AS DescripcionEstado, " + 
			"    p.Monto_solicitado AS montoSolicitado, " + 
			"    p.Monto_cuota AS montoCuota, " + 
			"    p.Cantidad_coutas AS cantCoutas " + 
			"FROM Prestamos p " + 
			"    INNER JOIN Cuentas c ON c.Id = p.Id_cuenta " + 
			"    INNER JOIN EstadoPrestamo ep ON ep.Id = p.Id_estado " + 
			"WHERE ep.Id = 1 " + 
			"    AND c.Id = ?;";
	
	public String getPrestamosPendientesBuscar = "" +
			"SELECT p.Id AS Id, " + 
			"    c.Id AS IdCuenta, " + 
			"    c.Nro_cuenta AS numeroCuenta, " + 
			"    c.CBU AS CBUCuenta, " + 
			"    p.Fecha_pedido AS FechaPedido, " + 
			"    ep.Id AS IdEstado, " + 
			"    ep.Descripcion AS DescripcionEstado, " + 
			"    p.Monto_solicitado AS montoSolicitado, " + 
			"    p.Monto_cuota AS montoCuota, " + 
			"    p.Cantidad_coutas AS cantCoutas " + 
			"FROM Prestamos p " + 
			"    INNER JOIN Cuentas c ON c.Id = p.Id_cuenta " + 
			"    INNER JOIN EstadoPrestamo ep ON ep.Id = p.Id_estado " + 
			"WHERE ep.Id = 3 " + 
			"    AND ( " + 
			"        p.Id LIKE ? " + 
			"        OR c.Nro_cuenta LIKE ? " + 
			"        OR c.CBU LIKE ? " + 
			"        OR p.Fecha_pedido LIKE ? " + 
			"        OR p.Monto_solicitado LIKE ? " + 
			"        OR p.Monto_cuota LIKE ? " + 
			"        OR p.Cantidad_coutas LIKE ? " + 
			"    );";
	
	public String rechazarPrestamo = ""+
			"UPDATE Prestamos " + 
			"SET Id_estado = 2 " + 
			"WHERE Id = ?;";
	
	public String aceptarPrestamo = "" +
			"UPDATE Prestamos " + 
			"SET Id_estado = 1 " + 
			"WHERE Id = ?;";
	
	public String insertarPrestamo = "" +
			"INSERT INTO Prestamos ( " + 
			"        Id_cuenta, " + 
			"        Id_estado, " + 
			"        Monto_solicitado, " + 
			"        Monto_cuota, " + 
			"        Cantidad_coutas " + 
			"    ) " + 
			"VALUES(?, 3, ?, ?, ?);";
	public String informe = "" +
			"SELECT COUNT(*) AS CantidadPrestamos, " + 
			"    SUM(p.Monto_solicitado) AS ImporteTotal, " + 
			"    SUM(p.Monto_cuota * p.Cantidad_coutas) - SUM(p.Monto_solicitado) AS Intereses " + 
			"FROM Prestamos p " + 
			"WHERE MONTH(p.Fecha_pedido) = ?;";
	
	public String getTipo = "SELECT Id, Descripcion, Eliminado FROM ";
	
	public String loginUsuario = "" +
			"SELECT Id, " + 
			"    Id_rol, " + 
			"    Nombre_usuario, " + 
			"    Email, " + 
			"    Pass, " + 
			"    Eliminado " + 
			"FROM Usuarios " + 
			"WHERE Email = ? " + 
			"    AND Pass = ? " + 
			"    AND Eliminado = 0;";
			
	public String insertarUsuario = "" +
			"INSERT INTO Usuarios (Id_rol, Nombre_usuario, Email, Pass) " + 
			"VALUES(?, ?, ?, ?);";
	
	public String buscarIdUsuario = "" +
			"SELECT Id " + 
			"FROM Usuarios " + 
			"WHERE Nombre_usuario = ?;";
	
	public String bajaUsuario = "" +
			"UPDATE Usuarios " + 
			"SET Eliminado = 1 " + 
			"WHERE Id = ?;";
	
	public String altaUsuario = "" +
			"UPDATE Usuarios " + 
			"SET Eliminado = 0 " + 
			"WHERE Id = ?;";
	
	public String cambioPassUsuario = "" +
			"UPDATE Usuarios " + 
			"SET Pass = ? " + 
			"WHERE Id = ?;";
	
	public String editarUsuario = "" +
			"UPDATE Usuarios " + 
			"SET Email = ? " + 
			"WHERE Id = ?;";
	
	
	public Queries() {}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
