package daoImpl;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.IMovimientoDao;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Tipo;

public class MovimientoDaoImpl implements IMovimientoDao {
	private String altaCuenta = this.abrirQuery("./queries/movimiento/alta_movimiento.sql");
	private String getMovimientos = this.abrirQuery("./queries/movimiento/get_movimiento.sql");
	private String getMovimientosBuscar = this.abrirQuery("./queries/movimiento/get_movimiento_buscar.sql");
	private String getImporteTotal = this.abrirQuery("./queries/movimiento/get_importe_total.sql");
	
	/**
	 * The function "abrirQuery" reads the contents of a file given its path and returns the content as a
	 * string.
	 * 
	 * @param ruta The parameter "ruta" is a String that represents the file path of the query file that
	 * needs to be opened and read.
	 * @return The method is returning a String.
	 */
	public String abrirQuery(String ruta) {
		String result = "";
		try {
			File archivo = new File(ruta);
			FileReader reader = new FileReader(archivo);
			char[] buffer = new char[(int) archivo.length()];
			reader.read(buffer);
			reader.close();
			result = new String(buffer);
			return result;
		} catch (Exception e) {
			System.out.println("Exeption opening the file");
			return result;
		}
	}

	@Override
	public Boolean movimientoBanco(Movimiento movimiento) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.abrirQuery("./queries/movimiento/alta_movimiento.sql"));
			statement.setInt(1, movimiento.getCuenta().getId());
			statement.setInt(2, movimiento.getTipoMovimiento().getId());
			statement.setInt(3, movimiento.getConcepto().getId());
			statement.setBigDecimal(4, movimiento.getMonto());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.getMessage();
		}
		
		return isInsertExitoso;
	}
	
	public ArrayList<Movimiento> obtenerMovimientos(Cuenta cuenta) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/movimiento/get_movimiento.sql"));
			pStatement.setInt(1, cuenta.getId());
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				// m.Id, c.Id AS , tm.Id AS , tm.Descripcion AS , con.Id AS , con.Descripcion AS , m.Fecha_movimiento AS , m.Monto_movimiento AS 
				Movimiento movimiento = new Movimiento();
				movimiento.setId(rSet.getInt("Id"));
				cuenta.setId(rSet.getInt("IdCuenta"));
				cuenta.setSaldo(rSet.getBigDecimal("Saldo"));
				movimiento.setCuenta(cuenta);
				movimiento.setTipoMovimiento(new Tipo(rSet.getInt("IdTipoMovimiento"), rSet.getString("DescripcionMovimiento")));
				movimiento.setConcepto(new Tipo(rSet.getInt("IdConcepto"), rSet.getString("DescripcionConcepto")));
				movimiento.setFechaMovimiento(rSet.getDate("FechaMovimiento"));
				movimiento.setMonto(rSet.getBigDecimal("MontoMovimiento"));
				movimientos.add(movimiento);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movimientos;
	}

	@Override
	public ArrayList<Movimiento> obtenerMovimientos(Cuenta cuenta, String terminoBuscar) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		terminoBuscar = "%" + terminoBuscar + "%";
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/movimiento/get_movimiento_buscar.sql"));
			pStatement.setInt(1, cuenta.getId());
			pStatement.setString(2, terminoBuscar);
			pStatement.setString(3, terminoBuscar);
			pStatement.setString(4, terminoBuscar);
			pStatement.setString(5, terminoBuscar);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				// m.Id, c.Id AS , tm.Id AS , tm.Descripcion AS , con.Id AS , con.Descripcion AS , m.Fecha_movimiento AS , m.Monto_movimiento AS 
				Movimiento movimiento = new Movimiento();
				movimiento.setId(rSet.getInt("Id"));
				cuenta.setId(rSet.getInt("IdCuenta"));
				cuenta.setSaldo(rSet.getBigDecimal("Saldo"));
				movimiento.setCuenta(cuenta);
				movimiento.setTipoMovimiento(new Tipo(rSet.getInt("IdTipoMovimiento"), rSet.getString("DescripcionMovimiento")));
				movimiento.setConcepto(new Tipo(rSet.getInt("IdConcepto"), rSet.getString("DescripcionConcepto")));
				movimiento.setFechaMovimiento(rSet.getDate("FechaMovimiento"));
				movimiento.setMonto(rSet.getBigDecimal("MontoMovimiento"));
				movimientos.add(movimiento);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movimientos;
	}

	@Override
	public int importTotal(Tipo tipoMovimiento, int Mes) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		int importeTotal = 0;
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/movimiento/get_importe_total.sql"));
			pStatement.setInt(1, tipoMovimiento.getId());
			pStatement.setInt(2, Mes);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				importeTotal = rSet.getInt("importeTotal");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return importeTotal;
	}

}
