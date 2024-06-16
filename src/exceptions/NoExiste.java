package exceptions;

import java.sql.SQLException;

public class NoExiste extends SQLException {
	private static final long serialVersionUID = 1L;
	public NoExiste() {
	}
	
	public String getMessage() {
		return "No Existe el DNI ingresado";
	}
}
