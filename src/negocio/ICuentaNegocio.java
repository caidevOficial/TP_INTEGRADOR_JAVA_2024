package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;

import entidad.Cliente;
import entidad.Cuenta;
import exceptions.IngresoDuplicado;
import exceptions.NoExiste;
import exceptions.SaldoNegativo;
import exceptions.TriggerCreacionExcedida;

public interface ICuentaNegocio {
	public ArrayList<Cuenta> obtenerCuentas();
	public ArrayList<Cuenta> obtenerCuentas(Cliente cliente);
	public ArrayList<Cuenta> obtenerCuentas(String terminoBuscar);
	public Cuenta obtenerCBU(Cuenta cuenta);
	public int buscarId(Cliente cliente);
	public String crearCuenta(Cuenta cuenta) throws TriggerCreacionExcedida, IngresoDuplicado, NoExiste;
	public Boolean actualizarSaldoSumar(Cuenta cuenta, BigDecimal nuevosaldo);
	public String actualizarSaldoRestar(Cuenta cuenta, BigDecimal nuevosaldo) throws SaldoNegativo;
	public Boolean bajaCuenta(int id);
	public Boolean altaCuenta(int id);
	public String editarCuenta(Cuenta cuenta) throws SaldoNegativo, IngresoDuplicado;
	public int ultimoId();
}
