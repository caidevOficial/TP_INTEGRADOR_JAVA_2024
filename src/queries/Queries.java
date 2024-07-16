package queries;

public class Queries {
	
	private String queryBuscarId = "SELECT Id FROM Clientes WHERE DNI = ?;";
	private String buscarIdCliente = "SELECT c.Id FROM Clientes AS c WHERE c.Id_usuario = ?;";
	
	private String altaCuenta = "UPDATE Cuentas SET Eliminado = 0 WHERE Id = ?;";
	private String bajaCuenta = 	 "UPDATE Cuentas SET Eliminado = 1 WHERE Id = ?;";
	private String crearCuenta =  "INSERT INTO Cuentas (Id_cliente, Nro_cuenta, CBU, Id_tipo_cuenta) VALUES(?, ?, ?, ?);";
	private String cuentaPorCbu = "" +
			 "SELECT c.Id AS Id," + 
			 "	cl.Id AS IdCliente," + 
			 "	c.Fecha_creacion AS FechaCreacion," + 
			 "	c.Nro_cuenta AS numeroCuenta," + 
			 "	c.CBU As CBU," + 
			 "	c.Saldo AS Saldo," + 
			 "	tc.Id As IdTipoCuenta," + 
			 "	tc.Descripcion As DescripcionTipo," + 
			 "	c.Eliminado AS Eliminado " + 
			 "FROM Cuentas AS c " + 
			 "INNER JOIN Clientes AS cl ON cl.Id = c.Id_cliente " + 
			 "INNER JOIN TipoCuenta AS tc ON tc.Id = c.Id_tipo_cuenta " + 
			 "WHERE CBU = ?;";
	
	private String editarCuenta = "UPDATE Cuentas SET Nro_cuenta = ?, CBU = ?, Saldo = ?, Id_tipo_cuenta = ? WHERE Id = ?;";
	
	private String getCuentasBuscar = "" +
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
			 "INNER JOIN Clientes AS cl ON cl.Id = c.Id_cliente " + 
			 "INNER JOIN TipoCuenta AS tc ON tc.Id = c.Id_tipo_cuenta " + 
			 "WHERE c.Id LIKE ? " + 
			 "OR cl.Nombre LIKE ? " + 
			 "OR cl.Apellido LIKE ? " + 
			 "OR cl.DNI LIKE ? " + 
			 "OR c.Fecha_creacion LIKE ? " + 
			 "OR c.Nro_cuenta LIKE ? " + 
			 "OR c.CBU LIKE ? " + 
			 "OR c.Saldo LIKE ? " + 
			 "OR tc.Descripcion LIKE ?;";
	
	private String getBuscarCuenta = "" +
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
			 "INNER JOIN Clientes AS cl ON cl.Id = c.Id_cliente " + 
			 "INNER JOIN TipoCuenta AS tc ON tc.Id = c.Id_tipo_cuenta " + 
			 "WHERE c.Id = ? ";
	
	private String getCuentasClientes = "SELECT c.Id AS Id," + 
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
			"INNER JOIN Clientes AS cl ON cl.Id = c.Id_cliente " + 
			"INNER JOIN TipoCuenta AS tc ON tc.Id = c.Id_tipo_cuenta " + 
			"WHERE cl.Id = ? " + 
			"AND c.Eliminado = 0 " + 
			"ORDER BY cl.Apellido ASC, cl.Nombre ASC;";
	
	private String getCuentas =  "" +
			"SELECT c.Id AS Id, " + 
			"	cl.Id AS IdCliente, " + 
			"	cl.Apellido AS Apellido, " + 
			"	cl.Nombre AS Nombre, " + 
			"	cl.DNI As DNI, " + 
			"	cl.CUIL AS CUIL, " + 
			"	c.Fecha_creacion AS FechaCreacion, " + 
			"	c.Nro_cuenta AS numeroCuenta, " + 
			"	c.CBU AS CBU, " + 
			"	c.Saldo AS Saldo, " + 
			"	tc.Id As IdTipoCuenta, " + 
			"	tc.Descripcion AS nombreTipoCuenta, " + 
			"	c.Eliminado AS Eliminado " + 
			"FROM Cuentas AS c " + 
			"INNER JOIN Clientes AS cl ON cl.Id = c.Id_cliente " + 
			"INNER JOIN TipoCuenta AS tc ON tc.Id = c.Id_tipo_cuenta " +
			"ORDER BY " + 
			"Apellido ASC;";
	
