package queries;

public class Queries {
	
	public String queryBuscarId =   "SELECT Id FROM Clientes WHERE DNI = ?;";
	public String buscarIdUsuario = "SELECT c.Id FROM Clientes c WHERE c.Id_usuario = ?;";
	public String altaCuenta = 	 "UPDATE Cuentas SET Eliminado = 0 WHERE Id = ?;";
	public String bajaCuenta = 	 "UPDATE Cuentas SET Eliminado = 1 WHERE Id = ?;";
	public String crearCuenta =  "INSERT INTO Cuentas (Id_cliente, Nro_cuenta, CBU, Id_tipo_cuenta) VALUES(?, ?, ?, ?);";
	public String cuentaPorCbu = "SELECT c.Id AS Id," + 
								 "cl.Id AS IdCliente," + 
								 "c.Fecha_creacion AS FechaCreacion," + 
								 "c.Nro_cuenta AS numeroCuenta," + 
								 "c.CBU As CBU," + 
								 "c.Saldo AS Saldo," + 
								 "tc.Id As IdTipoCuenta," + 
								 "tc.Descripcion As DescripcionTipo," + 
								 "c.Eliminado AS Eliminado" + 
								 "FROM Cuentas c" + 
								 "INNER JOIN Clientes cl ON cl.Id = c.Id_cliente" + 
								 "INNER JOIN TipoCuenta tc ON tc.Id = c.Id_tipo_cuenta" + 
								 "WHERE CBU = ?;";
	public String editarCuenta = "UPDATE Cuentas SET Nro_cuenta = ?, CBU = ?, Saldo = ?, Id_tipo_cuenta = ? WHERE Id = ?;";
	public String getCuentasBuscar = "SELECT c.Id AS Id," + 
									 "cl.Id AS IdCliente," + 
									 "cl.Nombre AS Nombre," + 
									 "cl.Apellido AS Apellido," + 
									 "cl.DNI As DNI," + 
									 "cl.CUIL AS CUIL," + 
									 "c.Fecha_creacion AS FechaCreacion," + 
									 "c.Nro_cuenta AS numeroCuenta," + 
									 "c.CBU AS CBU," + 
									 "c.Saldo AS Saldo," + 
									 "tc.Id As IdTipoCuenta," + 
									 "tc.Descripcion AS nombreTipoCuenta," + 
									 "c.Eliminado AS Eliminado" + 
									 "FROM Cuentas c" + 
									 "INNER JOIN Clientes cl ON cl.Id = c.Id_cliente" + 
									 "INNER JOIN TipoCuenta tc ON tc.Id = c.Id_tipo_cuenta" + 
									 "WHERE c.Id LIKE ?" + 
									 "OR cl.Nombre LIKE ?" + 
									 "OR cl.Apellido LIKE ?" + 
									 "OR cl.DNI LIKE ?" + 
									 "OR c.Fecha_creacion LIKE ?" + 
									 "OR c.Nro_cuenta LIKE ?" + 
									 "OR c.CBU LIKE ?" + 
									 "OR c.Saldo LIKE ?" + 
									 "OR tc.Descripcion LIKE ?;";
	public String getCuentasClientes =  "SELECT c.Id AS Id," + 
										"cl.Id AS IdCliente," + 
										"cl.Nombre AS Nombre," + 
										"cl.Apellido AS Apellido," + 
										"cl.DNI As DNI," + 
										"cl.CUIL AS CUIL," + 
										"c.Fecha_creacion AS FechaCreacion," + 
										"c.Nro_cuenta AS numeroCuenta," + 
										"c.CBU AS CBU," + 
										"c.Saldo AS Saldo," + 
										"tc.Id As IdTipoCuenta," + 
										"tc.Descripcion AS nombreTipoCuenta," + 
										"c.Eliminado AS Eliminado" + 
										"FROM Cuentas c" + 
										"INNER JOIN Clientes cl ON cl.Id = c.Id_cliente" + 
										"INNER JOIN TipoCuenta tc ON tc.Id = c.Id_tipo_cuenta" + 
										"WHERE cl.Id = ?" + 
										"AND c.Eliminado = 0;";
	public String getCuentas =  "SELECT c.Id AS Id," + 
								"cl.Id AS IdCliente," + 
								"cl.Nombre AS Nombre," + 
								"cl.Apellido AS Apellido," + 
								"cl.DNI As DNI," + 
								"cl.CUIL AS CUIL," + 
								"c.Fecha_creacion AS FechaCreacion," + 
								"c.Nro_cuenta AS numeroCuenta," + 
								"c.CBU AS CBU," + 
								"c.Saldo AS Saldo," + 
								"tc.Id As IdTipoCuenta," + 
								"tc.Descripcion AS nombreTipoCuenta," + 
								"c.Eliminado AS Eliminado" + 
								"FROM Cuentas c" + 
								"INNER JOIN Clientes cl ON cl.Id = c.Id_cliente" + 
								"INNER JOIN TipoCuenta tc ON tc.Id = c.Id_tipo_cuenta";
	public String idPorIdCliente = "SELECT c.Id AS Id, cl.Id AS IdCliente FROM Cuentas AS cINNER JOIN Clientes AS cl ON cl.Id = c.Id_cliente WHERE cl.Id = ?;";
	public String restarSaldo = "UPDATE Cuentas SET Saldo = Saldo - ? WHERE Id = ?;";
	public String sumarSaldo = "UPDATE Cuentas SET Saldo = Saldo + ? WHERE Id = ?;";
	public String ultimoId = "SELECT MAX(c.Id) + 1 AS Id FROM Cuentas AS c;";	
	public String altaCuentaMovimiento = "INSERT INTO Movimientos (Id_cuenta, Id_tipo_movimiento, Id_concepto, Monto_movimiento)VALUES (?, ?, ?, ?);";
	public String getMovimientos =  "SELECT m.Id AS Id," + 
									"c.Id AS IdCuenta," + 
									"c.Saldo AS Saldo," + 
									"tm.Id AS IdTipoMovimiento," + 
									"tm.Descripcion AS DescripcionMovimiento," + 
									"con.Id AS IdConcepto," + 
									"con.Descripcion AS DescripcionConcepto," + 
									"m.Fecha_movimiento AS FechaMovimiento," + 
									"m.Monto_movimiento AS MontoMovimiento" + 
									"FROM Movimientos m" + 
									"INNER JOIN Cuentas c ON c.Id = m.Id_cuenta" + 
									"INNER JOIN TipoMovimiento tm ON tm.Id = m.Id_tipo_movimiento" + 
									"INNER JOIN Concepto con ON con.Id = m.Id_concepto" + 
									"WHERE c.Id = ?;";
	public String getMovimientosBuscar = "SELECT m.Id AS Id," + 
										 "c.Id AS IdCuenta," + 
										 "c.Saldo AS Saldo," + 
										 "tm.Id AS IdTipoMovimiento," + 
										 "tm.Descripcion AS DescripcionMovimiento," + 
										 "con.Id AS IdConcepto," + 
										 "con.Descripcion AS DescripcionConcepto," + 
										 "m.Fecha_movimiento AS FechaMovimiento," + 
										 "m.Monto_movimiento AS MontoMovimiento" + 
										 "FROM Movimientos m" + 
										 "INNER JOIN Cuentas c ON c.Id = m.Id_cuenta" + 
										 "INNER JOIN TipoMovimiento tm ON tm.Id = m.Id_tipo_movimiento" + 
										 "INNER JOIN Concepto con ON con.Id = m.Id_concepto" + 
										 "WHERE c.Id = ?" + 
										 "AND (" + 
										 "tm.Descripcion LIKE ?" + 
									  	 "OR con.Descripcion LIKE ?" + 
										 "OR m.Fecha_movimiento LIKE ?" + 
										 "OR m.Monto_movimiento LIKE ?" + 
										 ");";
	public String getImporteTotal = "SELECT SUM(m.Monto_movimiento) AS importeTotal FROM Movimientos AS m INNER JOIN TipoMovimiento tm ON tm.Id = m.Id_tipo_movimiento WHERE tm.Id = ? AND MONTH(m.Fecha_movimiento) = ?;";
	public String getCoutasPrestamo =   "SELECT c.Id AS Id," + 
										"p.Id AS IdPrestamo," + 
										"p.Monto_cuota AS MontoCouta," + 
										"c.Fecha_pago AS FechaPago," + 
										"c.Numero_cuota AS NumeroCuota," + 
										"c.Paga As Paga" + 
										"FROM Coutas c" + 
										"INNER JOIN Prestamos p ON p.Id = c.Id_prestamo" + 
										"WHERE c.Id_prestamo = ?" + 
										"AND c.Paga = 0" + 
										"AND DATE(c.Fecha_pago) <= DATE(NOW())";
	public String setCuotaPrestamo = "UPDATE Coutas SET Paga = 1 WHERE Id = ?;";
		
	public Queries() {}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
}
