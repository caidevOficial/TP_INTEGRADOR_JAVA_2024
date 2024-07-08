/**
 * 
 */
package entidades;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author Administrator
 *
 */
public class Transferencia extends Movimiento {
	
	private Cuenta idCuentaDestino;

	/**
	 * 
	 */
	public Transferencia() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param idCuentaOrigen
	 * @param idCuentaDestino
	 * @param tipoMovimiento
	 * @param concepto
	 * @param fechaMovimiento
	 * @param monto
	 */
	public Transferencia(int id, Cuenta idCuentaOrigen, Cuenta idCuentaDestino, Tipo tipoMovimiento, Tipo concepto, Date fechaMovimiento,
			BigDecimal monto) {
		super(id, idCuentaOrigen, tipoMovimiento, concepto, fechaMovimiento, monto);
		this.idCuentaDestino = idCuentaDestino;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the idCuentaDestino
	 */
	public Cuenta getIdCuentaDestino() {
		return this.idCuentaDestino;
	}

	/**
	 * @param idCuentaDestino the idCuentaDestino to set
	 */
	public void setIdCuentaDestino(Cuenta idCuentaDestino) {
		this.idCuentaDestino = idCuentaDestino;
	}

}