	private String idPorIdCliente = "SELECT c.Id AS Id, cl.Id AS IdCliente FROM Cuentas AS c INNER JOIN Clientes AS cl ON cl.Id = c.Id_cliente WHERE cl.Id = ?;";
	private String restarSaldo = "UPDATE Cuentas SET Saldo = Saldo - ? WHERE Id = ?;";
	private String sumarSaldo = "UPDATE Cuentas SET Saldo = Saldo + ? WHERE Id = ?;";
	private String ultimoId = "SELECT MAX(c.Id) + 1 AS Id FROM Cuentas AS c;";	
	private String altaCuentaMovimiento = "INSERT INTO Movimientos (Id_cuenta, Id_tipo_movimiento, Id_concepto, Monto_movimiento) VALUES (?, ?, ?, ?);";
	
	private String getMovimientos =  "" +
			"SELECT m.Id AS Id," + 
			"	c.Id AS IdCuenta," + 
			"	c.Saldo AS Saldo," + 
			"	tm.Id AS IdTipoMovimiento," + 
			"	tm.Descripcion AS DescripcionMovimiento," + 
			"	con.Id AS IdConcepto," + 
			"	con.Descripcion AS DescripcionConcepto," + 
			"	m.Fecha_movimiento AS FechaMovimiento," + 
			"	m.Monto_movimiento AS MontoMovimiento " + 
			"FROM Movimientos AS m " + 
			"INNER JOIN Cuentas AS c ON c.Id = m.Id_cuenta " + 
			"INNER JOIN TipoMovimiento AS tm ON tm.Id = m.Id_tipo_movimiento " + 
			"INNER JOIN Concepto AS con ON con.Id = m.Id_concepto " + 
			"WHERE c.Id = ?;";
	
	private String getMovimientosBuscar = "" +
			"SELECT m.Id AS Id," + 
			 "	c.Id AS IdCuenta," + 
			 "	c.Saldo AS Saldo," + 
			 "	tm.Id AS IdTipoMovimiento," + 
			 "	tm.Descripcion AS DescripcionMovimiento," + 
			 "	con.Id AS IdConcepto," + 
			 "	con.Descripcion AS DescripcionConcepto," + 
			 "	m.Fecha_movimiento AS FechaMovimiento," + 
			 "	m.Monto_movimiento AS MontoMovimiento " + 
			 "FROM Movimientos AS m " + 
			 "INNER JOIN Cuentas AS c ON c.Id = m.Id_cuenta " + 
			 "INNER JOIN TipoMovimiento AS tm ON tm.Id = m.Id_tipo_movimiento " + 
			 "INNER JOIN Concepto AS con ON con.Id = m.Id_concepto " + 
			 "WHERE c.Id = ? " + 
			 "AND (" + 
			 "tm.Descripcion LIKE ? " + 
		  	 "OR con.Descripcion LIKE ? " + 
			 "OR m.Fecha_movimiento LIKE ? " + 
			 "OR m.Monto_movimiento LIKE ? " + 
			 ");";
	
	private String ultimoIdMovimientos = "SELECT MAX(id) AS ID FROM Transferencias;";
	
	private String getImporteTotal = "" +
			"SELECT " +
			"SUM(m.Monto_movimiento) AS importeTotal " +
			"FROM Movimientos AS m " +
			"INNER JOIN TipoMovimiento AS tm " +
			"	ON tm.Id = m.Id_tipo_movimiento " +
			"WHERE tm.Id = ? AND MONTH(m.Fecha_movimiento) = ?;";
	
