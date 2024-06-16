SELECT p.Id AS Id,
    c.Id AS IdCuenta,
    c.Nro_cuenta AS numeroCuenta,
    c.CBU AS CBUCuenta,
    p.Fecha_pedido AS FechaPedido,
    ep.Id AS IdEstado,
    ep.Descripcion AS DescripcionEstado,
    p.Monto_solicitado AS montoSolicitado,
    p.Monto_cuota AS montoCuota,
    p.Cantidad_coutas AS cantCoutas
FROM Prestamos p
    INNER JOIN Cuentas c ON c.Id = p.Id_cuenta
    INNER JOIN EstadoPrestamo ep ON ep.Id = p.Id_estado
WHERE ep.Id = 3;