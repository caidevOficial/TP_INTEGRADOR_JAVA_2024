package negocioImpl;

import java.util.ArrayList;

import daoImpl.ClienteDaoImpl;
import entidad.Cliente;
import entidad.Usuario;
import exceptions.IngresoDuplicado;
import exceptions.TriggerCreacionExcedida;
import negocio.IClienteNegocio;

public class ClienteNegocioImpl implements IClienteNegocio {
	
	ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();

	public int buscarId(String dni) {
		return clienteDaoImpl.buscarId(dni);
	}

	public String crearCliente(Cliente cliente) throws TriggerCreacionExcedida, IngresoDuplicado {
		return clienteDaoImpl.crearCliente(cliente);
	}

	@Override
	public ArrayList<Cliente> obtenerClientes() {
		return clienteDaoImpl.obtenerClientes();
	}

	@Override
	public Boolean bajaCliente(String dni) {
		// TODO Auto-generated method stub
		return clienteDaoImpl.bajaCliente(dni);
	}

	@Override
	public Boolean altaCliente(String dni) {
		// TODO Auto-generated method stub
		return clienteDaoImpl.altaCliente(dni);
	}

	@Override
	public int buscarId(Usuario usuario) {
		// TODO Auto-generated method stub
		return clienteDaoImpl.buscarId(usuario);
	}

	@Override
	public Cliente obtenerCliente(Cliente clienteBuscar) {
		// TODO Auto-generated method stub
		return clienteDaoImpl.obtenerCliente(clienteBuscar);
	}

	@Override
	public ArrayList<Cliente> obtenerClientes(String terminoBuscar) {
		// TODO Auto-generated method stub
		return clienteDaoImpl.obtenerClientes(terminoBuscar);
	}

	@Override
	public String editarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDaoImpl.editarCliente(cliente);
	}

}
