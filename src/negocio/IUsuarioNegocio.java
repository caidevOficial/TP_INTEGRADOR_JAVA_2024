package negocio;

import entidades.Usuario;
import exceptions.IngresoDuplicado;
import exceptions.IngresoLargo;

public interface IUsuarioNegocio {
	public Usuario login(Usuario usuario);
	public String crearUsuario(Usuario usuario) throws IngresoDuplicado, IngresoLargo;
	public int buscarID(String email);
	public String editarUsuario(Usuario usuario);
	public Boolean bajaUsuario(int Idcliente);
	public Boolean altaUsuario(int Idcliente);
	public Boolean cambiarPassUsuario(int Idcliente, String nuevaPass);
}
