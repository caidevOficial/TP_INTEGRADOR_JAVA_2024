package negocioimpl;

import java.util.ArrayList;

import daoimpl.CuotaDaoImpl;
import entidades.Cuota;
import entidades.Prestamo;
import negocio.ICuotaNegocio;

public class CuotaNegocioImpl implements ICuotaNegocio {
	CuotaDaoImpl coutaNegocio = new CuotaDaoImpl();

	@Override
	public ArrayList<Cuota> obtenerCuotas(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return coutaNegocio.obtenerCuotas(prestamo);
	}

	@Override
	public Boolean cuotaPaga(Cuota cuota) {
		// TODO Auto-generated method stub
		return coutaNegocio.cuotaPaga(cuota);
	}


}
