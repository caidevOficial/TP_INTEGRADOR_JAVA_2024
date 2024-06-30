package dao;

import java.util.ArrayList;

import entidades.Cuota;
import entidades.Prestamo;

public interface ICuotaDao {
	public ArrayList<Cuota> obtenerCuotas(Prestamo prestamo);
	public Boolean cuotaPaga(Cuota cuota);
}
