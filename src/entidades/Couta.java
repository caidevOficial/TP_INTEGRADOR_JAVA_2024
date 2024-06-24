package entidades;

import java.sql.Date;

public class Couta {
	private int id;
	private Prestamo prestamo;
	private Date fecha_pago;
	private int numeroCouta;
	private Boolean paga;
	
	public Couta() {
		
	}

	public Couta(int id, Prestamo prestamo, Date fecha_pago, int numeroCouta, Boolean paga) {
		super();
		this.id = id;
		this.prestamo = prestamo;
		this.fecha_pago = fecha_pago;
		this.numeroCouta = numeroCouta;
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

	public int getNumeroCouta() {
		return numeroCouta;
	}

	public void setNumeroCouta(int numeroCouta) {
		this.numeroCouta = numeroCouta;
	}

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}
	
	
}
