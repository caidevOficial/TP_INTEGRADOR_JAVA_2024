package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Tipo;
import entidades.Usuario;
import negocioimpl.ClienteNegocioImpl;
import negocioimpl.TipoNegocioImpl;
import negocioimpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class servletAdminClientes
 */
@WebServlet("/servletAdminClientes")
public class servletAdminClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAdminClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("cargarClientes") != null) {
			cargarClientes(request, response);
			return;
		}
		if(request.getParameter("btnEliminar") != null) {
			bajaCliente(request, response);
			return;
		}
		if(request.getParameter("btnAlta") != null) {
			altaCliente(request, response);
			return;
		}
		if(request.getParameter("btnEditar") != null) {
			btnEditar(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnCrearCliente") != null) {
			crearUsuarioCliente(request, response);
			return;
		}
		if (request.getParameter("btnBuscarCliente") != null) {
			cargarClientesBuscador(request, response);
			return;
		}
		if (request.getParameter("btnEditarCliente") != null) {
			btnEditarCliente(request, response);
			return;
		}
		if (request.getParameter("btnCambiarPass") != null) {
			cambiarPass(request, response);
			return;
		}
	}
	
	//DO GET
	protected void cargarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		request.getSession().setAttribute("clientes", clienteNegocio.obtenerClientes());
		
		RequestDispatcher rd = request.getRequestDispatcher("Clientes.jsp");
		rd.forward(request, response);
	}
	
	protected void bajaCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		clienteNegocio.bajaCliente(request.getParameter("txtDni"));
		usuarioNegocio.bajaUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
		
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminstrador?cargarClientes=1");   
        rd.forward(request, response);
	}

	protected void altaCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		System.out.println(clienteNegocio.altaCliente(request.getParameter("txtDni")));
		System.out.println(usuarioNegocio.altaUsuario(Integer.parseInt(request.getParameter("txtIdUsuario"))));;
		
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminstrador?cargarClientes=1");   
        rd.forward(request, response);
	}

	protected void btnEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = new Cliente();
		cliente.setId(Integer.parseInt(request.getParameter("txtId")));
		ClienteNegocioImpl clienteNegocioImpl = new ClienteNegocioImpl();
		request.getSession().setAttribute("cliente", clienteNegocioImpl.obtenerCliente(cliente));;
		TipoNegocioImpl tipoNegocio = new TipoNegocioImpl();
		request.getSession().setAttribute("selectProvincias", tipoNegocio.getTipos("Provincias"));
		request.getSession().setAttribute("selectNacionalidades", tipoNegocio.getTipos("Nacionalidades"));
		request.getSession().setAttribute("selectLocalidades", tipoNegocio.getTipos("Localidades"));
		request.getSession().setAttribute("selectTipoCuenta", tipoNegocio.getTipos("TipoCuenta"));
		request.getSession().setAttribute("selectGeneros", tipoNegocio.getTipos("Genero"));
		
		RequestDispatcher rd = request.getRequestDispatcher("EditarCliente.jsp");
		rd.forward(request, response);
	}

	//DO POST
	protected void crearUsuarioCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		Usuario usuario = new Usuario();
		Tipo tipoRol = new Tipo();
		
		tipoRol.setId(2);
		usuario.setTipoRol(tipoRol);
		usuario.setNombreUsuario(request.getParameter("txtNombreUsuario"));
		usuario.setEmail(request.getParameter("txtEmail"));
		usuario.setPassword(request.getParameter("txtPassword"));
		
		try {
			request.setAttribute("usuarioClienteInsertado", usuarioNegocio.crearUsuario(usuario));
		} catch (SQLException e) {
			request.setAttribute("usuarioClienteError", e.getMessage());
		}
		
		int idUsuario = usuarioNegocio.buscarID(request.getParameter("txtNombreUsuario"));
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cliente cliente = new Cliente();
		
		usuario.setId(idUsuario);
		cliente.setUsuario(usuario);
		cliente.setNombre(request.getParameter("txtNombre"));
		cliente.setApellido(request.getParameter("txtApellido"));
		cliente.setDni(request.getParameter("txtDni"));
		cliente.setCuil(request.getParameter("txtCuil"));
		cliente.setDireccion(request.getParameter("txtDireccion"));
		Tipo tipolocalidad = new Tipo();
		tipolocalidad.setId(Integer.parseInt(request.getParameter("ddlLocalidad")));
		cliente.setLocalidad(tipolocalidad);
		Tipo tipoProvincia = new Tipo();
		tipoProvincia.setId(Integer.parseInt(request.getParameter("ddlProvincia")));
		cliente.setProvincia(tipoProvincia);
		Tipo tipoNacionalidad = new Tipo();
		tipoNacionalidad.setId(Integer.parseInt(request.getParameter("ddlNacionalidad")));
		cliente.setNacionalidad(tipoNacionalidad);
		cliente.setTelefono(request.getParameter("txtTelefono"));
		Tipo tipoGenero = new Tipo();
		tipoGenero.setId(Integer.parseInt(request.getParameter("ddlGenero")));
		cliente.setGenero(tipoGenero);
		cliente.setFechaNacimiento(Date.valueOf(request.getParameter("txtFechaNacimiento")));
		
		try {
			request.setAttribute("clienteInsertado", clienteNegocio.crearCliente(cliente));
		} catch (SQLException e) {
			request.setAttribute("clienteError", e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/CrearCuentas.jsp");   
        rd.forward(request, response);
	}
	
	protected void cargarClientesBuscador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		request.getSession().setAttribute("clientes", clienteNegocio.obtenerClientes(request.getParameter("txtBuscarCliente")));
		RequestDispatcher rd = request.getRequestDispatcher("Clientes.jsp");
		rd.forward(request, response);
	}

	protected void btnEditarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		
		Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
		Usuario usuario = new Usuario();
		
		usuario.setEmail(request.getParameter("txtEmail"));
		usuario.setId(cliente.getUsuario().getId());
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		
		cliente.setNombre(request.getParameter("txtNombre"));
		cliente.setApellido(request.getParameter("txtApellido"));
		cliente.setDni(request.getParameter("txtDni"));
		cliente.setCuil(request.getParameter("txtCuil"));
		cliente.setDireccion(request.getParameter("txtDireccion"));
		Tipo tipolocalidad = new Tipo();
		
		tipolocalidad.setId(Integer.parseInt(request.getParameter("ddlLocalidad")));
		cliente.setLocalidad(tipolocalidad);
		Tipo tipoProvincia = new Tipo();
		
		tipoProvincia.setId(Integer.parseInt(request.getParameter("ddlProvincia")));
		cliente.setProvincia(tipoProvincia);
		Tipo tipoNacionalidad = new Tipo();
		
		tipoNacionalidad.setId(Integer.parseInt(request.getParameter("ddlNacionalidad")));
		cliente.setNacionalidad(tipoNacionalidad);
		cliente.setTelefono(request.getParameter("txtTelefono"));
		Tipo tipoGenero = new Tipo();
		
		tipoGenero.setId(Integer.parseInt(request.getParameter("ddlGenero")));
		cliente.setGenero(tipoGenero);
		cliente.setFechaNacimiento(Date.valueOf(request.getParameter("txtFechaNacimiento")));
		request.setAttribute("clienteEditado", usuarioNegocio.editarUsuario(usuario));
		request.setAttribute("clienteEditado", clienteNegocio.editarCliente(cliente));
		request.getSession().setAttribute("cliente", cliente);
		
		RequestDispatcher rd = request.getRequestDispatcher("EditarCliente.jsp");
		rd.forward(request, response);
	}
	protected void cambiarPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		request.setAttribute("passActualizada", usuarioNegocio.cambiarPassUsuario(Integer.parseInt(request.getSession().getAttribute("idUsuario").toString()), request.getParameter("txtPassword").toString()));
		RequestDispatcher rd = request.getRequestDispatcher("CambiarPass.jsp");   
        rd.forward(request, response);
	}
}