	private String getCuotasPrestamo =   "" +
			"SELECT c.Id AS Id," + 
			"	p.Id AS IdPrestamo," + 
			"	p.Monto_cuota AS MontoCuota," + 
			"	c.Fecha_pago AS FechaPago," + 
			"	c.Numero_cuota AS NumeroCuota," + 
			"	c.Paga As Paga " + 
			"FROM Cuotas AS c " + 
			"INNER JOIN Prestamos AS p ON p.Id = c.Id_prestamo " + 
			"WHERE c.Id_prestamo = ? " + 
			"AND c.Paga = 0 " + 
			"AND DATE(c.Fecha_pago) <= DATE(NOW());";
	
	private String setCuotaPrestamo = "UPDATE Cuotas SET Paga = 1 WHERE Id = ?;";
	
	private String getClientes = "" +
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
			"FROM Clientes AS c " + 
			"    INNER JOIN Localidades AS l ON l.Id = c.Id_Localidad " + 
			"    INNER JOIN Provincias AS p ON p.Id = c.Id_Provincia " + 
			"    INNER JOIN Nacionalidades AS n ON n.Id = c.Id_Nacionalidad " + 
			"    INNER JOIN Genero AS g ON g.Id = c.Id_Genero " + 
			"    INNER JOIN Usuarios AS u ON u.Id = c.Id_usuario " + 
			"    INNER JOIN Roles AS r On u.Id_rol = r.Id;";
	
	private String getCliente = "" +
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
			"FROM Clientes AS c " + 
			"    INNER JOIN Localidades AS l ON l.Id = c.Id_Localidad " + 
			"    INNER JOIN Provincias AS p ON p.Id = c.Id_Provincia " + 
			"    INNER JOIN Nacionalidades AS n ON n.Id = c.Id_Nacionalidad " + 
			"    INNER JOIN Genero AS g ON g.Id = c.Id_Genero " + 
			"    INNER JOIN Usuarios AS u ON u.Id = c.Id_usuario " + 
			"    INNER JOIN Roles AS r On u.Id_rol = r.Id " + 
			"WHERE c.Id = ?;";
	
	private String getClientesPorTermino = "" +
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
			"FROM Clientes AS c " + 
			"    INNER JOIN Localidades AS l ON l.Id = c.Id_Localidad " + 
			"    INNER JOIN Provincias AS p ON p.Id = c.Id_Provincia " + 
			"    INNER JOIN Nacionalidades AS n ON n.Id = c.Id_Nacionalidad " + 
			"    INNER JOIN Genero AS g ON g.Id = c.Id_Genero " + 
			"    INNER JOIN Usuarios AS u ON u.Id = c.Id_usuario " + 
			"    INNER JOIN Roles AS r On u.Id_rol = r.Id " + 
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
	
	private String crearCliente = "" +
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
	
	private String bajaCliente = "UPDATE Clientes  SET Eliminado = 1 WHERE DNI = ?;";
	
	private String altaCliente = "UPDATE Clientes  SET Eliminado = 0 WHERE DNI = ?;";
	
	private String editarCliente = "UPDATE Clientes " + 
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
	
	private String getPrestamos = "" +
			"SELECT p.Id AS Id, " + 
			"    c.Id AS IdCuenta, " + 
			"    c.Nro_cuenta AS numeroCuenta, " + 
			"    c.CBU AS CBUCuenta, " + 
			"    p.Fecha_pedido AS FechaPedido, " + 
			"    ep.Id AS IdEstado, " + 
			"    ep.Descripcion AS DescripcionEstado, " + 
			"    p.Monto_solicitado AS montoSolicitado, " + 
			"    p.Monto_cuota AS montoCuota, " + 
			"    p.Cantidad_cuotas AS cantCuotas " + 
			"FROM Prestamos AS p " + 
			"    INNER JOIN Cuentas AS c ON c.Id = p.Id " + 
			"    INNER JOIN EstadoPrestamo AS ep ON ep.Id = p.Id_estado;";
	
