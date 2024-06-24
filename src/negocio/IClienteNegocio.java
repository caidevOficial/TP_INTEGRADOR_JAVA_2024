package negocio;

import java.util.ArrayList;

import entidades.Cliente;
import entidades.Usuario;
import exceptions.IngresoDuplicado;
import exceptions.TriggerCreacionExcedida;

public interface IClienteNegocio {
	public int buscarId(String dni);
	public int buscarId(Usuario usuario);
	public String crearCliente(Cliente cliente) throws TriggerCreacionExcedida, IngresoDuplicado;
	public String editarCliente(Cliente cliente);
	public ArrayList<Cliente> obtenerClientes();
	public ArrayList<Cliente> obtenerClientes(String terminoBuscar);
	public Cliente obtenerCliente(Cliente clienteBuscar);
	public Boolean bajaCliente(String dni);
	public Boolean altaCliente(String dni);
}
