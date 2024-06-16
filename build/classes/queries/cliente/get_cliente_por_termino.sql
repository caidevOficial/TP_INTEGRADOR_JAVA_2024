SELECT c.Id AS Id,
    c.Nombre AS Nombre,
    c.Apellido AS Apellido,
    c.DNI AS DNI,
    c.CUIL AS CUIL,
    c.Direccion AS Direccion,
    c.Eliminado AS Eliminado,
    l.Id AS IdLocalidad,
    l.Descripcion AS nombreLocalidad,
    l.Eliminado AS eliminadoLocalidad,
    p.Id AS IdProvincia,
    p.Descripcion AS nombreProvincia,
    p.Eliminado AS eliminadoProvincia,
    n.Id AS IdNacionalidad,
    n.Descripcion AS nombreNacionalidad,
    l.Eliminado AS eliminadoNacionalidad,
    g.Id AS IdGenero,
    g.Descripcion AS genero,
    g.Eliminado AS eliminadoGenero,
    c.Fecha_nacimiento As FechaNacimiento,
    c.Telefono As Telefono,
    u.Id AS IdUsuario,
    r.Id as IdRol,
    r.Descripcion AS nombreRol,
    u.Nombre_usuario AS nombreUsuario,
    u.Email AS Email
FROM Clientes c
    INNER JOIN Localidades l ON l.Id = c.Id_Localidad
    INNER JOIN Provincias p ON p.Id = c.Id_Provincia
    INNER JOIN Nacionalidades n ON n.Id = c.Id_Nacionalidad
    INNER JOIN Genero g ON g.Id = c.Id_Genero
    INNER JOIN Usuarios u ON u.Id = c.Id_usuario
    INNER JOIN Roles r On u.Id_rol = r.Id
WHERE c.Id LIKE ?
    OR c.DNI LIKE ?
    OR c.Nombre LIKE ?
    OR c.Apellido LIKE ?
    OR c.Telefono LIKE ?
    OR c.Direccion LIKE ?
    OR l.Descripcion LIKE ?
    OR p.Descripcion LIKE ?
    OR n.Descripcion LIKE ?
    OR g.Descripcion LIKE ?
    OR c.Fecha_nacimiento LIKE ?;