	private String getPrestamosPendientes = "" +
			"SELECT p.Id AS Id, " + 
			"    c.Id AS IdCuenta, " + 
			"    c.Nro_cuenta AS numeroCuenta, " + 
			"    c.CBU AS CBUCuenta, " + 
			"    p.Fecha_pedido AS FechaPedido, " + 
			"    ep.Id AS IdEstado, " + 
			"    ep.Descripcion AS DescripcionEstado, " + 
			"    p.Monto_solicitado AS montoSolicitado, " + 
			"    p.Monto_cuota AS montoCuota, " + 
			"    p.Cantidad_cuotas AS cantCuotas " + 
			"FROM Prestamos AS p " + 
			"    INNER JOIN Cuentas AS c ON c.Id = p.Id_cuenta " + 
			"    INNER JOIN EstadoPrestamo AS ep ON ep.Id = p.Id_estado " + 
			"WHERE ep.Id = 3;";
	
	private String getPrestamosAprobados = "SELECT " + 
			"p.Id AS Id, " + 
			"c.Id AS IdCuenta, " + 
			"c.Nro_cuenta AS numeroCuenta, " + 
			"c.CBU AS CBUCuenta, " + 
			"p.Fecha_pedido AS FechaPedido, " + 
			"ep.Id AS IdEstado, " + 
			"ep.Descripcion AS DescripcionEstado, " + 
			"p.Monto_solicitado AS montoSolicitado, " + 
			"p.Monto_cuota AS montoCuota, " + 
			"p.Cantidad_cuotas AS cantCuotas " + 
			"FROM Prestamos AS p " + 
			"INNER JOIN Cuentas AS c ON c.Id = p.Id_cuenta " + 
			"INNER JOIN Clientes AS cl ON c.Id_cliente = cl.Id " + 
			"INNER JOIN EstadoPrestamo AS ep ON ep.Id = p.Id_estado " + 
			"WHERE ep.Id = 1 AND cl.Id = ?;";
	
	private String getPrestamosPendientesBuscar = "" +
			"SELECT p.Id AS Id, " + 
			"    c.Id AS IdCuenta, " + 
			"    c.Nro_cuenta AS numeroCuenta, " + 
			"    c.CBU AS CBUCuenta, " + 
			"    p.Fecha_pedido AS FechaPedido, " + 
			"    ep.Id AS IdEstado, " + 
			"    ep.Descripcion AS DescripcionEstado, " + 
			"    p.Monto_solicitado AS montoSolicitado, " + 
			"    p.Monto_cuota AS montoCuota, " + 
			"    p.Cantidad_cuotas AS cantCuotas " + 
			"FROM Prestamos AS p " + 
			"    INNER JOIN Cuentas AS c ON c.Id = p.Id_cuenta " + 
			"    INNER JOIN EstadoPrestamo AS ep ON ep.Id = p.Id_estado " + 
			"WHERE ep.Id = 3 " + 
			"    AND ( " + 
			"        p.Id LIKE ? " + 
			"        OR c.Nro_cuenta LIKE ? " + 
			"        OR c.CBU LIKE ? " + 
			"        OR p.Fecha_pedido LIKE ? " + 
			"        OR p.Monto_solicitado LIKE ? " + 
			"        OR p.Monto_cuota LIKE ? " + 
			"        OR p.Cantidad_cuotas LIKE ? " + 
			"    );";
	
	private String rechazarPrestamo = ""+
			"UPDATE Prestamos " + 
			"SET Id_estado = 2 " + 
			"WHERE Id = ?;";
	
	private String aceptarPrestamo = "" +
			"UPDATE Prestamos " + 
			"SET Id_estado = 1 " + 
			"WHERE Id = ?;";
	
	private String insertarPrestamo = "" +
			"INSERT INTO Prestamos ( " + 
			"        Id_cuenta, " + 
			"        Id_estado, " + 
			"        Monto_solicitado, " + 
			"        Monto_cuota, " + 
			"        Cantidad_cuotas " + 
			"    ) " + 
			"VALUES(?, 3, ?, ?, ?);";
	private String informe = "" +
			"SELECT COUNT(*) AS CantidadPrestamos, " + 
			"    SUM(p.Monto_solicitado) AS ImporteTotal, " + 
			"    SUM(p.Monto_cuota * p.Cantidad_cuotas) - SUM(p.Monto_solicitado) AS Intereses " + 
			"FROM Prestamos AS p " + 
			"WHERE MONTH(p.Fecha_pedido) = ?;";
	
