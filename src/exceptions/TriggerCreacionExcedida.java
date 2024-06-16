package exceptions;

import java.sql.SQLException;

public class TriggerCreacionExcedida extends SQLException {
	private static final long serialVersionUID = 1L;
	private String tabla = "";
	private Boolean cliente = false;
	public TriggerCreacionExcedida(String tabla, Boolean cliente) {
		this.tabla = tabla;
		this.cliente = cliente;
	}
	public TriggerCreacionExcedida(Boolean cliente) {
		this.cliente = cliente;
	}
	
	public String getMessage() {
		if(!cliente) {
			return "Ya existe un registro para ese id en la base de datos en la tabla " + tabla;
		}
		return "El cliente excedio la cantidad de cuentas que se pueden crear";
	}
}
