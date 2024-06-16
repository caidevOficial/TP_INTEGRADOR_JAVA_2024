SELECT Id,
    Id_rol,
    Nombre_usuario,
    Email,
    Pass,
    Eliminado
FROM Usuarios
WHERE Email = ?
    AND Pass = ?
    AND Eliminado = 0;