package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	
	private Conexion()
	{
		try
		{
			String host = "jdbc:mysql://localhost:3306/bdBanco";
			String user= "root";
			String password = "helloJava";
			this.connection = DriverManager.getConnection(host, user, password);
			this.connection.setAutoCommit(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * The function returns an instance of the Conexion class, creating a new instance if one does not
	 * already exist.
	 * 
	 * @return The method is returning an instance of the Conexion class.
	 */
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}
	/**
	 * The function returns the SQL connection.
	 * 
	 * @return The method is returning a Connection object.
	 */
	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	/**
	 * The function closes the connection to a database and sets the instance variable to null.
	 */
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		instancia = null;
	}
}
