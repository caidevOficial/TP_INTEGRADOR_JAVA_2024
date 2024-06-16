package negocioImpl;

import java.util.ArrayList;

import daoImpl.CoutaDaoImpl;
import entidad.Couta;
import entidad.Prestamo;
import negocio.ICoutaNegocio;

public class CoutaNegocioImpl implements ICoutaNegocio {
	CoutaDaoImpl coutaNegocio = new CoutaDaoImpl();

	@Override
	public ArrayList<Couta> obtenerCoutas(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return coutaNegocio.obtenerCoutas(prestamo);
	}

	@Override
	public Boolean coutaPaga(Couta couta) {
		// TODO Auto-generated method stub
		return coutaNegocio.coutaPaga(couta);
	}


}
