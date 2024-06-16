package negocioImpl;

import java.util.ArrayList;

import dao.IMovimientoDao;
import daoImpl.MovimientoDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Tipo;

public class MovimientoNegocioImpl implements IMovimientoDao {
	MovimientoDaoImpl movimientoNeogocio = new MovimientoDaoImpl();


	@Override
	public Boolean movimientoBanco(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return movimientoNeogocio.movimientoBanco(movimiento);
	}

	@Override
	public ArrayList<Movimiento> obtenerMovimientos(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return movimientoNeogocio.obtenerMovimientos(cuenta);
	}

	@Override
	public ArrayList<Movimiento> obtenerMovimientos(Cuenta cuenta, String terminoBuscar) {
		// TODO Auto-generated method stub
		return movimientoNeogocio.obtenerMovimientos(cuenta, terminoBuscar);
	}

	@Override
	public int importTotal(Tipo tipoMovimiento, int Mes) {
		// TODO Auto-generated method stub
		return movimientoNeogocio.importTotal(tipoMovimiento, Mes);
	}

}
