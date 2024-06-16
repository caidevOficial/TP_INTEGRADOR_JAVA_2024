package entidad;

public class Tipo {
	private int id;
	private String descripcion;
	private Boolean eliminado;
	
	public Tipo() {
		// TODO Auto-generated constructor stub
	}
	
	public Tipo(int id) {
		super();
		this.id = id;
	}

	public Tipo(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
	
	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

}
