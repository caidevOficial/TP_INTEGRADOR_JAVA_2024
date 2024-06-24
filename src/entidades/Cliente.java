package entidades;

import java.sql.Date;

public class Cliente {
	private int id;
	private Usuario usuario;
	private String nombre;
	private String apellido;
	private String dni;
	private String cuil;
	private String direccion;
	private Tipo localidad;
	private Tipo provincia;
	private Tipo nacionalidad;
	private Tipo genero;
	private Date fechaNacimiento;
	private String telefono;
	private Boolean eliminado;
	
	public Cliente() {
		
	}

	public Cliente(int id, String nombre, String apellido, String dni, String cuil, String direccion, String localidad,
			String provincia, String nacionalidad, int genero, Date fechaNacimiento, String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.cuil = cuil;
		this.direccion = direccion;
		this.genero.setId(genero);
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
	}
	
	

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Tipo getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Tipo localidad) {
		this.localidad = localidad;
	}

	public Tipo getProvincia() {
		return provincia;
	}

	public void setProvincia(Tipo provincia) {
		this.provincia = provincia;
	}

	public Tipo getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Tipo nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Tipo getGenero() {
		return genero;
	}

	public void setGenero(Tipo genero) {
		this.genero = genero;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
