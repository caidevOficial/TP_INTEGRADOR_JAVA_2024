package entidades;

import java.sql.Date;

public class Cuota {
	private int id;
	private Prestamo prestamo;
	private Date fecha_pago;
	private int numeroCuota;
	private Boolean paga;
	
	public Cuota() {
		
	}

	public Cuota(int id, Prestamo prestamo, Date fecha_pago, int numeroCuota, Boolean paga) {
		super();
		this.id = id;
		this.prestamo = prestamo;
		this.fecha_pago = fecha_pago;
		this.numeroCuota = numeroCuota;
		this.paga = paga;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public Date getFecha_pago() {
		return fecha_pago;
	}

	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}

	public int getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}
	
	
}
