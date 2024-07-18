package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Usuario;
import negocioimpl.ClienteNegocioImpl;
import negocioimpl.CuentaNegocioImpl;
import negocioimpl.MovimientoNegocioImpl;

/**
 * Servlet implementation class servletClienteCuentas
 */
@WebServlet("/servletClienteCuentas")
public class servletClienteCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletClienteCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("cargarSelects") != null) {
			cargarCuentas(request, response);
			return;
		}
		if (request.getParameter("btnBuscarMovimientos") != null) {
			cargarMovimientosBuscar(request, response);
			return;
		}
		if (request.getParameter("btnMostrarMovimientos") != null) {
			cargarMovimientos(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	
	protected void cargarMovimientosBuscar(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
		Cuenta cuenta = new Cuenta();
		cuenta.setId(Integer.parseInt(request.getSession().getAttribute("selected").toString()));
		request.getSession().setAttribute("movimientos", movimientoNegocio.obtenerMovimientos(cuenta, request.getParameter("txtBuscar")));
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
