package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.IPrestamoDao;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Prestamo;
import entidades.Tipo;
import queries.Queries;

public class PrestamoDaoImpl implements IPrestamoDao {
	Queries queryManager;
	private String getPrestamos;
	private String getPrestamosPendientes;
	private String getPrestamosAprobados;
	private String getPrestamosPendientesBuscar;
	private String rechazar;
	private String aceptar;
	private String insertarPrestamo;
	private String informe;

	public PrestamoDaoImpl() {
		this.queryManager = new Queries();
		this.getPrestamos = this.queryManager.getPrestamos;
		this.getPrestamosPendientes = this.queryManager.getPrestamosPendientes;
		this.getPrestamosAprobados = this.queryManager.getPrestamosAprobados;
		this.getPrestamosPendientesBuscar = this.queryManager.getPrestamosPendientesBuscar;
		this.rechazar = this.queryManager.rechazarPrestamo;
		this.aceptar = this.queryManager.aceptarPrestamo;
		this.insertarPrestamo = this.queryManager.insertarPrestamo;
		this.informe = this.queryManager.informe;
	}
	
	@Override
	public ArrayList<Prestamo> getPrestamos() {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getPrestamos);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setId(rSet.getInt("Id"));
				Cuenta cuenta = new Cuenta();
				cuenta.setId(rSet.getInt("IdCuenta"));
				cuenta.setNumeroCuenta(rSet.getString("numeroCuenta"));
				cuenta.setCbu(rSet.getString("CBUCuenta"));
				prestamo.setCuenta(cuenta);
				prestamo.setFechaPedido(rSet.getDate("FechaPedido"));
				Tipo estado = new Tipo();			
				estado.setId(rSet.getInt("IdEstado"));
				estado.setDescripcion(rSet.getString("DescripcionEstado"));
				prestamo.setEstado(estado);
				//p.Monto_solicitado AS montoSolicitado, p.Monto_cuota AS montoCuota, p.Cantidad_cuotas AS cantCoutas, p.Cuotas_pagas AS cuotasPagas
				prestamo.setMontoSolicitado(rSet.getBigDecimal("montoSolicitado"));
				prestamo.setMontoCuota(rSet.getBigDecimal("montoCuota"));
				prestamo.setCantidadCuotas(rSet.getInt("cantCoutas"));
				prestamos.add(prestamo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamos;
	}
	
	public ArrayList<Prestamo> getPrestamosPendientes() {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getPrestamosPendientes);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setId(rSet.getInt("Id"));
				Cuenta cuenta = new Cuenta();
				cuenta.setId(rSet.getInt("IdCuenta"));
				cuenta.setNumeroCuenta(rSet.getString("numeroCuenta"));
				cuenta.setCbu(rSet.getString("CBUCuenta"));
				prestamo.setCuenta(cuenta);
				prestamo.setFechaPedido(rSet.getDate("FechaPedido"));
				Tipo estado = new Tipo();			
				estado.setId(rSet.getInt("IdEstado"));
				estado.setDescripcion(rSet.getString("DescripcionEstado"));
				prestamo.setEstado(estado);
				prestamo.setMontoSolicitado(rSet.getBigDecimal("montoSolicitado"));
				prestamo.setMontoCuota(rSet.getBigDecimal("montoCuota"));
				prestamo.setCantidadCuotas(rSet.getInt("cantCoutas"));
				prestamos.add(prestamo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamos;
	}

	@Override
	public Boolean aprobar(Prestamo prestamo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.aceptar);
			statement.setInt(1, prestamo.getId());
			
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

	@Override
	public Boolean rechazar(Prestamo prestamo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.rechazar);
			statement.setInt(1, prestamo.getId());
			
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

	@Override
	public ArrayList<Prestamo> getPrestamosPendientes(String terminoBuscar) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		terminoBuscar = "%" + terminoBuscar + "%";
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getPrestamosPendientesBuscar);
			pStatement.setString(1, terminoBuscar);
			pStatement.setString(2, terminoBuscar);
			pStatement.setString(3, terminoBuscar);
			pStatement.setString(4, terminoBuscar);
			pStatement.setString(5, terminoBuscar);
			pStatement.setString(6, terminoBuscar);
			pStatement.setString(7, terminoBuscar);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setId(rSet.getInt("Id"));
				Cuenta cuenta = new Cuenta();
				cuenta.setId(rSet.getInt("IdCuenta"));
				cuenta.setNumeroCuenta(rSet.getString("numeroCuenta"));
				cuenta.setCbu(rSet.getString("CBUCuenta"));
				prestamo.setCuenta(cuenta);
				prestamo.setFechaPedido(rSet.getDate("FechaPedido"));
				Tipo estado = new Tipo();			
				estado.setId(rSet.getInt("IdEstado"));
				estado.setDescripcion(rSet.getString("DescripcionEstado"));
				prestamo.setEstado(estado);
				prestamo.setMontoSolicitado(rSet.getBigDecimal("montoSolicitado"));
				prestamo.setMontoCuota(rSet.getBigDecimal("montoCuota"));
				prestamo.setCantidadCuotas(rSet.getInt("cantCoutas"));
				prestamos.add(prestamo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamos;
	}

	@Override
	public String insertarPrestamo(Prestamo prestamo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		String isInsertExitoso = "0";

		try {
			System.out.println(prestamo.toString());
			statement = conexion.prepareStatement(this.insertarPrestamo);
			statement.setInt(1, prestamo.getCuenta().getId());
			statement.setBigDecimal(2, prestamo.getMontoSolicitado());
			statement.setBigDecimal(3, prestamo.getMontoCuota());
			statement.setInt(4, prestamo.getCantidadCuotas());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				conexion.commit();
				isInsertExitoso = "1";
				System.out.println(String.format("rows affected: %d", rowsAffected));
			} else {
				System.out.println("No rows affected. Insertion might have failed.");
			}
		} catch (SQLException e) {
			System.out.println("Excepcion capturada al insertar un prestamo en la DB");
			System.out.println(e.getMessage());
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}

	@Override
	public ArrayList<Prestamo> getPrestamosAprobados(Cliente cliente) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.queryManager.getImporteTotal);
			pStatement.setInt(1, cliente.getId());
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setId(rSet.getInt("Id"));
				Cuenta cuenta = new Cuenta();
				cuenta.setId(rSet.getInt("IdCuenta"));
				cuenta.setNumeroCuenta(rSet.getString("numeroCuenta"));
				cuenta.setCbu(rSet.getString("CBUCuenta"));
				prestamo.setCuenta(cuenta);
				prestamo.setFechaPedido(rSet.getDate("FechaPedido"));
				Tipo estado = new Tipo();			
				estado.setId(rSet.getInt("IdEstado"));
				estado.setDescripcion(rSet.getString("DescripcionEstado"));
				prestamo.setEstado(estado);
				prestamo.setMontoSolicitado(rSet.getBigDecimal("montoSolicitado"));
				prestamo.setMontoCuota(rSet.getBigDecimal("montoCuota"));
				prestamo.setCantidadCuotas(rSet.getInt("cantCoutas"));
				prestamos.add(prestamo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamos;
	}

	@Override
	public int[] informe(int mes) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		int[] info = new int[3];
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.informe);
			pStatement.setInt(1, mes);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				info[0] = rSet.getInt("CantidadPrestamos");
				info[1] = rSet.getInt("ImporteTotal");
				info[2] = rSet.getInt("Intereses");
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
	

}
