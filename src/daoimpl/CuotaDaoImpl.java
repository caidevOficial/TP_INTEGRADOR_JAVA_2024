package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ICuotaDao;
import entidades.Cuota;
import entidades.Prestamo;
import queries.Queries;

public class CuotaDaoImpl implements ICuotaDao {
	
	private Queries queryManager;
	private String getCuotasPrestamo; 
	private String setCuotasPrestamo;
			
	public CuotaDaoImpl() {
		this.queryManager = new Queries();
		this.getCuotasPrestamo = this.queryManager.getGetCuotasPrestamo();
		this.setCuotasPrestamo = this.queryManager.getSetCuotaPrestamo();
	}

	@Override
	public ArrayList<Cuota> obtenerCuotas(Prestamo prestamo) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuota> cuotas = new ArrayList<Cuota>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getCuotasPrestamo);
			pStatement.setInt(1, prestamo.getId());
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Cuota cuota = new Cuota();
				cuota.setId(rSet.getInt("Id"));
				prestamo.setMontoCuota(rSet.getBigDecimal("MontoCuota"));
				cuota.setPrestamo(prestamo);
				cuota.setFecha_pago(rSet.getDate("FechaPago"));
				cuota.setNumeroCuota(rSet.getInt("NumeroCuota"));
				cuota.setPaga(rSet.getBoolean("Paga"));
				cuotas.add(cuota);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cuotas;
	}

	@Override
	public Boolean cuotaPaga(Cuota cuota) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.setCuotasPrestamo);
			statement.setInt(1, cuota.getId());
			
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
