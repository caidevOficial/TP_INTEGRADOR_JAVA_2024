package dao;

import java.sql.Date;
import java.util.ArrayList;

import entidades.Cuenta;
import entidades.Transferencia;

public interface ITransferenciaDao {
	public boolean transferenciaBanco(Transferencia transferencia);
	public ArrayList<Transferencia> obtenerTransferencias(Cuenta cuenta);
	public ArrayList<Transferencia> obtenerTransferencias(Cuenta cuenta, Date fecha);
	public ArrayList<Transferencia> obtenerTransferencias(Cuenta cuenta, Date fecha_inicio, Date fecha_final);
}
