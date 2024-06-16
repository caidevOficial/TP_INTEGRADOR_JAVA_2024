SELECT c.Id AS Id,
    cl.Id AS IdCliente,
    cl.Nombre AS Nombre,
    cl.Apellido AS Apellido,
    cl.DNI As DNI,
    cl.CUIL AS CUIL,
    c.Fecha_creacion AS FechaCreacion,
    c.Nro_cuenta AS numeroCuenta,
    c.CBU AS CBU,
    c.Saldo AS Saldo,
    tc.Id As IdTipoCuenta,
    tc.Descripcion AS nombreTipoCuenta,
    c.Eliminado AS Eliminado
FROM Cuentas c
    INNER JOIN Clientes cl ON cl.Id = c.Id_cliente
    INNER JOIN TipoCuenta tc ON tc.Id = c.Id_tipo_cuenta
WHERE c.Id LIKE ?
    OR cl.Nombre LIKE ?
    OR cl.Apellido LIKE ?
    OR cl.DNI LIKE ?
    OR c.Fecha_creacion LIKE ?
    OR c.Nro_cuenta LIKE ?
    OR c.CBU LIKE ?
    OR c.Saldo LIKE ?
    OR tc.Descripcion LIKE ?;