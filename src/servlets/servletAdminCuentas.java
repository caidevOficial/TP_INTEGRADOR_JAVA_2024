package servlets;

import java.io.IOException;
import java.math.BigDecimal;
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
import entidades.Tipo;
import negocioimpl.ClienteNegocioImpl;
import negocioimpl.CuentaNegocioImpl;
import negocioimpl.MovimientoNegocioImpl;
import negocioimpl.TipoNegocioImpl;

/**
 * Servlet implementation class servletAdminCuentas
 */
@WebServlet("/servletAdminCuentas")
public class servletAdminCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAdminCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("cargarCuentas") != null) {
			cargarCuentas(request, response);
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
		if(request.getParameter("btnEditarCuenta") != null) {
			btnEditarCuenta(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnCrearCuenta") != null) {
			crearCuenta(request, response);
			return;
		}
		if (request.getParameter("btnBuscarCuenta") != null) {
			cargarCuentasBuscador(request, response);
			return;
		}
		if (request.getParameter("editarCuenta") != null) {
			editarCuenta(request, response);
			return;
		}
	}
	
	//DO GET
	protected void cargarCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		//TERMINAR
		
		request.getSession().setAttribute("cuentas", cuentaNegocio.obtenerCuentas());
		
		RequestDispatcher rd = request.getRequestDispatcher("Cuentas.jsp");
		rd.forward(request, response);
	}
	
	protected void altaCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TERMINAR
	}
	
	protected void bajaCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TERMINAR
	}

	protected void btnEditarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TERMINAR
	}

	//DO POST
	protected void crearCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TERMINAR
	}

	protected void cargarCuentasBuscador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TERMINAR
	}

	protected void editarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TERMINAR
	}
}
