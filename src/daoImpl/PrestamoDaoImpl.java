package daoImpl;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.IPrestamoDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import entidad.Tipo;

public class PrestamoDaoImpl implements IPrestamoDao {
	private String getPrestamos = this.abrirQuery("./queries/prestamo/get_prestamo.sql");
	private String getPrestamosPendientes = this.abrirQuery("./queries/prestamo/get_prestamos_pendientes.sql");
	private String getPrestamosAprobados = this.abrirQuery("./queries/prestamo/get_prestamos_aprobados.sql");
	private String getPrestamosPendientesBuscar = this.abrirQuery("./queries/prestamo/_buscar.sql");
	private String rechazar = this.abrirQuery("./queries/prestamo/rechazar_prestamo.sql");
	private String aceptar = this.abrirQuery("./queries/prestamo/aceptar_prestamo.sql");
	private String insertarPrestamo = this.abrirQuery("./queries/prestamo/insertar_prestamo.sql");
	private String informe = this.abrirQuery("./queries/prestamo/informe_prestamos.sql");

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
	public ArrayList<Prestamo> getPrestamos() {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(getPrestamos);
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
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/prestamo/get_prestamos_pendientes.sql"));
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
			statement = conexion.prepareStatement(aceptar);
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
			statement = conexion.prepareStatement(rechazar);
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
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/prestamo/_buscar.sql"));
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
		try
		{
			statement = conexion.prepareStatement(insertarPrestamo);
			statement.setInt(1, prestamo.getCuenta().getId());
			statement.setBigDecimal(2, prestamo.getMontoSolicitado());
			statement.setBigDecimal(3, prestamo.getMontoCuota());
			statement.setInt(4, prestamo.getCantidadCuotas());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = "1";
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
	public ArrayList<Prestamo> getPrestamosAprobados(Cliente cliente) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/movimiento/get_importe_total.sql"));
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
			PreparedStatement pStatement =  connection.prepareStatement(informe);
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
