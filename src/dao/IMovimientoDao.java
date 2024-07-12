package dao;

import java.util.ArrayList;

import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Tipo;

public interface IMovimientoDao {
	public Boolean movimientoBanco(Movimiento movimiento);
	public ArrayList<Movimiento> obtenerMovimientos(Cuenta cuenta);
	public ArrayList<Movimiento> obtenerMovimientos(Cuenta cuenta, String terminoBuscar);
	public int importTotal(Tipo tipoMovimiento, int Mes);
	public int ultimoIdMovimiento();
}
