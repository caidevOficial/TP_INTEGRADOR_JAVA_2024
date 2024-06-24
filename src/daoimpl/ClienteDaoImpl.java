package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.IClienteDao;
import entidades.Cliente;
import entidades.Tipo;
import entidades.Usuario;
import exceptions.IngresoDuplicado;
import exceptions.TriggerCreacionExcedida;
import queries.Queries;

public class ClienteDaoImpl implements IClienteDao {
	Queries queryManager;
	private String buscarId;
	private String buscarIdUsuario;
	private String getClientes;
	private String getCliente;
	private String getClientesPorTermino;
	private String crearCliente;
	private String bajaCliente;
	private String altaCliente;
	private String editarCliente;
	
	/***
	 * 
	 */
	public ClienteDaoImpl() {
		this.queryManager = new Queries();
		this.buscarId = this.queryManager.queryBuscarId;
		this.buscarIdUsuario = this.queryManager.buscarIdCliente;
		this.getClientes = this.queryManager.getClientes;
		this.getCliente = this.queryManager.getCliente;
		this.getClientesPorTermino = this.queryManager.getClientesPorTermino;
		this.crearCliente = this.queryManager.crearCliente;
		this.bajaCliente = this.queryManager.bajaCliente;
		this.altaCliente = this.queryManager.altaCliente;
		this.editarCliente = this.queryManager.editarCliente;
	}

