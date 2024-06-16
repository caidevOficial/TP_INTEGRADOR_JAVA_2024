package dao;

import java.util.ArrayList;
import entidad.Couta;
import entidad.Prestamo;

public interface ICoutaDao {
	public ArrayList<Couta> obtenerCoutas(Prestamo prestamo);
	public Boolean coutaPaga(Couta couta);
}
