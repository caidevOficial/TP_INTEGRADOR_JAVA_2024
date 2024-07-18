package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.IMovimientoDao;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Tipo;
import queries.Queries;

public class MovimientoDaoImpl implements IMovimientoDao {
	
	private Queries queryManager;
	private String altaCuentaMovimientos;
	private String getMovimientos;
	private String getMovimientosBuscar; 
	private String getImporteTotal;
	private String getUltimoIdMovimiento;
	
	public MovimientoDaoImpl() {
		this.queryManager = new Queries();
		this.altaCuentaMovimientos = this.queryManager.getAltaCuentaMovimiento();
		this.getMovimientos = this.queryManager.getGetMovimientos();
		this.getMovimientosBuscar = this.queryManager.getGetMovimientosBuscar();
		this.getImporteTotal = this.queryManager.getGetImporteTotal();
		this.getUltimoIdMovimiento = this.queryManager.getUltimoIdMovimientos();
	}

	@Override
	public Boolean movimientoBanco(Movimiento movimiento) {
		System.out.println("Dentro de movimiento banco");
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.altaCuentaMovimientos);
			statement.setInt(1, movimiento.getCuenta().getId());
			statement.setInt(2, movimiento.getTipoMovimiento().getId());
			statement.setInt(3, movimiento.getConcepto().getId());
			statement.setBigDecimal(4, movimiento.getMonto());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				System.out.println("Commit movimiento");
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
			System.out.println(e.getMessage());
		}
		finally {
			Conexion.getConexion().cerrarConexion();
		}

		return isInsertExitoso;
	}
	
	public ArrayList<Movimiento> obtenerMovimientos(Cuenta cuenta) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getMovimientos);
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
		finally {
			Conexion.getConexion().cerrarConexion();
		}

		return movimientos;
	}

	@Override
	public ArrayList<Movimiento> obtenerMovimientos(Cuenta cuenta, String terminoBuscar) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		terminoBuscar = "%" + terminoBuscar + "%";
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getMovimientosBuscar);
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
		finally {
			Conexion.getConexion().cerrarConexion();
		}

		return movimientos;
	}

	@Override
	public int importTotal(Tipo tipoMovimiento, int Mes) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		int importeTotal = 0;
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getImporteTotal);
			pStatement.setInt(1, tipoMovimiento.getId());
			pStatement.setInt(2, Mes);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				importeTotal = rSet.getInt("importeTotal");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Conexion.getConexion().cerrarConexion();
		}

		return importeTotal;
	}

	@Override
	public int ultimoIdMovimiento() {
		Connection connection = Conexion.getConexion().getSQLConexion();
		int id = 0;
		
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getUltimoIdMovimiento);
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				id = rSet.getInt("ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Conexion.getConexion().cerrarConexion();
		}

		return id;
	}
}
