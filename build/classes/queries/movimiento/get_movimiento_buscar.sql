SELECT m.Id AS Id,
    c.Id AS IdCuenta,
    c.Saldo AS Saldo,
    tm.Id AS IdTipoMovimiento,
    tm.Descripcion AS DescripcionMovimiento,
    con.Id AS IdConcepto,
    con.Descripcion AS DescripcionConcepto,
    m.Fecha_movimiento AS FechaMovimiento,
    m.Monto_movimiento AS MontoMovimiento
FROM Movimientos m
    INNER JOIN Cuentas c ON c.Id = m.Id_cuenta
    INNER JOIN TipoMovimiento tm ON tm.Id = m.Id_tipo_movimiento
    INNER JOIN Concepto con ON con.Id = m.Id_concepto
WHERE c.Id = ?
    AND (
        tm.Descripcion LIKE ?
        OR con.Descripcion LIKE ?
        OR m.Fecha_movimiento LIKE ?
        OR m.Monto_movimiento LIKE ?
    );