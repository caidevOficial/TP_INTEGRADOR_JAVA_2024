package exceptions;

import java.sql.SQLException;

public class IngresoLargo extends SQLException {
	private static final long serialVersionUID = 1L;
	String columnaIngreso = "";
	
	public IngresoLargo(String columna) {
		this.columnaIngreso = columna;
	}
	
	public String getMessage() {
		return "El campo " + columnaIngreso + " es demasiado largo";
	}
}
