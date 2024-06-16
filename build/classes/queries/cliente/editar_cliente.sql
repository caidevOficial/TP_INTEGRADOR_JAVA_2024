UPDATE Clientes
SET Nombre = ?,
    Apellido = ?,
    DNI = ?,
    CUIL = ?,
    Direccion = ?,
    Id_Localidad = ?,
    Id_Provincia = ?,
    Id_Nacionalidad = ?,
    Id_Genero = ?,
    Fecha_nacimiento = ?,
    Telefono = ?
WHERE Id = ?;