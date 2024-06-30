package negocio;

import java.util.ArrayList;

import entidades.Cuota;
import entidades.Prestamo;

public interface ICuotaNegocio {
	public ArrayList<Cuota> obtenerCuotas(Prestamo prestamo);
	public Boolean cuotaPaga(Cuota cuota);
}