	private String getTipo = "SELECT Id, Descripcion, Eliminado FROM ";
	
	private String loginUsuario = "" +
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
			
	private String insertarUsuario = "" +
			"INSERT INTO Usuarios (Id_rol, Nombre_usuario, Email, Pass) " + 
			"VALUES(?, ?, ?, ?);";
	
	private String buscarIdUsuario = "" +
			"SELECT Id " + 
			"FROM Usuarios " + 
			"WHERE Nombre_usuario = ?;";
	
	private String bajaUsuario = "" +
			"UPDATE Usuarios " + 
			"SET Eliminado = 1 " + 
			"WHERE Id = ?;";
	
	private String altaUsuario = "" +
			"UPDATE Usuarios " + 
			"SET Eliminado = 0 " + 
			"WHERE Id = ?;";
	
	private String cambioPassUsuario = "" +
			"UPDATE Usuarios " + 
			"SET Pass = ? " + 
			"WHERE Id = ?;";
	
	private String editarUsuario = "" +
			"UPDATE Usuarios " + 
			"SET Email = ? " + 
			"WHERE Id = ?;";
	
	private String altaTransferencia = "INSERT INTO Transferencias (Id_movimiento, id_cuenta_destino) VALUES (?, ?);";
	
	private String getTransferencias = "SELECT " + 
			"M.Id AS IDMovimiento, " + 
			"M.Id_cuenta AS CuentaOrigen, " + 
			"TM.Id AS IDMovimiento, " + 
			"TM.Descripcion AS DescripcionMovimiento, " + 
			"C.Id AS IDConcepto, " + 
			"C.Descripcion AS DescripcionConcepto, " + 
			"M.Fecha_movimiento AS Fecha, " + 
			"M.Monto_movimiento AS Monto, " + 
			"T.id_cuenta_destino AS CuentaDestino " + 
			"FROM Transferencias AS T " + 
			"INNER JOIN Movimientos AS M ON T.Id_movimiento = M.Id " + 
			"INNER JOIN Concepto AS C ON M.Id_concepto = C.Id " + 
			"INNER JOIN TipoMovimiento AS TM ON M.Id_tipo_movimiento = TM.Id " + 
			"WHERE M.Id_cuenta = ? " + 
			"ORDER BY M.Fecha_movimiento DESC;";
	
	private String getTransferenciasFecha = "SELECT " + 
			"M.Id AS IDMovimiento, " + 
			"M.Id_cuenta AS CuentaOrigen, " + 
			"TM.Id AS IDMovimiento, " + 
			"TM.Descripcion AS DescripcionMovimiento, " + 
			"C.Id AS IDConcepto, " + 
			"C.Descripcion AS DescripcionConcepto, " + 
			"M.Fecha_movimiento AS Fecha, " + 
			"M.Monto_movimiento AS Monto, " + 
			"T.id_cuenta_destino AS CuentaDestino " + 
			"FROM Transferencias AS T " + 
			"INNER JOIN Movimientos AS M ON T.Id_movimiento = M.Id " + 
			"INNER JOIN Concepto AS C ON M.Id_concepto = C.Id " + 
			"INNER JOIN TipoMovimiento AS TM ON M.Id_tipo_movimiento = TM.Id " + 
			"WHERE M.Id_cuenta = ? " +
			"YEAR(M.Fecha_movimiento) = YEAR(?) " + 
			"AND MONTH(M.Fecha_movimiento) = MONTH(?) " + 
			"AND DAY(M.Fecha_movimiento) = DAY(?) " + 
			"ORDER BY M.Fecha_movimiento DESC;";
	
