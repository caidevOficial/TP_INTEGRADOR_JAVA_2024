package servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Prestamo;
import entidades.Tipo;
import negocioimpl.CuentaNegocioImpl;
import negocioimpl.MovimientoNegocioImpl;
import negocioimpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class servletAdminPrestamos
 */
@WebServlet(description = "Servlet para manejar los prestamos desde el usuario Admin", urlPatterns = { "/servletAdminPrestamos" })
public class servletAdminPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAdminPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("cargarPrestamos") != null) {
			cargarPrestamos(request, response);
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnPrestamosImporte") != null) {
			informePrestamos(request, response);
			return;
		}
		if (request.getParameter("btnBuscarPrestamo") != null) {
			cargarPrestamosBuscador(request, response);
			return;
		}
		
	}
	
	// DO-GET
	protected void cargarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		request.getSession().setAttribute("prestamos", prestamoNegocio.getPrestamosPendientes());
		RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
		rd.forward(request, response);
	}
	
	
	// DO-POST
	protected void informePrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocioImpl prestamoNegocioImpl = new PrestamoNegocioImpl();
		request.setAttribute("informe", prestamoNegocioImpl.informe(Integer.parseInt(request.getParameter("ddlMes"))));
		RequestDispatcher rd = request.getRequestDispatcher("Informes.jsp");
		rd.forward(request, response);
	}
	
	protected void cargarPrestamosBuscador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		request.getSession().setAttribute("prestamos", prestamoNegocio.getPrestamosPendientes(request.getParameter("txtBuscarPrestamo")));
		RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
		rd.forward(request, response);
	}
	
	protected void rechazarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		Prestamo prestamo = new Prestamo();
		prestamo.setId(Integer.parseInt(request.getParameter("txtId")));
		prestamoNegocio.rechazar(prestamo);
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminPrestamos?cargarPrestamos");   
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
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminPrestamos?cargarPrestamos=1");   
        rd.forward(request, response);
	}

}
