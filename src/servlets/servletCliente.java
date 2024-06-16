package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Couta;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Prestamo;
import entidad.Tipo;
import entidad.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CoutaNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;
import negocioImpl.TipoNegocioImpl;

/**
 * Servlet implementation class servletCliente
 */
@WebServlet("/servletCliente")
public class servletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public servletCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("cargarSelects") != null) {
			cargarCuentas(request, response);
			return;
		}
		if (request.getParameter("cargarSelectsPrestamo") != null) {
			cargarPrestamo(request, response);
			return;
		}
		if (request.getParameter("cargarSelectsPrestamoPagar") != null) {
			cargarPrestamoPagar(request, response);
			return;
		}
		if (request.getParameter("btnSeleccionarCuenta") != null) {
			cargarCoutasPrestamo(request, response);
			return;
		}
		if (request.getParameter("cargarPerfil") != null) {
			cargarPerfil(request, response);
			return;
		}
		if (request.getParameter("btnMostrarMovimientos") != null) {
			cargarMovimientos(request, response);
			return;
		}
		if (request.getParameter("btnPedirPrestamo") != null) {
			pedirPrestamo(request, response);
			return;
		}
		if (request.getParameter("btnBuscarMovimientos") != null) {
			cargarMovimientosBuscar(request, response);
			return;
		}
		if (request.getParameter("transferir") != null) {
			cargarTranferir(request, response);
			return;
		}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		if (request.getParameter("btnPagar") != null) {
			pagarPrestamo(request, response);
			return;
		}
		
		if (request.getParameter("btnTransferir") != null) {
			transferir(request, response);
			return;
		}
	}
	
	protected void transferir(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		Cuenta cuenta = new Cuenta();
		cuenta.setId(Integer.parseInt(request.getParameter("ddlCuenta")));
		Cuenta cuentaATransferir = new Cuenta();
		cuentaATransferir.setCbu(request.getParameter("txtCBU"));
		cuentaATransferir = cuentaNegocio.obtenerCBU(cuentaATransferir);
		if (cuentaATransferir.getId() != 0) {
			try {
				cuentaNegocio.actualizarSaldoRestar(cuenta, BigDecimal.valueOf(Long.valueOf(request.getParameter("txtMonto"))));
				cuentaNegocio.actualizarSaldoSumar(cuentaATransferir, BigDecimal.valueOf(Long.valueOf(request.getParameter("txtMonto"))));
				MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
				Movimiento movimiento = new Movimiento();
				movimiento.setCuenta(cuenta);
				movimiento.setConcepto(new Tipo(Integer.parseInt(request.getParameter("ddlConcepto"))));
				movimiento.setTipoMovimiento(new Tipo(4));
				movimiento.setMonto(BigDecimal.valueOf(Long.valueOf(request.getParameter("txtMonto"))).negate());
				movimientoNegocio.movimientoBanco(movimiento);
				movimiento.setCuenta(cuentaATransferir);
				movimiento.setConcepto(new Tipo(Integer.parseInt(request.getParameter("ddlConcepto"))));
				movimiento.setTipoMovimiento(new Tipo(4));
				movimiento.setMonto(BigDecimal.valueOf(Long.valueOf(request.getParameter("txtMonto"))));
				movimientoNegocio.movimientoBanco(movimiento);
				request.setAttribute("insertado", movimientoNegocio.movimientoBanco(movimiento));
			} catch (Exception e) {
				request.setAttribute("errorTransferir", e.getMessage());
			}
			
		} else {
			request.setAttribute("errorTransferir", "CBU no encontrado");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");   
        rd.forward(request, response);
	}
	
	protected void pagarPrestamo(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		CoutaNegocioImpl coutaNegocio = new CoutaNegocioImpl();
		MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		Prestamo prestamo = (Prestamo)request.getSession().getAttribute("prestamo");
		try {
			cuentaNegocio.actualizarSaldoRestar(prestamo.getCuenta(), prestamo.getMontoCuota());
			Movimiento movimiento = new Movimiento();
			movimiento.setCuenta(prestamo.getCuenta());
			movimiento.setTipoMovimiento(new Tipo(3));
			movimiento.setConcepto(new Tipo(5));
			movimiento.setMonto(prestamo.getMontoCuota().negate());
			movimientoNegocio.movimientoBanco(movimiento);
			Couta couta = new Couta();
			couta.setId(Integer.parseInt(request.getParameter("ddlCoutas")));
			request.setAttribute("coutaPaga", coutaNegocio.coutaPaga(couta));;
		} catch (SQLException e) {
			request.setAttribute("errorPago", e.getMessage());
		}
		request.getSession().setAttribute("coutas", null);
		cargarPrestamoPagar(request, response);
	}
	
	private void cargarCoutasPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CoutaNegocioImpl coutasNegocioImpl = new CoutaNegocioImpl();
		int prestamoSeleccionado = Integer.valueOf(request.getParameter("ddlPrestamos"));
		request.getSession().setAttribute("selected", prestamoSeleccionado);
		ArrayList<Prestamo> prestamos = (ArrayList<Prestamo>)request.getSession().getAttribute("prestamos");
		Prestamo prestamo = new Prestamo();
		for(Prestamo prestamoFor : prestamos) {
			if (prestamoFor.getId() == prestamoSeleccionado) {
				prestamo = prestamoFor;
			}
		}
		request.getSession().setAttribute("coutas", coutasNegocioImpl.obtenerCoutas(prestamo));
		request.getSession().setAttribute("prestamo", prestamo);
		RequestDispatcher rd = request.getRequestDispatcher("/PagarPrestamo.jsp");   
        rd.forward(request, response);
	}
	
	private void cargarPrestamoPagar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		Usuario	usuario = new Usuario();
		usuario = (Usuario)request.getSession().getAttribute("usuario");
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cliente cliente = new Cliente();
		cliente.setId(clienteNegocio.buscarId(usuario));
		request.getSession().setAttribute("prestamos", prestamoNegocio.getPrestamosAprobados(cliente));
		RequestDispatcher rd = request.getRequestDispatcher("/PagarPrestamo.jsp");   
        rd.forward(request, response);
	}
	
	protected void cargarTranferir(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		TipoNegocioImpl tipoNegocio = new TipoNegocioImpl();
		request.getSession().setAttribute("concepto", tipoNegocio.getTipos("Concepto"));
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		Usuario	usuario = new Usuario();
		usuario = (Usuario)request.getSession().getAttribute("usuario");
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cliente cliente = new Cliente();
		cliente.setId(clienteNegocio.buscarId(usuario));
		request.getSession().setAttribute("cuentas", cuentaNegocio.obtenerCuentas(cliente));
		RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");   
        rd.forward(request, response);
	}

	
	protected void cargarMovimientosBuscar(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
		Cuenta cuenta = new Cuenta();
		cuenta.setId(Integer.parseInt(request.getSession().getAttribute("selected").toString()));
		request.getSession().setAttribute("movimientos", movimientoNegocio.obtenerMovimientos(cuenta, request.getParameter("txtBuscar")));
		RequestDispatcher rd = request.getRequestDispatcher("/Cuenta.jsp");   
        rd.forward(request, response);
	}
	
	protected void pedirPrestamo(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		Prestamo prestamo = new Prestamo();
		prestamo.setCantidadCuotas(Integer.parseInt(request.getParameter("ddlCoutas")));
		Cuenta cuenta = new Cuenta();
		cuenta.setId(Integer.parseInt(request.getParameter("ddlCuenta")));
		prestamo.setCuenta(cuenta);
		prestamo.setMontoSolicitado(BigDecimal.valueOf(Long.parseLong(request.getParameter("txtMonto"))));
		long montoCuota = (long)((Long.parseLong(request.getParameter("txtMonto")) / Long.parseLong(request.getParameter("ddlCoutas"))) * (long)1.12);
		prestamo.setMontoCuota(BigDecimal.valueOf(montoCuota));
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		request.setAttribute("insertado", prestamoNegocio.insertarPrestamo(prestamo));
		// cargarSelectsPrestamo
		RequestDispatcher rd = request.getRequestDispatcher("/PedirPrestamo.jsp");   
        rd.forward(request, response);
	}
	
	protected void cargarPerfil(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		Usuario	usuario = new Usuario();
		usuario = (Usuario)request.getSession().getAttribute("usuario");
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cliente cliente = new Cliente();
		cliente.setId(clienteNegocio.buscarId(usuario));
		request.getSession().setAttribute("cliente", clienteNegocio.obtenerCliente(cliente));
		RequestDispatcher rd = request.getRequestDispatcher("/MiPerfil.jsp");   
        rd.forward(request, response);
	}
	
	protected void cargarPrestamo(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		Usuario	usuario = new Usuario();
		usuario = (Usuario)request.getSession().getAttribute("usuario");
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cliente cliente = new Cliente();
		cliente.setId(clienteNegocio.buscarId(usuario));
		request.getSession().setAttribute("cuentas", cuentaNegocio.obtenerCuentas(cliente));
		RequestDispatcher rd = request.getRequestDispatcher("/PedirPrestamo.jsp");   
        rd.forward(request, response);
	}
	
	protected void cargarCuentas(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		Usuario	usuario = new Usuario();
		usuario = (Usuario)request.getSession().getAttribute("usuario");
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cliente cliente = new Cliente();
		cliente.setId(clienteNegocio.buscarId(usuario));
		request.getSession().setAttribute("cuentas", cuentaNegocio.obtenerCuentas(cliente));
		RequestDispatcher rd = request.getRequestDispatcher("/Cuenta.jsp");   
        rd.forward(request, response);
	}
	
	protected void cargarMovimientos(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
		Cuenta cuenta = new Cuenta();
		cuenta.setId(Integer.parseInt(request.getParameter("ddlCuenta")));
		request.getSession().setAttribute("selected", Integer.parseInt(request.getParameter("ddlCuenta")));
		request.getSession().setAttribute("movimientos", movimientoNegocio.obtenerMovimientos(cuenta));
		RequestDispatcher rd = request.getRequestDispatcher("/Cuenta.jsp");   
        rd.forward(request, response);
	}
}
