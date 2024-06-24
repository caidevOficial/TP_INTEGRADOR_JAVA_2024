package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.IUsuarioDao;
import entidades.Tipo;
import entidades.Usuario;
import exceptions.IngresoDuplicado;
import exceptions.IngresoLargo;
import queries.Queries;

public class UsuarioDaoImpl implements IUsuarioDao {
	Queries queryManager;
	private String login;
	
	private String insertar;
	private String buscarId;
	private String bajaUsuario;
	private String altaUsuario;
	private String cambioPassUsuario;
	private String editarUsuario;
	
	public UsuarioDaoImpl() {
		this.queryManager = new Queries();
		this.login = this.queryManager.loginUsuario;
		this.insertar = this.queryManager.insertarUsuario;
		this.buscarId = this.queryManager.buscarIdUsuario;
		this.bajaUsuario = this.queryManager.bajaUsuario;
		this.altaUsuario = this.queryManager.altaUsuario;
		this.cambioPassUsuario = this.queryManager.cambioPassUsuario;
		this.editarUsuario = this.queryManager.editarUsuario;
	}
		
	/**
	 * The login function takes a user object as input, queries the database to find a matching user, and
	 * returns the user object if found, otherwise returns null.
	 * 
	 * @param usuario The "usuario" parameter is an object of the "Usuario" class, which represents a user
	 * in the system. It contains the following attributes:
	 * @return The method is returning an object of type "Usuario" if the "Id" property of the "usuario"
	 * object is not equal to 0. Otherwise, it returns null.
	 */
	public Usuario login(Usuario usuario) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.login);
			pStatement.setString(1, usuario.getEmail());
			pStatement.setString(2, usuario.getPassword());
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				int id = rSet.getInt("Id");
				int idRol = rSet.getInt("Id_rol");
				String nombreUsuario = rSet.getString("Nombre_usuario");
				String mail = rSet.getString("Email");
				String password = rSet.getString("Pass");
				Boolean eliminado = rSet.getBoolean("Eliminado");
				usuario.setId(id);
				Tipo tipoRol = new Tipo();
				tipoRol.setId(idRol);
				usuario.setNombreUsuario(nombreUsuario);
				usuario.setTipoRol(tipoRol);
				usuario.setEmail(mail);
				usuario.setPassword(password);
				usuario.setEliminado(eliminado);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Se rompe acá");
		}
		if(usuario.getId() != 0) {
			return usuario;
		} else {
			return null;
		}
	}
	/**
	 * The function "crearUsuario" is used to insert a new user into a database table.
	 * 
	 * @param usuario The "usuario" parameter is an object of the class "Usuario" which contains the
	 * following attributes:
	 * @return The method is returning a Boolean value, indicating whether the user creation was
	 * successful or not.
	 * @throws IngresoDuplicado 
	 */
	public String crearUsuario(Usuario usuario) throws IngresoDuplicado, IngresoLargo {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		String isInsertExitoso = "";
		try
		{
			statement = conexion.prepareStatement(this.insertar);
			statement.setInt(1, usuario.getTipoRol().getId());;
			statement.setString(2, usuario.getNombreUsuario());
			statement.setString(3, usuario.getEmail());
			statement.setString(4, usuario.getPassword());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = "1";
				return isInsertExitoso;
			}
		} 
		catch (SQLException e) 
		{
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(e.getMessage().contains("Duplicate entry") && e.getMessage().contains("Email")) {
				IngresoDuplicado ingresoDuplicado = new IngresoDuplicado("email");
				throw ingresoDuplicado;
			}
			if(e.getMessage().contains("Duplicate entry") && e.getMessage().contains("Nombre_usuario")) {
				IngresoDuplicado ingresoDuplicado = new IngresoDuplicado("nombre usuario");
				throw ingresoDuplicado;
			}
			if(e.getMessage().contains("Data too long for column 'Pass'")) {
				IngresoLargo ingresoLargo = new IngresoLargo("contrase�a");
				throw ingresoLargo;
			}
			return e.getMessage();
		}
		
		return isInsertExitoso;
	}
	
	public int buscarId(String nombreUsuario) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		int id = 0;
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.buscarId);
			pStatement.setString(1, nombreUsuario);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				id = rSet.getInt("Id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public Boolean bajaUsuario(int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.bajaUsuario);
			statement.setInt(1, id);
			
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
	public Boolean altaUsuario(int Idcliente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.altaUsuario);
			statement.setInt(1, Idcliente);
			
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
	public Boolean cambiarPassUsuario(int Idcliente, String nuevaPass) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.cambioPassUsuario);
			statement.setString(1, nuevaPass);
			statement.setInt(2, Idcliente);
			
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
	public String editarUsuario(Usuario usuario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		String isInsertExitoso = "";
		try
		{
			statement = conexion.prepareStatement(this.editarUsuario);
			statement.setString(1, usuario.getEmail());
			statement.setInt(2, usuario.getId());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = "1";
				return isInsertExitoso;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}
}
