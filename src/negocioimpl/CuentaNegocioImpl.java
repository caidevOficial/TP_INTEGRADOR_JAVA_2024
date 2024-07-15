package negocioimpl;

import java.math.BigDecimal;
import java.util.ArrayList;

import daoimpl.CuentaDaoImpl;
import entidades.Cliente;
import entidades.Cuenta;
import exceptions.IngresoDuplicado;
import exceptions.NoExiste;
import exceptions.SaldoNegativo;
import exceptions.TriggerCreacionExcedida;
import negocio.ICuentaNegocio;

public class CuentaNegocioImpl implements ICuentaNegocio {
	private CuentaDaoImpl cuentasNegocio;
	
	public CuentaNegocioImpl() {
		this.cuentasNegocio = new CuentaDaoImpl();
	}
	
	public ArrayList<Cuenta> obtenerCuentas() {
		return cuentasNegocio.obtenerCuentas();
	}

	public String crearCuenta(Cuenta cuenta) throws TriggerCreacionExcedida, IngresoDuplicado, NoExiste {
		return cuentasNegocio.crearCuenta(cuenta);
	}

	@Override
	public Boolean bajaCuenta(int id) {
		return cuentasNegocio.bajaCuenta(id);
	}

	@Override
	public Boolean altaCuenta(int id) {
		return cuentasNegocio.altaCuenta(id);
	}

	@Override
	public int ultimoId() {
		return cuentasNegocio.ultimoId();
	}

	@Override
	public Boolean actualizarSaldoSumar(Cuenta cuenta, BigDecimal nuevosaldo) {
		return cuentasNegocio.actualizarSaldoSumar(cuenta, nuevosaldo);
	}

	@Override
	public String actualizarSaldoRestar(Cuenta cuenta, BigDecimal nuevosaldo) throws SaldoNegativo {
		return cuentasNegocio.actualizarSaldoRestar(cuenta, nuevosaldo);
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentas(Cliente cliente) {
		return cuentasNegocio.obtenerCuentas(cliente);
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentas(String terminoBuscar) {
		return cuentasNegocio.obtenerCuentas(terminoBuscar);
	}

	@Override
	public Cuenta obtenerCBU(Cuenta cuenta) {
		return cuentasNegocio.obtenerCBU(cuenta);
	}

	@Override
	public int buscarId(Cliente cliente) {
		return cuentasNegocio.buscarId(cliente);
	}

	@Override
	public String editarCuenta(Cuenta cuenta) throws SaldoNegativo, IngresoDuplicado {
		return cuentasNegocio.editarCuenta(cuenta);
	}
}
