package negocioImpl;

import java.util.ArrayList;

import daoImpl.PrestamoDaoImpl;
import entidad.Cliente;
import entidad.Prestamo;
import negocio.IPrestamoNegocio;

public class PrestamoNegocioImpl implements IPrestamoNegocio {
	PrestamoDaoImpl prestamoNegocio = new PrestamoDaoImpl();

	@Override
	public ArrayList<Prestamo> getPrestamos() {
		// TODO Auto-generated method stub
		return prestamoNegocio.getPrestamos();
	}

	@Override
	public String insertarPrestamo(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return prestamoNegocio.insertarPrestamo(prestamo);
	}

	@Override
	public Boolean aprobar(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return prestamoNegocio.aprobar(prestamo);
	}

	@Override
	public Boolean rechazar(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return prestamoNegocio.rechazar(prestamo);
	}

	@Override
	public ArrayList<Prestamo> getPrestamosPendientes() {
		// TODO Auto-generated method stub
		return prestamoNegocio.getPrestamosPendientes();
	}

	@Override
	public ArrayList<Prestamo> getPrestamosPendientes(String terminoBuscar) {
		// TODO Auto-generated method stub
		return prestamoNegocio.getPrestamosPendientes(terminoBuscar);
	}

	@Override
	public ArrayList<Prestamo> getPrestamosAprobados(Cliente cliente) {
		// TODO Auto-generated method stub
		return prestamoNegocio.getPrestamosAprobados(cliente);
	}

	@Override
	public int[] informe(int mes) {
		// TODO Auto-generated method stub
		return prestamoNegocio.informe(mes);
	}

}
