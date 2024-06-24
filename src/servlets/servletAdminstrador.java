package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Prestamo;
import entidades.Tipo;
import entidades.Usuario;
import negocioimpl.ClienteNegocioImpl;
import negocioimpl.CuentaNegocioImpl;
import negocioimpl.MovimientoNegocioImpl;
import negocioimpl.PrestamoNegocioImpl;
import negocioimpl.TipoNegocioImpl;
import negocioimpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class servletAdminstrador
 */
@WebServlet("/servletAdminstrador")
public class servletAdminstrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAdminstrador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("cargarSelects") != null) {
			cargarSelects(request, response);
			return;
		}
		if (request.getParameter("cargarClientes") != null) {
			cargarClientes(request, response);
			return;
		}
		if (request.getParameter("cargarCuentas") != null) {
			cargarCuentas(request, response);
			return;
		}
		if (request.getParameter("cargarPrestamos") != null) {
			cargarPrestamos(request, response);
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
		if(request.getParameter("btnAltaCuenta") != null) {
			altaCuenta(request, response);
			return;
		}
		if(request.getParameter("btnEliminarCuenta") != null) {
			bajaCuenta(request, response);
			return;
		}
		if(request.getParameter("btnRechazar") != null) {
			rechazarPrestamo(request, response);
			return;
		}
		if(request.getParameter("btnAceptar") != null) {
			aprobarPrestamo(request, response);
			return;
		}
		if(request.getParameter("btnEditar") != null) {
			btnEditar(request, response);
			return;
		}
		if(request.getParameter("btnEditarCuenta") != null) {
			btnEditarCuenta(request, response);
			return;
		}
		if (request.getParameter("cargarInformes") != null) {
			cargarInformes(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnCrearAdmin") != null) {
			crearUsuarioAdministrador(request, response);
			return;
		}
		if (request.getParameter("btnCrearCliente") != null) {
			crearUsuarioCliente(request, response);
			return;
		}
		if (request.getParameter("btnCrearCuenta") != null) {
			crearCuenta(request, response);
			return;
		}
		if (request.getParameter("btnCambiarPass") != null) {
			cambiarPass(request, response);
			return;
		}
		if (request.getParameter("btnBuscarCliente") != null) {
			cargarClientesBuscador(request, response);
			return;
		}
		if (request.getParameter("btnBuscarCuenta") != null) {
			cargarCuentasBuscador(request, response);
			return;
		}
		if (request.getParameter("btnBuscarPrestamo") != null) {
			cargarPrestamosBuscador(request, response);
			return;
		}
		if (request.getParameter("btnEditarCliente") != null) {
			btnEditarCliente(request, response);
			return;
		}
		if (request.getParameter("editarCuenta") != null) {
			editarCuenta(request, response);
			return;
		}
		if (request.getParameter("btnBuscarInformeImporteTotal") != null) {
			buscarImporteTotal(request, response);
			return;
		}
		if (request.getParameter("btnPrestamosImporte") != null) {
			informePrestamos(request, response);
			return;
		}
	}
	
	protected void informePrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocioImpl prestamoNegocioImpl = new PrestamoNegocioImpl();
		request.setAttribute("informe", prestamoNegocioImpl.informe(Integer.parseInt(request.getParameter("ddlMes"))));
		RequestDispatcher rd = request.getRequestDispatcher("Informes.jsp");
		rd.forward(request, response);
	}
	
	protected void buscarImporteTotal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovimientoNegocioImpl movimientoNegocioImpl = new MovimientoNegocioImpl();
		request.setAttribute("importeTotal", movimientoNegocioImpl.importTotal(new Tipo(Integer.parseInt(request.getParameter("ddlTipoMovimiento"))), Integer.parseInt(request.getParameter("ddlMes"))));
		request.setAttribute("selected", Integer.parseInt(request.getParameter("ddlTipoMovimiento")));
		RequestDispatcher rd = request.getRequestDispatcher("Informes.jsp");
		rd.forward(request, response);
	}
	
	protected void cargarInformes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TipoNegocioImpl tipoNegocio = new TipoNegocioImpl();
		request.getSession().setAttribute("tipoMovimiento", tipoNegocio.getTipos("TipoMovimiento"));
		RequestDispatcher rd = request.getRequestDispatcher("Informes.jsp");
		rd.forward(request, response);
	}
	
	
	protected void editarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cuenta cuenta = (Cuenta)request.getSession().getAttribute("cuenta");
		cuenta.setCbu(request.getParameter("txtCBU"));
		cuenta.setNumeroCuenta(request.getParameter("txtNumeroCuenta"));
		cuenta.setSaldo(BigDecimal.valueOf(Long.parseLong(request.getParameter("txtSaldo"))));
		cuenta.setTipoCuenta(new Tipo(Integer.parseInt(request.getParameter("ddlTipoCuenta"))));
		CuentaNegocioImpl cuentaNegocioImpl = new CuentaNegocioImpl();
		try {
			request.setAttribute("cuentaEditada", cuentaNegocioImpl.editarCuenta(cuenta));
		} catch (SQLException e) {
			request.setAttribute("cuentaError", e.getMessage());
		}
		
		request.getSession().setAttribute("cuenta", cuenta);
		RequestDispatcher rd = request.getRequestDispatcher("EditarCuenta.jsp");
		rd.forward(request, response);
	}
	
	protected void btnEditarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cuenta cuenta = new Cuenta();
		cuenta.setCbu(request.getParameter("txtCBU"));
		CuentaNegocioImpl cuentaNegocioImpl = new CuentaNegocioImpl();
		request.getSession().setAttribute("cuenta", cuentaNegocioImpl.obtenerCBU(cuenta));
		TipoNegocioImpl tipoNegocio = new TipoNegocioImpl();
		request.getSession().setAttribute("selectTipoCuenta", tipoNegocio.getTipos("TipoCuenta"));
		RequestDispatcher rd = request.getRequestDispatcher("EditarCuenta.jsp");
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
	
	protected void cargarPrestamosBuscador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		request.getSession().setAttribute("prestamos", prestamoNegocio.getPrestamosPendientes(request.getParameter("txtBuscarPrestamo")));
		RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
		rd.forward(request, response);
	}
	
	protected void cargarCuentasBuscador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		request.getSession().setAttribute("cuentas", cuentaNegocio.obtenerCuentas(request.getParameter("txtBuscarCuenta")));
		RequestDispatcher rd = request.getRequestDispatcher("Cuentas.jsp");
		rd.forward(request, response);
	}
	
	protected void cargarClientesBuscador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		request.getSession().setAttribute("clientes", clienteNegocio.obtenerClientes(request.getParameter("txtBuscarCliente")));
		RequestDispatcher rd = request.getRequestDispatcher("Clientes.jsp");
		rd.forward(request, response);
	}

	protected void cambiarPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		request.setAttribute("passActualizada", usuarioNegocio.cambiarPassUsuario(Integer.parseInt(request.getSession().getAttribute("idUsuario").toString()), request.getParameter("txtPassword").toString()));
		RequestDispatcher rd = request.getRequestDispatcher("CambiarPass.jsp");   
        rd.forward(request, response);
	}
	
	protected void bajaCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		cuentaNegocio.bajaCuenta(Integer.parseInt(request.getParameter("txtId")));
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminstrador?cargarCuentas=1");   
        rd.forward(request, response);
	}
	
	protected void altaCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		cuentaNegocio.altaCuenta(Integer.parseInt(request.getParameter("txtId")));
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminstrador?cargarCuentas=1");   
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
	
	protected void rechazarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		Prestamo prestamo = new Prestamo();
		prestamo.setId(Integer.parseInt(request.getParameter("txtId")));
		prestamoNegocio.rechazar(prestamo);
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminstrador?cargarPrestamos");   
        rd.forward(request, response);
	}
	
	protected void aprobarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		Prestamo prestamo = new Prestamo();
		prestamo.setId(Integer.parseInt(request.getParameter("txtId")));
		prestamoNegocio.aprobar(prestamo);
		MovimientoNegocioImpl movimientoNegocio =  new MovimientoNegocioImpl();
		Movimiento movimiento = new Movimiento();
		Cuenta cuenta = new Cuenta();
		cuenta.setId(Integer.parseInt(request.getParameter("txtIdCuenta")));
		movimiento.setCuenta(cuenta);
		movimiento.setTipoMovimiento(new Tipo(2));
		movimiento.setConcepto(new Tipo(5));
		movimiento.setMonto(BigDecimal.valueOf(Long.valueOf(request.getParameter("txtMonto"))));
		movimientoNegocio.movimientoBanco(movimiento);
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		cuentaNegocio.actualizarSaldoSumar(cuenta, BigDecimal.valueOf(Long.valueOf(request.getParameter("txtMonto"))));
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminstrador?cargarPrestamos=1");   
        rd.forward(request, response);
	}
	
	//
	// Do Get
	//
	protected void cargarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		request.getSession().setAttribute("prestamos", prestamoNegocio.getPrestamosPendientes());
		RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
		rd.forward(request, response);
	}

	protected void cargarSelects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TipoNegocioImpl tipoNegocio = new TipoNegocioImpl();
		request.getSession().setAttribute("selectProvincias", tipoNegocio.getTipos("Provincias"));
		request.getSession().setAttribute("selectNacionalidades", tipoNegocio.getTipos("Nacionalidades"));
		request.getSession().setAttribute("selectLocalidades", tipoNegocio.getTipos("Localidades"));
		request.getSession().setAttribute("selectTipoCuenta", tipoNegocio.getTipos("TipoCuenta"));
		request.getSession().setAttribute("selectGeneros", tipoNegocio.getTipos("Genero"));
		RequestDispatcher rd = request.getRequestDispatcher("/CrearCuentas.jsp");   
        rd.forward(request, response);
	}
	
	protected void cargarCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		request.getSession().setAttribute("cuentas", cuentaNegocio.obtenerCuentas());
		RequestDispatcher rd = request.getRequestDispatcher("Cuentas.jsp");
		rd.forward(request, response);
	}
	
	protected void cargarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		request.getSession().setAttribute("clientes", clienteNegocio.obtenerClientes());
		RequestDispatcher rd = request.getRequestDispatcher("Clientes.jsp");
		rd.forward(request, response);
	}
	
	protected void crearUsuarioAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		Usuario usuario = new Usuario();
		Tipo tipoRol = new Tipo();
		tipoRol.setId(1);
		usuario.setTipoRol(tipoRol);
		usuario.setNombreUsuario(request.getParameter("txtNombreUsuario"));
		usuario.setEmail(request.getParameter("txtEmail"));
		usuario.setPassword(request.getParameter("txtPassword"));
		try {
			request.setAttribute("usuarioInsertado", usuarioNegocio.crearUsuario(usuario));
		} catch (SQLException e) {
			request.setAttribute("usuarioError", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/CrearCuentas.jsp");   
        rd.forward(request, response);
	}
	
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
	
	protected void crearCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cuenta cuenta = new Cuenta();
		Tipo tipoCuenta = new Tipo();
		tipoCuenta.setId(Integer.parseInt(request.getParameter("ddlTipoCuenta")));
		cuenta.setTipoCuenta(tipoCuenta);
		Cliente cliente = new Cliente();
		cliente.setId(clienteNegocio.buscarId(request.getParameter("txtDniCliente")));
		cuenta.setCliente(cliente);
		cuenta.setNumeroCuenta(generateNumeroCuenta());
		cuenta.setCbu(generateCBU());
		try {
			request.setAttribute("cuentaInsertada", cuentaNegocio.crearCuenta(cuenta));
			int IdCuenta = cuentaNegocio.ultimoId();
			MovimientoNegocioImpl movimientoNegocio =  new MovimientoNegocioImpl();
			Movimiento movimiento = new Movimiento();
			cuenta.setId(IdCuenta);
			movimiento.setCuenta(cuenta);
			movimiento.setTipoMovimiento(new Tipo(1));
			movimiento.setConcepto(new Tipo(4));
			movimiento.setMonto(BigDecimal.valueOf(10000));
			movimientoNegocio.movimientoBanco(movimiento);
		} catch (SQLException e) {
			request.setAttribute("cuentaError", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/CrearCuentas.jsp");   
        rd.forward(request, response);
	}
	
	public static String generateNumeroCuenta() {
    	char digits[] = {'0','1','2','3','4','5','6','7','8','9'};
        StringBuilder result = new StringBuilder();
        for(int i=0; i<13; i++) {
            result.append(digits[(int)Math.floor(Math.random() * 10)]);
        }
        return result.toString();
    }
    
    public static String generateCBU() {
    	char digits[] = {'0','1','2','3','4','5','6','7','8','9'};
        StringBuilder result = new StringBuilder();
        for(int i=0; i<22; i++) {
            result.append(digits[(int)Math.floor(Math.random() * 10)]);
        }
        return result.toString();
    }
}
