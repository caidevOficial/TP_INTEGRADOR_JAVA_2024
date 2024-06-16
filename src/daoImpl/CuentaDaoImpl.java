package daoImpl;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.ICuentaDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Tipo;
import exceptions.IngresoDuplicado;
import exceptions.NoExiste;
import exceptions.SaldoNegativo;
import exceptions.TriggerCreacionExcedida;

public class CuentaDaoImpl implements ICuentaDao {
	private String getCuentas = this.abrirQuery("./queries/cuenta/get_cuentas.sql");
	private String getCuentasBuscar = this.abrirQuery("./queries/cuenta/get_cuentas_buscar.sql");
	private String getCuentasCliente = this.abrirQuery("./queries/cuenta/get_cuentas_cliente.sql");
	private String crearCuenta = this.abrirQuery("./queries/cuenta/crear_cuenta.sql");
	private String bajaCuenta = this.abrirQuery("./queries/cuenta/baja_cuenta.sql");
	private String altaCuenta = this.abrirQuery("./queries/cuenta/alta_cuenta.sql");
	private String actualizarSaldoSumar = this.abrirQuery("./queries/cuenta/sumar_saldo.sql");
	private String actualizarSaldoRestar = this.abrirQuery("./queries/cuenta/restar_saldo.sql");
	private String ultimoId = this.abrirQuery("./queries/cuenta/ultimo_id.sql");
	private String cuentaPorCBU = this.abrirQuery("./queries/cuenta/cuenta_por_cbu.sql");
	private String idPorIdCliente = this.abrirQuery("./queries/cuenta/id_por_idcliente.sql");
	private String EditarCuenta = this.abrirQuery("./queries/cuenta/editar_cuenta.sql");
	
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

