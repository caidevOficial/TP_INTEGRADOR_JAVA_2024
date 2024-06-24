package dao;

import java.util.ArrayList;

import entidades.Couta;
import entidades.Prestamo;

public interface ICoutaDao {
	public ArrayList<Couta> obtenerCoutas(Prestamo prestamo);
	public Boolean coutaPaga(Couta couta);
}