	private String getTransferenciasFechas = "SELECT " + 
			"M.Id AS IDMovimiento, " + 
			"M.Id_cuenta AS CuentaOrigen, " + 
			"TM.Id AS IDMovimiento, " + 
			"TM.Descripcion AS DescripcionMovimiento, " + 
			"C.Id AS IDConcepto, " + 
			"C.Descripcion AS DescripcionConcepto, " + 
			"M.Fecha_movimiento AS Fecha, " + 
			"M.Monto_movimiento AS Monto, " + 
			"T.id_cuenta_destino AS CuentaDestino " + 
			"FROM Transferencias AS T " + 
			"INNER JOIN Movimientos AS M ON T.Id_movimiento = M.Id " + 
			"INNER JOIN Concepto AS C ON M.Id_concepto = C.Id " + 
			"INNER JOIN TipoMovimiento AS TM ON M.Id_tipo_movimiento = TM.Id " + 
			"WHERE M.Id_cuenta = ? " + 
			"AND YEAR(M.Fecha_movimiento) >= YEAR(?) " + 
			"AND YEAR(M.Fecha_movimiento) <= YEAR(?) " + 
			"AND MONTH(M.Fecha_movimiento) >= MONTH(?) " + 
			"AND MONTH(M.Fecha_movimiento) <= MONTH(?) " + 
			"AND DAY(M.Fecha_movimiento) >= DAY(?) " + 
			"AND DAY(M.Fecha_movimiento) <= DAY(?) " + 
			"ORDER BY M.Fecha_movimiento DESC;";
	
	public Queries() {}
	
	/**
	 * @return the queryBuscarId
	 */
	public String getQueryBuscarId() {
		return this.queryBuscarId;
	}

	/**
	 * @return the buscarIdCliente
	 */
	public String getBuscarIdCliente() {
		return this.buscarIdCliente;
	}

	/**
	 * @return the altaCuenta
	 */
	public String getAltaCuenta() {
		return this.altaCuenta;
	}

	/**
	 * @return the bajaCuenta
	 */
	public String getBajaCuenta() {
		return this.bajaCuenta;
	}

	/**
	 * @return the crearCuenta
	 */
	public String getCrearCuenta() {
		return this.crearCuenta;
	}

	/**
	 * @return the cuentaPorCbu
	 */
	public String getCuentaPorCbu() {
		return this.cuentaPorCbu;
	}

	/**
	 * @return the editarCuenta
	 */
	public String getEditarCuenta() {
		return this.editarCuenta;
	}

	/**
	 * @return the getCuentasBuscar
	 */
	public String getGetCuentasBuscar() {
		return this.getCuentasBuscar;
	}

	/**
	 * @return the getBuscarCuenta
	 */
	public String getGetBuscarCuenta() {
		return this.getBuscarCuenta;
	}

	/**
	 * @return the getCuentasClientes
	 */
	public String getGetCuentasClientes() {
		return this.getCuentasClientes;
	}

	/**
	 * @return the getCuentas
	 */
	public String getGetCuentas() {
		return this.getCuentas;
	}

	/**
	 * @return the idPorIdCliente
	 */
	public String getIdPorIdCliente() {
		return this.idPorIdCliente;
	}

	/**
	 * @return the restarSaldo
	 */
	public String getRestarSaldo() {
		return this.restarSaldo;
	}

	/**
	 * @return the sumarSaldo
	 */
	public String getSumarSaldo() {
		return this.sumarSaldo;
	}

	/**
	 * @return the ultimoId
	 */
	public String getUltimoId() {
		return this.ultimoId;
	}

	/**
	 * @return the altaCuentaMovimiento
	 */
	public String getAltaCuentaMovimiento() {
		return this.altaCuentaMovimiento;
	}

	/**
	 * @return the getMovimientos
	 */
	public String getGetMovimientos() {
		return this.getMovimientos;
	}

	/**
	 * @return the getMovimientosBuscar
	 */
	public String getGetMovimientosBuscar() {
		return this.getMovimientosBuscar;
	}

	/**
	 * @return the ultimoIdMovimientos
	 */
	public String getUltimoIdMovimientos() {
		return this.ultimoIdMovimientos;
	}

