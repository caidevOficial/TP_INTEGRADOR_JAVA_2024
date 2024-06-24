package negocio;

import java.util.ArrayList;

import entidades.Couta;
import entidades.Prestamo;

public interface ICoutaNegocio {
	public ArrayList<Couta> obtenerCoutas(Prestamo prestamo);
	public Boolean coutaPaga(Couta couta);
}
