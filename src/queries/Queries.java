package queries;

public class Queries {
	
	public String queryBuscarId = "SELECT Id FROM Clientes WHERE DNI = ?;";
	public String buscarIdUsuario = "SELECT c.Id FROM Clientes c WHERE c.Id_usuario = ?;";
	
	public String getClientes = "SELECT c.Id AS Id,\r\n" + 
			"    c.Nombre AS Nombre,\r\n" + 
			"    c.Apellido AS Apellido,\r\n" + 
			"    c.DNI AS DNI,\r\n" + 
			"    c.CUIL AS CUIL,\r\n" + 
			"    c.Direccion AS Direccion,\r\n" + 
			"    c.Eliminado AS Eliminado,\r\n" + 
			"    l.Id AS IdLocalidad,\r\n" + 
			"    l.Descripcion AS nombreLocalidad,\r\n" + 
			"    l.Eliminado AS eliminadoLocalidad,\r\n" + 
			"    p.Id AS IdProvincia,\r\n" + 
			"    p.Descripcion AS nombreProvincia,\r\n" + 
			"    p.Eliminado AS eliminadoProvincia,\r\n" + 
			"    n.Id AS IdNacionalidad,\r\n" + 
			"    n.Descripcion AS nombreNacionalidad,\r\n" + 
			"    l.Eliminado AS eliminadoNacionalidad,\r\n" + 
			"    g.Id AS IdGenero,\r\n" + 
			"    g.Descripcion AS genero,\r\n" + 
			"    g.Eliminado AS eliminadoGenero,\r\n" + 
			"    c.Fecha_nacimiento As FechaNacimiento,\r\n" + 
			"    c.Telefono As Telefono,\r\n" + 
			"    u.Id AS IdUsuario,\r\n" + 
			"    r.Id as IdRol,\r\n" + 
			"    r.Descripcion AS nombreRol,\r\n" + 
			"    u.Nombre_usuario AS nombreUsuario,\r\n" + 
			"    u.Email AS Email\r\n" + 
			"FROM Clientes c\r\n" + 
			"    INNER JOIN Localidades l ON l.Id = c.Id_Localidad\r\n" + 
			"    INNER JOIN Provincias p ON p.Id = c.Id_Provincia\r\n" + 
			"    INNER JOIN Nacionalidades n ON n.Id = c.Id_Nacionalidad\r\n" + 
			"    INNER JOIN Genero g ON g.Id = c.Id_Genero\r\n" + 
			"    INNER JOIN Usuarios u ON u.Id = c.Id_usuario\r\n" + 
			"    INNER JOIN Roles r On u.Id_rol = r.Id;";
	
	public String getCliente = "SELECT c.Id AS Id,\r\n" + 
			"    c.Nombre AS Nombre,\r\n" + 
			"    c.Apellido AS Apellido,\r\n" + 
			"    c.DNI AS DNI,\r\n" + 
			"    c.CUIL AS CUIL,\r\n" + 
			"    c.Direccion AS Direccion,\r\n" + 
			"    c.Eliminado AS Eliminado,\r\n" + 
			"    l.Id AS IdLocalidad,\r\n" + 
			"    l.Descripcion AS nombreLocalidad,\r\n" + 
			"    l.Eliminado AS eliminadoLocalidad,\r\n" + 
			"    p.Id AS IdProvincia,\r\n" + 
			"    p.Descripcion AS nombreProvincia,\r\n" + 
			"    p.Eliminado AS eliminadoProvincia,\r\n" + 
			"    n.Id AS IdNacionalidad,\r\n" + 
			"    n.Descripcion AS nombreNacionalidad,\r\n" + 
			"    l.Eliminado AS eliminadoNacionalidad,\r\n" + 
			"    g.Id AS IdGenero,\r\n" + 
			"    g.Descripcion AS genero,\r\n" + 
			"    g.Eliminado AS eliminadoGenero,\r\n" + 
			"    c.Fecha_nacimiento As FechaNacimiento,\r\n" + 
			"    c.Telefono As Telefono,\r\n" + 
			"    u.Id AS IdUsuario,\r\n" + 
			"    r.Id as IdRol,\r\n" + 
			"    r.Descripcion AS nombreRol,\r\n" + 
			"    u.Nombre_usuario AS nombreUsuario,\r\n" + 
			"    u.Email AS Email\r\n" + 
			"FROM Clientes c\r\n" + 
			"    INNER JOIN Localidades l ON l.Id = c.Id_Localidad\r\n" + 
			"    INNER JOIN Provincias p ON p.Id = c.Id_Provincia\r\n" + 
			"    INNER JOIN Nacionalidades n ON n.Id = c.Id_Nacionalidad\r\n" + 
			"    INNER JOIN Genero g ON g.Id = c.Id_Genero\r\n" + 
			"    INNER JOIN Usuarios u ON u.Id = c.Id_usuario\r\n" + 
			"    INNER JOIN Roles r On u.Id_rol = r.Id\r\n" + 
			"WHERE c.Id = ?;";
	
