SELECT SUM(m.Monto_movimiento) AS importeTotal
FROM Movimientos AS m
    INNER JOIN TipoMovimiento tm ON tm.Id = m.Id_tipo_movimiento
WHERE tm.Id = ?
    AND MONTH(m.Fecha_movimiento) = ?;