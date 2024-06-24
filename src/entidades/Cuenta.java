package entidades;

import java.math.BigDecimal;
import java.sql.Date;

public class Cuenta {
	private int id;
	private Cliente cliente;
	private Date fechaCreacion;
	private String numeroCuenta;
	private String cbu;
	private BigDecimal saldo;
	private Tipo tipoCuenta;
	private Boolean eliminado;

	public Cuenta() {
		
	}

	public Cuenta(int id, int id_cliente, Date fechaCreacion, String numeroCuenta, String cbu, BigDecimal saldo,
			Tipo tipoCuenta) {
		super();
		this.id = id;
		cliente.setId(id_cliente);
		this.fechaCreacion = fechaCreacion;
		this.numeroCuenta = numeroCuenta;
		this.cbu = cbu;
		this.saldo = saldo;
		this.tipoCuenta = tipoCuenta;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Tipo getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(Tipo tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	
}
