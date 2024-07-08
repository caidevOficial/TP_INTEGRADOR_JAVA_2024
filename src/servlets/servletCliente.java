package servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Tipo;
import entidades.Usuario;
import negocioimpl.ClienteNegocioImpl;
import negocioimpl.CuentaNegocioImpl;
import negocioimpl.MovimientoNegocioImpl;
import negocioimpl.TipoNegocioImpl;

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
		}//SERVLET CLIENTEPRESTAMO
		if (request.getParameter("cargarSelectsPrestamo") != null) {
			cargarPrestamo(request, response);
			return;
		}//SERVLET CLIENTEPRESTAMO
		if (request.getParameter("cargarSelectsPrestamoPagar") != null) {
			cargarPrestamoPagar(request, response);
			return;
		}//SERVLET CLIENTEPRESTAMO
		if (request.getParameter("btnSeleccionarCuenta") != null) {
			cargarCuotasPrestamo(request, response);
			return;
		}
		if (request.getParameter("cargarPerfil") != null) {
			cargarPerfil(request, response);
			return;
		}//SERVLET CLIENTEPRESTAMO
		if (request.getParameter("btnPedirPrestamo") != null) {
			pedirPrestamo(request, response);
			return;
		}
		
		if (request.getParameter("btnMostrarMovimientos") != null) {
			cargarMovimientos(request, response);
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
		//SERVLET CLIENTEPRESTAMO
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
		RequestDispatcher rd = request.getRequestDispatcher("/servletClientePrestamos?btnPagar=1");   
        rd.forward(request, response);
	}
	
	private void cargarCuotasPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletClientePrestamos?btnSeleccionarCuenta=1");   
        rd.forward(request, response);
	}
	
	private void cargarPrestamoPagar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletClientePrestamos?cargarSelectsPrestamoPagar=1");   
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
		RequestDispatcher rd = request.getRequestDispatcher("/servletClientePrestamos?btnPedirPrestamo=1");   
        rd.forward(request, response);
	}
	
	protected void cargarPerfil(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletClientePerfil?cargarPerfil=1");   
        rd.forward(request, response);
	}
	
	protected void cargarPrestamo(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletClientePrestamos?cargarSelectsPrestamo=1");
		rd.forward(request, response);
	}
	
	protected void cargarCuentas(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletClienteCuentas?cargarSelects=1");   
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
