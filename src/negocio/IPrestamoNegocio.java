package negocio;

import java.util.ArrayList;

import entidades.Cliente;
import entidades.Prestamo;

public interface IPrestamoNegocio {
	public ArrayList<Prestamo> getPrestamos();
	public ArrayList<Prestamo> getPrestamosPendientes();
	public ArrayList<Prestamo> getPrestamosPendientes(String terminoBuscar);
	public ArrayList<Prestamo> getPrestamosAprobados(Cliente cliente);
	public String insertarPrestamo(Prestamo prestamo);
	public Boolean aprobar(Prestamo prestamo);
	public Boolean rechazar(Prestamo prestamo);
	public int[] informe(int mes);
}
