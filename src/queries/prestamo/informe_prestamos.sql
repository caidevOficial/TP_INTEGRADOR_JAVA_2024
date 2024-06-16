SELECT COUNT(*) AS CantidadPrestamos,
    SUM(p.Monto_solicitado) AS ImporteTotal,
    SUM(p.Monto_cuota * p.Cantidad_coutas) - SUM(p.Monto_solicitado) as Intereses
FROM Prestamos p
WHERE MONTH(p.Fecha_pedido) = ?;