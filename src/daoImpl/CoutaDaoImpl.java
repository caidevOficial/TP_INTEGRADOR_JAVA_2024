package daoImpl;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ICoutaDao;
import entidad.Couta;
import entidad.Prestamo;

public class CoutaDaoImpl implements ICoutaDao {
	private String getCoutasPrestamo = this.abrirQuery("./queries/cuota/get_cuotas_prestamo.sql");
	//private String this.abrirQuery("./queries/cuota/set_cuota.sql") = this.abrirQuery("./queries/cuota/set_cuota.sql");
	
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
	public ArrayList<Couta> obtenerCoutas(Prestamo prestamo) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Couta> coutas = new ArrayList<Couta>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/cuota/get_cuotas_prestamo.sql"));
			pStatement.setInt(1, prestamo.getId());
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Couta couta = new Couta();
				couta.setId(rSet.getInt("Id"));
				prestamo.setMontoCuota(rSet.getBigDecimal("MontoCouta"));
				couta.setPrestamo(prestamo);
				couta.setFecha_pago(rSet.getDate("FechaPago"));
				couta.setNumeroCouta(rSet.getInt("NumeroCuota"));
				couta.setPaga(rSet.getBoolean("Paga"));
				coutas.add(couta);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coutas;
	}

	@Override
	public Boolean coutaPaga(Couta couta) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.abrirQuery("./queries/cuota/set_cuota.sql"));
			statement.setInt(1, couta.getId());
			
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
}
