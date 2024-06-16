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
WHERE ep.Id = 3
    AND (
        p.Id LIKE ?
        OR c.Nro_cuenta LIKE ?
        OR c.CBU LIKE ?
        OR p.Fecha_pedido LIKE ?
        OR p.Monto_solicitado LIKE ?
        OR p.Monto_cuota LIKE ?
        OR p.Cantidad_coutas LIKE ?
    );