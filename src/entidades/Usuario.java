package entidades;

public class Usuario {
	private int id;
	private Tipo tipoRol;
	private String nombreUsuario;
	private String email;
	private String password;
	private Boolean eliminado;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public Usuario(String nombreUsuario, String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.nombreUsuario = nombreUsuario;
	}

	public Usuario(int id, Tipo tipoRol, String email, String password, Boolean eliminado) {
		super();
		this.id = id;
		this.tipoRol = tipoRol;
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
}
