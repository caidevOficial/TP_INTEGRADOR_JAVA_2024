package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import queries.Queries;

import dao.ITipoDao;
import entidades.Tipo;

public class TipoDaoImpl implements ITipoDao {
	private Queries queryManager;
	private String getTipo;

	public TipoDaoImpl() {
		this.queryManager = new Queries();
		this.getTipo = this.queryManager.getGetTipo();
	}
	
	public ArrayList<Tipo> getTipos(String tipoBuscar) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(String.format("%s %s", this.getTipo, tipoBuscar));
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
		finally {
			Conexion.getConexion().cerrarConexion();
		}

		return tipos;
	}
}
