package entidad;

public class Usuario {
	private int id;
	private Tipo tipoRol;
	private String nombreUsuario;
	private String email;
	private String contrase�a;
	private Boolean eliminado;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String email, String contrase�a) {
		super();
		this.email = email;
		this.contrase�a = contrase�a;
	}
	
	public Usuario(String nombreUsuario, String email, String contrase�a) {
		super();
		this.email = email;
		this.contrase�a = contrase�a;
		this.nombreUsuario = nombreUsuario;
	}

	public Usuario(int id, Tipo tipoRol, String email, String contrase�a, Boolean eliminado) {
		super();
		this.id = id;
		this.tipoRol = tipoRol;
		this.email = email;
		this.contrase�a = contrase�a;
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

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
}
