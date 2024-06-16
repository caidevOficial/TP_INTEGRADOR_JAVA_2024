SELECT c.Id AS Id,
    p.Id AS IdPrestamo,
    p.Monto_cuota AS MontoCouta,
    c.Fecha_pago AS FechaPago,
    c.Numero_cuota AS NumeroCuota,
    c.Paga As Paga
FROM Coutas c
    INNER JOIN Prestamos p ON p.Id = c.Id_prestamo
WHERE c.Id_prestamo = ?
    AND c.Paga = 0
    AND DATE(c.Fecha_pago) <= DATE(NOW())