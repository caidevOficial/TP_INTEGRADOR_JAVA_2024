package negocio;

import java.util.ArrayList;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Tipo;

public interface IMovimientoNegocio {
	public Boolean movimientoBanco(Movimiento movimiento);
	public ArrayList<Movimiento> obtenerMovimientos(Cuenta cuenta);
	public ArrayList<Movimiento> obtenerMovimientos(Cuenta cuenta, String terminoBuscar);
	public int importTotal(Tipo tipoMovimiento, int Mes);
}
