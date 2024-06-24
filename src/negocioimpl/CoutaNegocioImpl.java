package negocioimpl;

import java.util.ArrayList;

import daoimpl.CoutaDaoImpl;
import entidades.Couta;
import entidades.Prestamo;
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