	/**
	 * The function "buscarId" takes an email as input and returns the corresponding ID from a database.
	 * 
	 * @param email The parameter "email" is a String that represents the email address of a user.
	 * @return The method is returning an integer value, which is the id corresponding to the given email.
	 */
	public int buscarId(String dni) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		int id = 0;
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.buscarId);
			pStatement.setString(1, dni);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				id = rSet.getInt("Id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public int buscarId(Usuario usuario) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		int id = 0;
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.buscarIdUsuario);
			pStatement.setInt(1, usuario.getId());
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				id = rSet.getInt("Id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * This function creates a new client in a database using the provided client object.
	 * 
	 * @param cliente The parameter "cliente" is an object of the class "Cliente" which contains various
	 * attributes such as "nombre" (name), "apellido" (last name), "dni" (identification number), "cuil"
	 * (tax identification number), "direccion" (address), "localidad" (
	 * @return The method is returning a Boolean value, indicating whether the creation of the client was
	 * successful or not.
	 * @throws IngresoDuplicado 
	 */
	public String crearCliente(Cliente cliente) throws TriggerCreacionExcedida, IngresoDuplicado {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		String isInsertExitoso = "0";
		try
		{
			statement = conexion.prepareStatement(this.crearCliente);
			statement.setString(1, cliente.getNombre());;
			statement.setString(2, cliente.getApellido());
			statement.setString(3, cliente.getDni());
			statement.setString(4, cliente.getCuil());
			statement.setString(5, cliente.getDireccion());
			statement.setInt(6, cliente.getLocalidad().getId());
			statement.setInt(7, cliente.getProvincia().getId());
			statement.setInt(8, cliente.getNacionalidad().getId());
			statement.setInt(9, cliente.getGenero().getId());
			statement.setDate(10, cliente.getFechaNacimiento());
			statement.setString(11, cliente.getTelefono());
			statement.setInt(12, cliente.getUsuario().getId());
			
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
			if(e.getMessage().contains("No se pueden agregar")) {
				if (e.getMessage().contains("cliente")) {
					TriggerCreacionExcedida triggerCreacionExcedida = new TriggerCreacionExcedida("cliente", false);
					throw triggerCreacionExcedida;
				}
			}
			if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("DNI")) {
				IngresoDuplicado ingresoDuplicado =  new IngresoDuplicado("dni");
				throw ingresoDuplicado;
			}
			if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("CUIL")) {
				IngresoDuplicado ingresoDuplicado =  new IngresoDuplicado("cuil");
				throw ingresoDuplicado;
			}
			e.getMessage();
		}
		
		return isInsertExitoso;
	}

	/**
	 * The above function retrieves a list of clients from a database.
	 * 
	 * @return The method is returning an ArrayList of Cliente objects.
	 */
	public ArrayList<Cliente> obtenerClientes() {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getClientes);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("Id"));
				cliente.setNombre(rSet.getString("Nombre"));
				cliente.setApellido(rSet.getString("Apellido"));
				cliente.setDni(rSet.getString("DNI"));
				cliente.setCuil(rSet.getString("CUIL"));
				cliente.setDireccion(rSet.getString("Direccion"));
				cliente.setEliminado(rSet.getBoolean("Eliminado"));
				Tipo localidad = new Tipo();
				localidad.setId(rSet.getInt("IdLocalidad"));
				localidad.setDescripcion(rSet.getString("nombreLocalidad"));
				localidad.setEliminado(rSet.getBoolean("eliminadoLocalidad"));
				cliente.setLocalidad(localidad);
				Tipo provincia = new Tipo();
				provincia.setId(rSet.getInt("IdProvincia"));
				provincia.setDescripcion(rSet.getString("nombreProvincia"));
				provincia.setEliminado(rSet.getBoolean("eliminadoProvincia"));
				cliente.setProvincia(provincia);
				Tipo nacionalidad = new Tipo();
				nacionalidad.setId(rSet.getInt("IdNacionalidad"));
				nacionalidad.setDescripcion(rSet.getString("nombreNacionalidad"));
				nacionalidad.setEliminado(rSet.getBoolean("eliminadoNacionalidad"));
				cliente.setNacionalidad(nacionalidad);
				Tipo genero = new Tipo();
				genero.setId(rSet.getInt("IdGenero"));
				genero.setDescripcion(rSet.getString("genero"));
				genero.setEliminado(rSet.getBoolean("eliminadoGenero"));
				cliente.setGenero(genero);
				cliente.setFechaNacimiento(rSet.getDate("FechaNacimiento"));
				cliente.setTelefono(rSet.getString("Telefono"));
				Usuario usuario = new Usuario();
				usuario.setId(rSet.getInt("IdUsuario"));
				usuario.setEmail(rSet.getString("Email"));
				usuario.setNombreUsuario(rSet.getString("nombreUsuario"));
				usuario.setTipoRol(new Tipo(rSet.getInt("IdRol"),rSet.getString("nombreRol")));
				cliente.setUsuario(usuario);
				clientes.add(cliente);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public Boolean bajaCliente(String dni) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.bajaCliente);
			statement.setString(1, dni);
			
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
	public Boolean altaCliente(String dni) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.altaCliente);
			statement.setString(1, dni);
			
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
	public Cliente obtenerCliente(Cliente clienteBuscar) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		Cliente cliente = new Cliente();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getCliente);
			pStatement.setInt(1, clienteBuscar.getId());
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				cliente.setId(rSet.getInt("Id"));
				cliente.setNombre(rSet.getString("Nombre"));
				cliente.setApellido(rSet.getString("Apellido"));
				cliente.setDni(rSet.getString("DNI"));
				cliente.setCuil(rSet.getString("CUIL"));
				cliente.setDireccion(rSet.getString("Direccion"));
				cliente.setEliminado(rSet.getBoolean("Eliminado"));
				Tipo localidad = new Tipo();
				localidad.setId(rSet.getInt("IdLocalidad"));
				localidad.setDescripcion(rSet.getString("nombreLocalidad"));
				localidad.setEliminado(rSet.getBoolean("eliminadoLocalidad"));
				cliente.setLocalidad(localidad);
				Tipo provincia = new Tipo();
				provincia.setId(rSet.getInt("IdProvincia"));
				provincia.setDescripcion(rSet.getString("nombreProvincia"));
				provincia.setEliminado(rSet.getBoolean("eliminadoProvincia"));
				cliente.setProvincia(provincia);
				Tipo nacionalidad = new Tipo();
				nacionalidad.setId(rSet.getInt("IdNacionalidad"));
				nacionalidad.setDescripcion(rSet.getString("nombreNacionalidad"));
				nacionalidad.setEliminado(rSet.getBoolean("eliminadoNacionalidad"));
				cliente.setNacionalidad(nacionalidad);
				Tipo genero = new Tipo();
				genero.setId(rSet.getInt("IdGenero"));
				genero.setDescripcion(rSet.getString("genero"));
				genero.setEliminado(rSet.getBoolean("eliminadoGenero"));
				cliente.setGenero(genero);
				cliente.setFechaNacimiento(rSet.getDate("FechaNacimiento"));
				cliente.setTelefono(rSet.getString("Telefono"));
				Usuario usuario = new Usuario();
				usuario.setId(rSet.getInt("IdUsuario"));
				usuario.setEmail(rSet.getString("Email"));
				usuario.setNombreUsuario(rSet.getString("nombreUsuario"));
				usuario.setTipoRol(new Tipo(rSet.getInt("IdRol"),rSet.getString("nombreRol")));
				cliente.setUsuario(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	public ArrayList<Cliente> obtenerClientes(String terminoBuscar) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		terminoBuscar = "%"+ terminoBuscar +"%";
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getClientesPorTermino);
			pStatement.setString(1, terminoBuscar);
			pStatement.setString(2, terminoBuscar);
			pStatement.setString(3, terminoBuscar);
			pStatement.setString(4, terminoBuscar);
			pStatement.setString(5, terminoBuscar);
			pStatement.setString(6, terminoBuscar);
			pStatement.setString(7, terminoBuscar);
			pStatement.setString(8, terminoBuscar);
			pStatement.setString(9, terminoBuscar);
			pStatement.setString(10, terminoBuscar);
			pStatement.setString(11, terminoBuscar);
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("Id"));
				cliente.setNombre(rSet.getString("Nombre"));
				cliente.setApellido(rSet.getString("Apellido"));
				cliente.setDni(rSet.getString("DNI"));
				cliente.setCuil(rSet.getString("CUIL"));
				cliente.setDireccion(rSet.getString("Direccion"));
				cliente.setEliminado(rSet.getBoolean("Eliminado"));
				Tipo localidad = new Tipo();
				localidad.setId(rSet.getInt("IdLocalidad"));
				localidad.setDescripcion(rSet.getString("nombreLocalidad"));
				localidad.setEliminado(rSet.getBoolean("eliminadoLocalidad"));
				cliente.setLocalidad(localidad);
				Tipo provincia = new Tipo();
				provincia.setId(rSet.getInt("IdProvincia"));
				provincia.setDescripcion(rSet.getString("nombreProvincia"));
				provincia.setEliminado(rSet.getBoolean("eliminadoProvincia"));
				cliente.setProvincia(provincia);
				Tipo nacionalidad = new Tipo();
				nacionalidad.setId(rSet.getInt("IdNacionalidad"));
				nacionalidad.setDescripcion(rSet.getString("nombreNacionalidad"));
				nacionalidad.setEliminado(rSet.getBoolean("eliminadoNacionalidad"));
				cliente.setNacionalidad(nacionalidad);
				Tipo genero = new Tipo();
				genero.setId(rSet.getInt("IdGenero"));
				genero.setDescripcion(rSet.getString("genero"));
				genero.setEliminado(rSet.getBoolean("eliminadoGenero"));
				cliente.setGenero(genero);
				cliente.setFechaNacimiento(rSet.getDate("FechaNacimiento"));
				cliente.setTelefono(rSet.getString("Telefono"));
				Usuario usuario = new Usuario();
				usuario.setId(rSet.getInt("IdUsuario"));
				usuario.setEmail(rSet.getString("Email"));
				usuario.setNombreUsuario(rSet.getString("nombreUsuario"));
				usuario.setTipoRol(new Tipo(rSet.getInt("IdRol"),rSet.getString("nombreRol")));
				cliente.setUsuario(usuario);
				clientes.add(cliente);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public String editarCliente(Cliente cliente) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		String isInsertExitoso = "0";
		try
		{
			statement = conexion.prepareStatement(this.editarCliente);
			statement.setString(1, cliente.getNombre());;
			statement.setString(2, cliente.getApellido());
			statement.setString(3, cliente.getDni());
			statement.setString(4, cliente.getCuil());
			statement.setString(5, cliente.getDireccion());
			statement.setInt(6, cliente.getLocalidad().getId());
			statement.setInt(7, cliente.getProvincia().getId());
			statement.setInt(8, cliente.getNacionalidad().getId());
			statement.setInt(9, cliente.getGenero().getId());
			statement.setDate(10, cliente.getFechaNacimiento());
			statement.setString(11, cliente.getTelefono());
			statement.setInt(12, cliente.getId());
			
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
		}
		
		return isInsertExitoso;
	}
	
}