	/**
	 * @return the getImporteTotal
	 */
	public String getGetImporteTotal() {
		return this.getImporteTotal;
	}

	/**
	 * @return the getCuotasPrestamo
	 */
	public String getGetCuotasPrestamo() {
		return this.getCuotasPrestamo;
	}

	/**
	 * @return the setCuotaPrestamo
	 */
	public String getSetCuotaPrestamo() {
		return this.setCuotaPrestamo;
	}

	/**
	 * @return the getClientes
	 */
	public String getGetClientes() {
		return this.getClientes;
	}

	/**
	 * @return the getCliente
	 */
	public String getGetCliente() {
		return this.getCliente;
	}

	/**
	 * @return the getClientesPorTermino
	 */
	public String getGetClientesPorTermino() {
		return this.getClientesPorTermino;
	}

	/**
	 * @return the crearCliente
	 */
	public String getCrearCliente() {
		return this.crearCliente;
	}

	/**
	 * @return the bajaCliente
	 */
	public String getBajaCliente() {
		return this.bajaCliente;
	}

	/**
	 * @return the altaCliente
	 */
	public String getAltaCliente() {
		return this.altaCliente;
	}

	/**
	 * @return the editarCliente
	 */
	public String getEditarCliente() {
		return this.editarCliente;
	}

	/**
	 * @return the getPrestamos
	 */
	public String getGetPrestamos() {
		return this.getPrestamos;
	}

	/**
	 * @return the getPrestamosPendientes
	 */
	public String getGetPrestamosPendientes() {
		return this.getPrestamosPendientes;
	}

	/**
	 * @return the getPrestamosAprobados
	 */
	public String getGetPrestamosAprobados() {
		return this.getPrestamosAprobados;
	}

	/**
	 * @return the getPrestamosPendientesBuscar
	 */
	public String getGetPrestamosPendientesBuscar() {
		return this.getPrestamosPendientesBuscar;
	}

	/**
	 * @return the rechazarPrestamo
	 */
	public String getRechazarPrestamo() {
		return this.rechazarPrestamo;
	}

	/**
	 * @return the aceptarPrestamo
	 */
	public String getAceptarPrestamo() {
		return this.aceptarPrestamo;
	}

	/**
	 * @return the insertarPrestamo
	 */
	public String getInsertarPrestamo() {
		return this.insertarPrestamo;
	}

	/**
	 * @return the informe
	 */
	public String getInforme() {
		return this.informe;
	}

	/**
	 * @return the getTipo
	 */
	public String getGetTipo() {
		return this.getTipo;
	}

	/**
	 * @return the loginUsuario
	 */
	public String getLoginUsuario() {
		return this.loginUsuario;
	}

	/**
	 * @return the insertarUsuario
	 */
	public String getInsertarUsuario() {
		return this.insertarUsuario;
	}

	/**
	 * @return the buscarIdUsuario
	 */
	public String getBuscarIdUsuario() {
		return this.buscarIdUsuario;
	}

	/**
	 * @return the bajaUsuario
	 */
	public String getBajaUsuario() {
		return this.bajaUsuario;
	}

	/**
	 * @return the altaUsuario
	 */
	public String getAltaUsuario() {
		return this.altaUsuario;
	}

	/**
	 * @return the cambioPassUsuario
	 */
	public String getCambioPassUsuario() {
		return this.cambioPassUsuario;
	}

	/**
	 * @return the editarUsuario
	 */
	public String getEditarUsuario() {
		return this.editarUsuario;
	}

	/**
	 * @return the altaTransferencia
	 */
	public String getAltaTransferencia() {
		return this.altaTransferencia;
	}

	/**
	 * @return the getTransferencias
	 */
	public String getGetTransferencias() {
		return this.getTransferencias;
	}

	/**
	 * @return the getTransferenciasFecha
	 */
	public String getGetTransferenciasFecha() {
		return this.getTransferenciasFecha;
	}

	/**
	 * @return the getTransferenciasFechas
	 */
	public String getGetTransferenciasFechas() {
		return this.getTransferenciasFechas;
	}

}
