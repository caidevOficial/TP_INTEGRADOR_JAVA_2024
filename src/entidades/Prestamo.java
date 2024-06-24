package entidades;

import java.math.BigDecimal;
import java.sql.Date;

public class Prestamo {
	private int id;
	private Cuenta cuenta;
	private Date fechaPedido;
	private Tipo estado;
	private BigDecimal montoSolicitado;
	private BigDecimal montoCuota;
	private int cantidadCuotas;
	
	public Prestamo() {

	}

	public Prestamo(int id, Cuenta cuenta, Date fechaPedido, Tipo estado, BigDecimal montoSolicitado,
			BigDecimal montoCuota, int cantidadCuotas) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.fechaPedido = fechaPedido;
		this.estado = estado;
		this.montoSolicitado = montoSolicitado;
		this.montoCuota = montoCuota;
		this.cantidadCuotas = cantidadCuotas;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Tipo getEstado() {
		return estado;
	}

	public void setEstado(Tipo estado) {
		this.estado = estado;
	}

	public BigDecimal getMontoSolicitado() {
		return montoSolicitado;
	}

	public void setMontoSolicitado(BigDecimal montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}

	public BigDecimal getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(BigDecimal montoCuota) {
		this.montoCuota = montoCuota;
	}

	public int getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}
}
