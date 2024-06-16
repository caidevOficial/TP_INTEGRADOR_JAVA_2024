package negocioImpl;

import daoImpl.UsuarioDaoImpl;
import entidad.Usuario;
import exceptions.IngresoDuplicado;
import exceptions.IngresoLargo;
import negocio.IUsuarioNegocio;

public class UsuarioNegocioImpl implements IUsuarioNegocio {
	
	UsuarioDaoImpl usuarioNegocio = new UsuarioDaoImpl();
	
	public Usuario login(Usuario usuario) {
		return usuarioNegocio.login(usuario);
	}

	@Override
	public String crearUsuario(Usuario usuario) throws IngresoDuplicado, IngresoLargo {
		// TODO Auto-generated method stub
		return usuarioNegocio.crearUsuario(usuario);
	}

	@Override
	public int buscarID(String nombreUsuario) {
		// TODO Auto-generated method stub
		return usuarioNegocio.buscarId(nombreUsuario);
	}

	@Override
	public Boolean bajaUsuario(int Idcliente) {
		// TODO Auto-generated method stub
		return usuarioNegocio.bajaUsuario(Idcliente);
	}

	@Override
	public Boolean altaUsuario(int Idcliente) {
		// TODO Auto-generated method stub
		return usuarioNegocio.altaUsuario(Idcliente);
	}

	@Override
	public Boolean cambiarPassUsuario(int Idcliente, String nuevaPass) {
		// TODO Auto-generated method stub
		return usuarioNegocio.cambiarPassUsuario(Idcliente, nuevaPass);
	}

	@Override
	public String editarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioNegocio.editarUsuario(usuario);
	}
	
}
