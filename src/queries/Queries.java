package queries;

public class Queries {
	
	public String queryBuscarId = "SELECT Id FROM Clientes WHERE DNI = ?;";
	public String buscarIdCliente = "SELECT c.Id FROM Clientes AS c WHERE c.Id_usuario = ?;";
	
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
