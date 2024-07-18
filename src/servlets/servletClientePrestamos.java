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

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Cuota;
import entidades.Movimiento;
import entidades.Prestamo;
import entidades.Tipo;
import entidades.Usuario;
import negocioimpl.ClienteNegocioImpl;
import negocioimpl.CuentaNegocioImpl;
import negocioimpl.CuotaNegocioImpl;
import negocioimpl.MovimientoNegocioImpl;
import negocioimpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class servletClientePrestamos
 */
@WebServlet("/servletClientePrestamos")
public class servletClientePrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletClientePrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("cargarSelectsPrestamo") != null) {
			cargarPrestamo(request, response);
			return;
		}
		if (request.getParameter("cargarSelectsPrestamoPagar") != null) {
			cargarPrestamoPagar(request, response);
			return;
		}
		if (request.getParameter("btnSeleccionarCuenta") != null) {
			cargarCuotasPrestamo(request, response);
			return;
		}
		if (request.getParameter("btnPedirPrestamo") != null) {
			pedirPrestamo(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnPagar") != null) {
			pagarPrestamo(request, response);
			return;
		}
	}

	//DO GET
	protected void cargarPrestamo(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		Usuario	usuario = new Usuario();
		
		usuario = (Usuario)request.getSession().getAttribute("usuario");
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
		Cliente cliente = new Cliente();
		
		cliente.setId(clienteNegocio.buscarId(usuario));
		request.getSession().setAttribute("cuentas", cuentaNegocio.obtenerCuentas(cliente));
		
		RequestDispatcher rd = request.getRequestDispatcher("/PedirPrestamo.jsp");   
		System.out.println("Pagina de prestamos cargada");
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

	private void cargarCuotasPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuotaNegocioImpl cuotasNegocioImpl = new CuotaNegocioImpl();
		
		int prestamoSeleccionado = Integer.valueOf(request.getParameter("ddlPrestamos"));
		request.getSession().setAttribute("selected", prestamoSeleccionado);
		
		@SuppressWarnings("unchecked")
		ArrayList<Prestamo> prestamos = (ArrayList<Prestamo>)request.getSession().getAttribute("prestamos");
		System.out.println(prestamos.size());
		Prestamo prestamo = new Prestamo();
		
		for(Prestamo prestamoFor : prestamos) {
			System.out.println(prestamoSeleccionado);
			System.out.println(prestamoFor.getId());
			if (prestamoFor.getId() == prestamoSeleccionado) {
				prestamo = prestamoFor;
			}
		}
		System.out.println(prestamo.getId());
		request.getSession().setAttribute("cuotas", cuotasNegocioImpl.obtenerCuotas(prestamo));
		request.getSession().setAttribute("prestamo", prestamo);
		
		RequestDispatcher rd = request.getRequestDispatcher("/PagarPrestamo.jsp");   
        rd.forward(request, response);
	}

	protected void pedirPrestamo(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		Prestamo prestamo = new Prestamo();
		prestamo.setCantidadCuotas(Integer.parseInt(request.getParameter("ddlCuotas")));
		Cuenta cuenta = new Cuenta();
		
		cuenta.setId(Integer.parseInt(request.getParameter("ddlCuenta")));
		prestamo.setCuenta(cuenta);
		prestamo.setMontoSolicitado(BigDecimal.valueOf(Long.parseLong(request.getParameter("txtMonto"))));
		long montoCuota = (long)((Long.parseLong(request.getParameter("txtMonto")) / Long.parseLong(request.getParameter("ddlCuotas"))) * (long)1.12);
		prestamo.setMontoCuota(BigDecimal.valueOf(montoCuota));
		PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
		
		request.setAttribute("insertado", prestamoNegocio.insertarPrestamo(prestamo));

		RequestDispatcher rd = request.getRequestDispatcher("/PedirPrestamo.jsp");   
        rd.forward(request, response);
	}

	//DO POST
	protected void pagarPrestamo(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		CuotaNegocioImpl cuotaNegocio = new CuotaNegocioImpl();
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
			Cuota cuota = new Cuota();
			System.out.println(request.getParameter("ddlCuotas"));
			cuota.setId(Integer.parseInt(request.getParameter("ddlCuotas")));
			request.setAttribute("cuotaPaga", cuotaNegocio.cuotaPaga(cuota));;
		} catch (SQLException e) {
			request.setAttribute("errorPago", e.getMessage());
		}
		
		request.getSession().setAttribute("cuotas", null);
		cargarPrestamoPagar(request, response);
	}

}
