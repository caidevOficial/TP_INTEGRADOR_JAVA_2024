package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ITipoDao;
import entidad.Tipo;

public class TipoDaoImpl implements ITipoDao {
	private static final String getTipo = "SELECT Id, Descripcion, Eliminado FROM ";

	public ArrayList<Tipo> getTipos(String tipoBuscar) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(getTipo + tipoBuscar);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Tipo tipo = new Tipo();
				tipo.setId(rSet.getInt("Id"));
				tipo.setDescripcion(rSet.getString("Descripcion"));
				tipo.setEliminado(rSet.getBoolean("Eliminado"));
				tipos.add(tipo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipos;
	}
}
