package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import dao.ITransferenciaDao;
import entidades.Cuenta;
import entidades.Tipo;
import entidades.Transferencia;
import queries.Queries;

public class TransferenciaDaoImpl implements ITransferenciaDao{

	private Queries queryManager;
	private String altaTransferencia;
	private String getTransferencias;
	private String getTransferenciasFecha;
	private String getTransferenciasFechas;
	
	public TransferenciaDaoImpl() {
		this.queryManager = new Queries();
		this.altaTransferencia = this.queryManager.getAltaTransferencia();
		this.getTransferencias = this.queryManager.getGetTransferencias();
		this.getTransferenciasFecha = this.queryManager.getGetTransferenciasFecha();
		this.getTransferenciasFechas = this.queryManager.getGetTransferenciasFechas();
	}
	
	@Override
	public boolean transferenciaBanco(Transferencia transferencia) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		MovimientoDaoImpl daoMovimiento = new MovimientoDaoImpl();
		Boolean isInsertExitoso = false;
		
		try
		{
			if (daoMovimiento.movimientoBanco(transferencia)) {				
				statement = conexion.prepareStatement(this.altaTransferencia);
				statement.setInt(1, daoMovimiento.ultimoIdMovimiento());
				statement.setInt(2, transferencia.getIdCuentaDestino().getId());
				
				if(statement.executeUpdate() > 0)
				{
					conexion.commit();
					isInsertExitoso = true;
				}
			}
			else {
				System.out.println("Error al dar de alta movimiento");
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
		finally {
			Conexion.getConexion().cerrarConexion();
		}

		return isInsertExitoso;
	}

	@Override
	public ArrayList<Transferencia> obtenerTransferencias(Cuenta cuenta) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		CuentaDaoImpl daoCuenta = new CuentaDaoImpl();
		
		ArrayList<Transferencia> transferencias = new ArrayList<Transferencia>();
		
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getTransferencias);
			pStatement.setInt(1, cuenta.getId());
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				
				Transferencia transferencia = new Transferencia();
				
				transferencia.setId(rSet.getInt("Id"));
				cuenta.setId(rSet.getInt("IdCuenta"));
				cuenta.setSaldo(rSet.getBigDecimal("Saldo"));
				transferencia.setCuenta(cuenta);
				transferencia.setTipoMovimiento(new Tipo(rSet.getInt("IDMovimiento"), rSet.getString("DescripcionMovimiento")));
				transferencia.setConcepto(new Tipo(rSet.getInt("IDConcepto"), rSet.getString("DescripcionConcepto")));
				transferencia.setFechaMovimiento(rSet.getDate("Fecha"));
				transferencia.setMonto(rSet.getBigDecimal("Monto"));
				transferencia.setIdCuentaDestino(daoCuenta.obtenerCuenta(rSet.getInt("CuentaDestino")));
				
				transferencias.add(transferencia);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Conexion.getConexion().cerrarConexion();
		}

		return transferencias;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ArrayList<Transferencia> obtenerTransferencias(Cuenta cuenta, Date fecha) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		CuentaDaoImpl daoCuenta = new CuentaDaoImpl();
		
		ArrayList<Transferencia> transferencias = new ArrayList<Transferencia>();
		
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getTransferenciasFecha);
			pStatement.setInt(1, cuenta.getId());
			pStatement.setInt(2, fecha.getYear());
			pStatement.setInt(3, fecha.getMonth());
			pStatement.setInt(4, fecha.getDay());
			
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				
				Transferencia transferencia = new Transferencia();
				
				transferencia.setId(rSet.getInt("Id"));
				cuenta.setId(rSet.getInt("IdCuenta"));
				cuenta.setSaldo(rSet.getBigDecimal("Saldo"));
				transferencia.setCuenta(cuenta);
				transferencia.setTipoMovimiento(new Tipo(rSet.getInt("IDMovimiento"), rSet.getString("DescripcionMovimiento")));
				transferencia.setConcepto(new Tipo(rSet.getInt("IDConcepto"), rSet.getString("DescripcionConcepto")));
				transferencia.setFechaMovimiento(rSet.getDate("Fecha"));
				transferencia.setMonto(rSet.getBigDecimal("Monto"));
				transferencia.setIdCuentaDestino(daoCuenta.obtenerCuenta(rSet.getInt("CuentaDestino")));
				
				transferencias.add(transferencia);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Conexion.getConexion().cerrarConexion();
		}

		return transferencias;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ArrayList<Transferencia> obtenerTransferencias(Cuenta cuenta, Date fecha_inicio, Date fecha_final) {
		Connection connection = Conexion.getConexion().getSQLConexion();
		CuentaDaoImpl daoCuenta = new CuentaDaoImpl();
		
		ArrayList<Transferencia> transferencias = new ArrayList<Transferencia>();
		
		try {
			PreparedStatement pStatement =  connection.prepareStatement(this.getTransferenciasFechas);
			pStatement.setInt(1, cuenta.getId());
			pStatement.setInt(2, fecha_inicio.getYear());
			pStatement.setInt(3, fecha_final.getYear());
			pStatement.setInt(4, fecha_inicio.getMonth());
			pStatement.setInt(5, fecha_final.getMonth());
			pStatement.setInt(6, fecha_inicio.getDay());
			pStatement.setInt(7, fecha_final.getDay());
			
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				
				Transferencia transferencia = new Transferencia();
				
				transferencia.setId(rSet.getInt("Id"));
				cuenta.setId(rSet.getInt("IdCuenta"));
				cuenta.setSaldo(rSet.getBigDecimal("Saldo"));
				transferencia.setCuenta(cuenta);
				transferencia.setTipoMovimiento(new Tipo(rSet.getInt("IDMovimiento"), rSet.getString("DescripcionMovimiento")));
				transferencia.setConcepto(new Tipo(rSet.getInt("IDConcepto"), rSet.getString("DescripcionConcepto")));
				transferencia.setFechaMovimiento(rSet.getDate("Fecha"));
				transferencia.setMonto(rSet.getBigDecimal("Monto"));
				transferencia.setIdCuentaDestino(daoCuenta.obtenerCuenta(rSet.getInt("CuentaDestino")));
				
				transferencias.add(transferencia);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Conexion.getConexion().cerrarConexion();
		}

		return transferencias;
	}
}