	public String getClientesPorTermino = "SELECT c.Id AS Id,\r\n" + 
			"    c.Nombre AS Nombre,\r\n" + 
			"    c.Apellido AS Apellido,\r\n" + 
			"    c.DNI AS DNI,\r\n" + 
			"    c.CUIL AS CUIL,\r\n" + 
			"    c.Direccion AS Direccion,\r\n" + 
			"    c.Eliminado AS Eliminado,\r\n" + 
			"    l.Id AS IdLocalidad,\r\n" + 
			"    l.Descripcion AS nombreLocalidad,\r\n" + 
			"    l.Eliminado AS eliminadoLocalidad,\r\n" + 
			"    p.Id AS IdProvincia,\r\n" + 
			"    p.Descripcion AS nombreProvincia,\r\n" + 
			"    p.Eliminado AS eliminadoProvincia,\r\n" + 
			"    n.Id AS IdNacionalidad,\r\n" + 
			"    n.Descripcion AS nombreNacionalidad,\r\n" + 
			"    l.Eliminado AS eliminadoNacionalidad,\r\n" + 
			"    g.Id AS IdGenero,\r\n" + 
			"    g.Descripcion AS genero,\r\n" + 
			"    g.Eliminado AS eliminadoGenero,\r\n" + 
			"    c.Fecha_nacimiento As FechaNacimiento,\r\n" + 
			"    c.Telefono As Telefono,\r\n" + 
			"    u.Id AS IdUsuario,\r\n" + 
			"    r.Id as IdRol,\r\n" + 
			"    r.Descripcion AS nombreRol,\r\n" + 
			"    u.Nombre_usuario AS nombreUsuario,\r\n" + 
			"    u.Email AS Email\r\n" + 
			"FROM Clientes c\r\n" + 
			"    INNER JOIN Localidades l ON l.Id = c.Id_Localidad\r\n" + 
			"    INNER JOIN Provincias p ON p.Id = c.Id_Provincia\r\n" + 
			"    INNER JOIN Nacionalidades n ON n.Id = c.Id_Nacionalidad\r\n" + 
			"    INNER JOIN Genero g ON g.Id = c.Id_Genero\r\n" + 
			"    INNER JOIN Usuarios u ON u.Id = c.Id_usuario\r\n" + 
			"    INNER JOIN Roles r On u.Id_rol = r.Id\r\n" + 
			"WHERE c.Id LIKE ?\r\n" + 
			"    OR c.DNI LIKE ?\r\n" + 
			"    OR c.Nombre LIKE ?\r\n" + 
			"    OR c.Apellido LIKE ?\r\n" + 
			"    OR c.Telefono LIKE ?\r\n" + 
			"    OR c.Direccion LIKE ?\r\n" + 
			"    OR l.Descripcion LIKE ?\r\n" + 
			"    OR p.Descripcion LIKE ?\r\n" + 
			"    OR n.Descripcion LIKE ?\r\n" + 
			"    OR g.Descripcion LIKE ?\r\n" + 
			"    OR c.Fecha_nacimiento LIKE ?;";
	
	public String crearCliente = "INSERT INTO Clientes (\r\n" + 
			"        Nombre,\r\n" + 
			"        Apellido,\r\n" + 
			"        DNI,\r\n" + 
			"        CUIL,\r\n" + 
			"        Direccion,\r\n" + 
			"        Id_Localidad,\r\n" + 
			"        Id_Provincia,\r\n" + 
			"        Id_Nacionalidad,\r\n" + 
			"        Id_Genero,\r\n" + 
			"        Fecha_nacimiento,\r\n" + 
			"        Telefono,\r\n" + 
			"        Id_usuario\r\n" + 
			"    )\r\n" + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	public String bajaCliente = "UPDATE Clientes  SET Eliminado = 1 WHERE DNI = ?;";
	
	public String altaCliente = "UPDATE Clientes  SET Eliminado = 0 WHERE DNI = ?;";
	
	public String editarCliente = "UPDATE Clientes\r\n" + 
			"SET Nombre = ?,\r\n" + 
			"    Apellido = ?,\r\n" + 
			"    DNI = ?,\r\n" + 
			"    CUIL = ?,\r\n" + 
			"    Direccion = ?,\r\n" + 
			"    Id_Localidad = ?,\r\n" + 
			"    Id_Provincia = ?,\r\n" + 
			"    Id_Nacionalidad = ?,\r\n" + 
			"    Id_Genero = ?,\r\n" + 
			"    Fecha_nacimiento = ?,\r\n" + 
			"    Telefono = ?\r\n" + 
			"WHERE Id = ?;";
	
	public Queries() {}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
