package negocioImpl;

import java.math.BigDecimal;
import java.util.ArrayList;

import daoImpl.CuentaDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import exceptions.IngresoDuplicado;
import exceptions.NoExiste;
import exceptions.SaldoNegativo;
import exceptions.TriggerCreacionExcedida;
import negocio.ICuentaNegocio;

public class CuentaNegocioImpl implements ICuentaNegocio {
	CuentaDaoImpl cuentasNegocio = new CuentaDaoImpl();
	
	public ArrayList<Cuenta> obtenerCuentas() {
		return cuentasNegocio.obtenerCuentas();
	}

	public String crearCuenta(Cuenta cuenta) throws TriggerCreacionExcedida, IngresoDuplicado, NoExiste {
		return cuentasNegocio.crearCuenta(cuenta);
	}

	@Override
	public Boolean bajaCuenta(int id) {
		// TODO Auto-generated method stub
		return cuentasNegocio.bajaCuenta(id);
	}

	@Override
	public Boolean altaCuenta(int id) {
		// TODO Auto-generated method stub
		return cuentasNegocio.altaCuenta(id);
	}

	@Override
	public int ultimoId() {
		// TODO Auto-generated method stub
		return cuentasNegocio.ultimoId();
	}

	@Override
	public Boolean actualizarSaldoSumar(Cuenta cuenta, BigDecimal nuevosaldo) {
		// TODO Auto-generated method stub
		return cuentasNegocio.actualizarSaldoSumar(cuenta, nuevosaldo);
	}

	@Override
	public String actualizarSaldoRestar(Cuenta cuenta, BigDecimal nuevosaldo) throws SaldoNegativo {
		// TODO Auto-generated method stub
		return cuentasNegocio.actualizarSaldoRestar(cuenta, nuevosaldo);
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentas(Cliente cliente) {
		// TODO Auto-generated method stub
		return cuentasNegocio.obtenerCuentas(cliente);
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentas(String terminoBuscar) {
		// TODO Auto-generated method stub
		return cuentasNegocio.obtenerCuentas(terminoBuscar);
	}

	@Override
	public Cuenta obtenerCBU(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return cuentasNegocio.obtenerCBU(cuenta);
	}

	@Override
	public int buscarId(Cliente cliente) {
		// TODO Auto-generated method stub
		return cuentasNegocio.buscarId(cliente);
	}

	@Override
	public String editarCuenta(Cuenta cuenta) throws SaldoNegativo, IngresoDuplicado {
		// TODO Auto-generated method stub
		return cuentasNegocio.editarCuenta(cuenta);
	}
}
