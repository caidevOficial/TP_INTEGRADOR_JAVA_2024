package entidad;

import java.math.BigDecimal;
import java.sql.Date;

public class Movimiento {
	private int id;
	private Cuenta Cuenta;
	private Tipo tipoMovimiento;
	private Tipo concepto;
	private Date fechaMovimiento;
	private BigDecimal monto;

	public Movimiento() {
		
	}

	public Movimiento(int id, Cuenta idCuenta, Tipo tipoMovimiento, Tipo concepto,
			Date fechaMovimiento, BigDecimal monto) {
		super();
		this.id = id;
		this.Cuenta = idCuenta;
		this.tipoMovimiento = tipoMovimiento;
		this.concepto = concepto;
		this.fechaMovimiento = fechaMovimiento;
		this.monto = monto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cuenta getCuenta() {
		return Cuenta;
	}

	public void setCuenta(Cuenta Cuenta) {
		this.Cuenta = Cuenta;
	}

	public Tipo getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(Tipo tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Tipo getConcepto() {
		return concepto;
	}

	public void setConcepto(Tipo concepto) {
		this.concepto = concepto;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
}
