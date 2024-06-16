SELECT c.Id AS Id,
    cl.Id AS IdCliente
FROM Cuentas AS c
    INNER JOIN Clientes AS cl ON cl.Id = c.Id_cliente
WHERE cl.Id = ?;