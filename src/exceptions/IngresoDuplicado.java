package exceptions;

import java.sql.SQLException;

public class IngresoDuplicado extends SQLException {
	private static final long serialVersionUID = 1L;
	String columnaIngreso = "";
	public IngresoDuplicado(String columna) {
		this.columnaIngreso = columna;
	}
	
	public String getMessage() {
		return "El campo " + columnaIngreso + " ya se encuentra registrado, ingrese un nuevo " + columnaIngreso;
	}
}
