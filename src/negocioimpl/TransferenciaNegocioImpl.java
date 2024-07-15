package negocioimpl;

import java.sql.Date;
import java.util.ArrayList;

import daoimpl.TransferenciaDaoImpl;
import entidades.Cuenta;
import entidades.Transferencia;
import negocio.ITransferenciaNegocio;

public class TransferenciaNegocioImpl implements ITransferenciaNegocio{

	private TransferenciaDaoImpl transferenciaNegocio ;
	
	public TransferenciaNegocioImpl() {
		transferenciaNegocio = new TransferenciaDaoImpl();
	}
	
	@Override
	public boolean transferenciaBanco(Transferencia transferencia) {
		return transferenciaNegocio.transferenciaBanco(transferencia);
	}

	@Override
	public ArrayList<Transferencia> obtenerTransferencias(Cuenta cuenta) {
		return transferenciaNegocio.obtenerTransferencias(cuenta);
	}

	@Override
	public ArrayList<Transferencia> obtenerTransferencias(Cuenta cuenta, Date fecha) {
		return transferenciaNegocio.obtenerTransferencias(cuenta, fecha);
	}

	@Override
	public ArrayList<Transferencia> obtenerTransferencias(Cuenta cuenta, Date fecha_inicio, Date fecha_final) {
		return transferenciaNegocio.obtenerTransferencias(cuenta, fecha_inicio, fecha_final);
	}
}
