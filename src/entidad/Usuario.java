package entidad;

public class Usuario {
	private int id;
	private Tipo tipoRol;
	private String nombreUsuario;
	private String email;
	private String contraseña;
	private Boolean eliminado;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String email, String contraseña) {
		super();
		this.email = email;
		this.contraseña = contraseña;
	}
	
	public Usuario(String nombreUsuario, String email, String contraseña) {
		super();
		this.email = email;
		this.contraseña = contraseña;
		this.nombreUsuario = nombreUsuario;
	}

	public Usuario(int id, Tipo tipoRol, String email, String contraseña, Boolean eliminado) {
		super();
		this.id = id;
		this.tipoRol = tipoRol;
		this.email = email;
		this.contraseña = contraseña;
		this.eliminado = eliminado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tipo getTipoRol() {
		return tipoRol;
	}

	public void setTipoRol(Tipo tipoRol) {
		this.tipoRol = tipoRol;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
}
