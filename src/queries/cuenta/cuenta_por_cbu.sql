SELECT c.Id AS Id,
    cl.Id AS IdCliente,
    c.Fecha_creacion AS FechaCreacion,
    c.Nro_cuenta AS numeroCuenta,
    c.CBU As CBU,
    c.Saldo AS Saldo,
    tc.Id As IdTipoCuenta,
    tc.Descripcion As DescripcionTipo,
    c.Eliminado AS Eliminado
FROM Cuentas c
    INNER JOIN Clientes cl ON cl.Id = c.Id_cliente
    INNER JOIN TipoCuenta tc ON tc.Id = c.Id_tipo_cuenta
WHERE CBU = ?;