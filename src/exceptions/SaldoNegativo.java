package exceptions;

import java.sql.SQLException;

public class SaldoNegativo extends SQLException {
	private static final long serialVersionUID = 1L;
	public SaldoNegativo() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMessage() {
		return "No tiene el suficiente saldo para realizar esta operacion";
	}
}