	public ArrayList<Cuenta> obtenerCuentas() {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/cuenta/get_cuentas.sql"));
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setId(rSet.getInt("Id"));
				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("IdCliente"));
				cliente.setNombre(rSet.getString("Nombre"));
				cliente.setApellido(rSet.getString("Apellido"));
				cliente.setDni(rSet.getString("DNI"));
				cliente.setCuil(rSet.getString("CUIL"));
				cuenta.setCliente(cliente);
				cuenta.setFechaCreacion(rSet.getDate("FechaCreacion"));
				cuenta.setNumeroCuenta(rSet.getString("numeroCuenta"));
				cuenta.setCbu(rSet.getString("CBU"));
				cuenta.setSaldo(rSet.getBigDecimal("Saldo"));
				Tipo tipo = new Tipo();
				tipo.setId(rSet.getInt("IdTipoCuenta"));
				tipo.setDescripcion(rSet.getString("nombreTipoCuenta"));
				cuenta.setTipoCuenta(tipo);
				cuenta.setEliminado(rSet.getBoolean("Eliminado"));
				cuentas.add(cuenta);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cuentas;
	}

	@Override
	public String crearCuenta(Cuenta cuenta) throws TriggerCreacionExcedida, IngresoDuplicado, NoExiste {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		String isInsertExitoso = "0";
		try
		{
			statement = conexion.prepareStatement(this.abrirQuery("./queries/cuenta/crear_cuenta.sql"));
			statement.setInt(1, cuenta.getCliente().getId());;
			statement.setString(2, cuenta.getNumeroCuenta());
			statement.setString(3, cuenta.getCbu());
			statement.setInt(4, cuenta.getTipoCuenta().getId());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = "1";
			}
			if (isInsertExitoso == "0") {
				NoExiste noExiste = new NoExiste();
				throw noExiste;
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
				if (e.getMessage().contains("cuentas")) {
					TriggerCreacionExcedida triggerCreacionExcedida = new TriggerCreacionExcedida(true);
					throw triggerCreacionExcedida;
				}
			}
			if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("Nro_cuenta")) {
				IngresoDuplicado ingresoDuplicado =  new IngresoDuplicado("Numero Cuenta");
				throw ingresoDuplicado;
			}
			if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("CBU")) {
				IngresoDuplicado ingresoDuplicado =  new IngresoDuplicado("CBU");
				throw ingresoDuplicado;
			}
			if (isInsertExitoso == "0") {
				NoExiste noExiste = new NoExiste();
				throw noExiste;
			}
			e.getMessage();
		}
		
		return isInsertExitoso;
	}

	@Override
	public Boolean bajaCuenta(int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.abrirQuery("./queries/cuenta/baja_cuenta.sql"));
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
	public Boolean altaCuenta(int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.abrirQuery("./queries/cuenta/alta_cuenta.sql"));
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
	public int ultimoId() {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		int id = 0;
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/cuenta/ultimo_id.sql"));
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
	public Boolean actualizarSaldoSumar(Cuenta cuenta, BigDecimal nuevosaldo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(this.abrirQuery("./queries/cuenta/sumar_saldo.sql"));
			statement.setBigDecimal(1, nuevosaldo);
			statement.setInt(2, cuenta.getId());
			
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
	public String actualizarSaldoRestar(Cuenta cuenta, BigDecimal nuevosaldo) throws SaldoNegativo {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		String isInsertExitoso = "0";
		try
		{
			statement = conexion.prepareStatement(this.abrirQuery("./queries/cuenta/restar_saldo.sql"));
			statement.setBigDecimal(1, nuevosaldo);
			statement.setInt(2, cuenta.getId());
			
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
			if(e.getMessage().contains("Check constraint")) {
				SaldoNegativo saldoNegativo = new SaldoNegativo();
				throw saldoNegativo;
			}
			e.getMessage();
		}
		return isInsertExitoso;
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentas(Cliente clienteCuenta) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/cuenta/get_cuentas_cliente.sql"));
			pStatement.setInt(1, clienteCuenta.getId());
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setId(rSet.getInt("Id"));
				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("IdCliente"));
				cliente.setNombre(rSet.getString("Nombre"));
				cliente.setApellido(rSet.getString("Apellido"));
				cliente.setDni(rSet.getString("DNI"));
				cliente.setCuil(rSet.getString("CUIL"));
				cuenta.setCliente(cliente);
				cuenta.setFechaCreacion(rSet.getDate("FechaCreacion"));
				cuenta.setNumeroCuenta(rSet.getString("numeroCuenta"));
				cuenta.setCbu(rSet.getString("CBU"));
				cuenta.setSaldo(rSet.getBigDecimal("Saldo"));
				Tipo tipo = new Tipo();
				tipo.setId(rSet.getInt("IdTipoCuenta"));
				tipo.setDescripcion(rSet.getString("nombreTipoCuenta"));
				cuenta.setTipoCuenta(tipo);
				cuenta.setEliminado(rSet.getBoolean("Eliminado"));
				cuentas.add(cuenta);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cuentas;
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentas(String terminoBuscar) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		terminoBuscar = "%" + terminoBuscar + "%";
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/cuenta/get_cuentas_buscar.sql"));
			pStatement.setString(1, terminoBuscar);
			pStatement.setString(2, terminoBuscar);
			pStatement.setString(3, terminoBuscar);
			pStatement.setString(4, terminoBuscar);
			pStatement.setString(5, terminoBuscar);
			pStatement.setString(6, terminoBuscar);
			pStatement.setString(7, terminoBuscar);
			pStatement.setString(8, terminoBuscar);
			pStatement.setString(9, terminoBuscar);
			
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setId(rSet.getInt("Id"));
				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("IdCliente"));
				cliente.setNombre(rSet.getString("Nombre"));
				cliente.setApellido(rSet.getString("Apellido"));
				cliente.setDni(rSet.getString("DNI"));
				cliente.setCuil(rSet.getString("CUIL"));
				cuenta.setCliente(cliente);
				cuenta.setFechaCreacion(rSet.getDate("FechaCreacion"));
				cuenta.setNumeroCuenta(rSet.getString("numeroCuenta"));
				cuenta.setCbu(rSet.getString("CBU"));
				cuenta.setSaldo(rSet.getBigDecimal("Saldo"));
				Tipo tipo = new Tipo();
				tipo.setId(rSet.getInt("IdTipoCuenta"));
				tipo.setDescripcion(rSet.getString("nombreTipoCuenta"));
				cuenta.setTipoCuenta(tipo);
				cuenta.setEliminado(rSet.getBoolean("Eliminado"));
				cuentas.add(cuenta);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cuentas;
	}

	@Override
	public Cuenta obtenerCBU(Cuenta cuenta) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/cuenta/cuenta_por_cbu.sql"));
			pStatement.setString(1, cuenta.getCbu());
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				cuenta.setId(rSet.getInt("Id"));
				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("IdCliente"));
				cuenta.setCliente(cliente);
				cuenta.setFechaCreacion(rSet.getDate("FechaCreacion"));
				cuenta.setNumeroCuenta(rSet.getString("numeroCuenta"));
				cuenta.setTipoCuenta(new Tipo(rSet.getInt("IdTipoCuenta"), rSet.getString("DescripcionTipo")));
				cuenta.setCbu(rSet.getString("CBU"));
				cuenta.setSaldo(rSet.getBigDecimal("Saldo"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cuenta;
	}

	@Override
	public int buscarId(Cliente cliente) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		Cuenta cuenta = new Cuenta();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.abrirQuery("./queries/cuenta/id_por_idcliente.sql"));
			pStatement.setInt(1, cliente.getId());
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
//c.CBU As CBU, c.Saldo AS Saldo, tc.Id As IdTipoCuenta, tc.Descripcion As DescripcionTipo, cAS Eliminado
				cuenta.setId(rSet.getInt("IdCliente"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cuenta.getId();
	}

	@Override
	public String editarCuenta(Cuenta cuenta) throws SaldoNegativo, IngresoDuplicado {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		String isInsertExitoso = "0";
		try
		{
			statement = conexion.prepareStatement(this.abrirQuery("./queries/cuenta/editar_cuenta.sql"));
			statement.setString(1, cuenta.getNumeroCuenta());
			statement.setString(2, cuenta.getCbu());
			statement.setBigDecimal(3, cuenta.getSaldo());
			statement.setInt(4, cuenta.getTipoCuenta().getId());
			statement.setInt(5, cuenta.getId());
			
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
			if(e.getMessage().contains("Check constraint")) {
				SaldoNegativo saldoNegativo = new SaldoNegativo();
				throw saldoNegativo;
			}
			if(e.getMessage().contains("Duplicate entry") && e.getMessage().contains("CBU")) {
				IngresoDuplicado ingresoDuplicado = new IngresoDuplicado("CBU");
				throw ingresoDuplicado;
			}
			if(e.getMessage().contains("Duplicate entry") && e.getMessage().contains("Nro_cuenta")) {
				IngresoDuplicado ingresoDuplicado = new IngresoDuplicado("Numero Cuenta");
				throw ingresoDuplicado;
			}
			e.getMessage();
		}
		return isInsertExitoso;
	}
	
}
