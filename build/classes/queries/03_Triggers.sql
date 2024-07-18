DELIMITER //
CREATE TRIGGER generar_cuotas_despues_insertar_prestamo
AFTER INSERT ON Prestamos
FOR EACH ROW
BEGIN
    DECLARE numero_cuota INT DEFAULT 1;
    DECLARE fecha_pago DATE;
    DECLARE total_cuotas INT;

    -- Obtener la cantidad de cuotas del préstamo
    SET total_cuotas = NEW.Cantidad_cuotas;

    -- Calcular la fecha de pago inicial (puedes ajustar esto según tus necesidades)
    SET fecha_pago = NEW.Fecha_pedido;

    -- Insertar las cuotas en la tabla Cuotas
    WHILE numero_cuota <= total_cuotas DO
        INSERT INTO Cuotas (Id_prestamo, Fecha_pago, Numero_cuota) VALUES (NEW.Id, fecha_pago, numero_cuota);

        -- Ajustar la fecha de pago para la siguiente cuota (puedes ajustar esto según tus necesidades)
        SET fecha_pago = fecha_pago + INTERVAL 1 MONTH;

        SET numero_cuota = numero_cuota + 1;
    END WHILE;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER Before_Insert_Cliente
BEFORE INSERT ON Clientes FOR EACH ROW
BEGIN
    DECLARE cliente_count INT;
    SELECT COUNT(*) INTO cliente_count
    FROM Clientes
    WHERE Id_usuario = NEW.Id_usuario;

    IF cliente_count >= 1 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se pueden agregar más de 1 cliente para el mismo usuario';
    END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER Before_Insert_Cuentas
BEFORE INSERT ON Cuentas FOR EACH ROW
BEGIN
    DECLARE cuenta_count INT;
    SELECT COUNT(*) INTO cuenta_count
    FROM Cuentas
    WHERE Id_cliente = NEW.Id_cliente;

    IF cuenta_count >= 3 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se pueden agregar más de 3 cuentas para el mismo cliente.';
    END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER incrementar_monto_cuota
BEFORE INSERT ON `Prestamos`
FOR EACH ROW
BEGIN
    SET NEW.monto_cuota = NEW.monto_cuota * 1.10;
END;
//
DELIMITER ;