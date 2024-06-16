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
import queries.Queries;

public class CuentaDaoImpl implements ICuentaDao {
	
	Queries queryManager;
	
	private String getCuentas;
	private String getCuentasBuscar;
	private String getCuentasCliente;
	private String crearCuenta;
	private String bajaCuenta;
	private String altaCuenta; 
	private String actualizarSaldoSumar;
	private String actualizarSaldoRestar;
	private String ultimoId;
	private String cuentaPorCBU; 
	private String idPorIdCliente; 
	private String editarCuenta;
	
	public CuentaDaoImpl() {
		this.altaCuenta = this.queryManager.altaCuenta;
		this.bajaCuenta = this.queryManager.bajaCuenta;
		this.crearCuenta = this.queryManager.crearCuenta;
		this.editarCuenta = this.queryManager.editarCuenta;
		this.getCuentas = this.queryManager.getCuentas;
		this.getCuentasBuscar = this.queryManager.getCuentasBuscar;
		this.getCuentasCliente = this.queryManager.getCuentasClientes;
		this.actualizarSaldoSumar = this.queryManager.sumarSaldo;
		this.actualizarSaldoRestar = this.queryManager.restarSaldo;
		this.ultimoId = this.queryManager.ultimoId;
		this.cuentaPorCBU = this.queryManager.cuentaPorCbu;
		this.idPorIdCliente = this.queryManager.idPorIdCliente;
	}

	public ArrayList<Cuenta> obtenerCuentas() {
		Connection connection = Conexion.getConexion().getSQLConexion();
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getCuentas);
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
			statement = conexion.prepareStatement(this.crearCuenta);
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
			statement = conexion.prepareStatement(this.bajaCuenta);
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
			statement = conexion.prepareStatement(this.altaCuenta);
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
			PreparedStatement pStatement =  connection.prepareStatement(this.ultimoId);
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
			statement = conexion.prepareStatement(this.actualizarSaldoSumar);
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
			statement = conexion.prepareStatement(this.actualizarSaldoRestar);
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
			PreparedStatement pStatement =  connection.prepareStatement(this.getCuentasCliente);
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
			PreparedStatement pStatement =  connection.prepareStatement(this.getCuentasBuscar);
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
			PreparedStatement pStatement =  connection.prepareStatement(this.cuentaPorCBU);
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
			PreparedStatement pStatement =  connection.prepareStatement(this.idPorIdCliente);
			pStatement.setInt(1, cliente.getId());
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
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
			statement = conexion.prepareStatement(this.editarCuenta);
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
