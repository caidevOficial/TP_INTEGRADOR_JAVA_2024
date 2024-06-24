package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ICoutaDao;
import entidades.Couta;
import entidades.Prestamo;
import queries.Queries;

public class CoutaDaoImpl implements ICoutaDao {
	
	Queries queryManager;
	
	private String getCoutasPrestamo; 
	private String setCuotasPrestamo;
			
	public CoutaDaoImpl() {
		this.getCoutasPrestamo = this.queryManager.getCoutasPrestamo;
		this.setCuotasPrestamo = this.queryManager.setCuotaPrestamo;
	}

	@Override
	public ArrayList<Couta> obtenerCoutas(Prestamo prestamo) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Couta> coutas = new ArrayList<Couta>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getCoutasPrestamo);
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
			statement = conexion.prepareStatement(this.setCuotasPrestamo);
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
