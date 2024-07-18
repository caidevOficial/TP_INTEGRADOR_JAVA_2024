package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Tipo;
import entidades.Usuario;
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
		}//SERVLET ADMINCLIENTES
		if (request.getParameter("cargarClientes") != null) {
			cargarClientes(request, response);
			return;
		}//SERVLET ADMINCUENTAS
		if (request.getParameter("cargarCuentas") != null) {
			cargarCuentas(request, response);
			return;
		}//SERVLET ADMINPRESTAMOS
		if (request.getParameter("cargarPrestamos") != null) {
			cargarPrestamos(request, response);
			return;
		}//SERVLET ADMINCLIENTES
		if(request.getParameter("btnEliminar") != null) {
			bajaCliente(request, response);
			return;
		}//SERVLET ADMINCLIENTES
		if(request.getParameter("btnAlta") != null) {
			altaCliente(request, response);
			return;
		}//SERVLET ADMINCUENTAS
		if(request.getParameter("btnAltaCuenta") != null) {
			altaCuenta(request, response);
			return;
		}//SERVLET ADMINCUENTAS
		if(request.getParameter("btnEliminarCuenta") != null) {
			bajaCuenta(request, response);
			return;
		}//SERVLET ADMINPRESTAMOS
		if(request.getParameter("btnRechazar") != null) {
			rechazarPrestamo(request, response);
			return;
		}//SERVLET ADMINPRESTAMOS
		if(request.getParameter("btnAceptar") != null) {
			aprobarPrestamo(request, response);
			return;
		}//SERVLET ADMINCLIENTES
		if(request.getParameter("btnEditar") != null) {
			btnEditar(request, response);
			return;
		}//SERVLET ADMINCUENTAS
		if(request.getParameter("btnEditarCuenta") != null) {
			btnEditarCuenta(request, response);
			return;
		}//SERVLET ADMININFORMES
		if (request.getParameter("cargarInformes") != null) {
			cargarInformes(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnCrearAdmin") != null) {
			crearUsuarioAdministrador(request, response);
			return;
		}//SERVLET ADMINCLIENTES
		if (request.getParameter("btnCrearCliente") != null) {
			crearUsuarioCliente(request, response);
			return;
		}//SERVLET ADMINCUENTAS
		if (request.getParameter("btnCrearCuenta") != null) {
			crearCuenta(request, response);
			return;
		}
		if (request.getParameter("btnCambiarPass") != null) {
			cambiarPass(request, response);
			return;
		}//SERVLET ADMINCLIENTES
		if (request.getParameter("btnBuscarCliente") != null) {
			cargarClientesBuscador(request, response);
			return;
		}//SERVLET ADMINCUENTAS
		if (request.getParameter("btnBuscarCuenta") != null) {
			cargarCuentasBuscador(request, response);
			return;
		}//SERVLET ADMINPRESTAMOS
		if (request.getParameter("btnBuscarPrestamo") != null) {
			cargarPrestamosBuscador(request, response);
			return;
		}//SERVLET ADMINCLIENTES
		if (request.getParameter("btnEditarCliente") != null) {
			btnEditarCliente(request, response);
			return;
		}//SERVLET ADMINCUENTAS
		if (request.getParameter("editarCuenta") != null) {
			editarCuenta(request, response);
			return;
		}//SERVLET ADMINPRESTAMOS
		if (request.getParameter("btnBuscarInformeImporteTotal") != null) {
			buscarImporteTotal(request, response);
			return;
		}//SERVLET ADMINPRESTAMOS
		if (request.getParameter("btnPrestamosImporte") != null) {
			informePrestamos(request, response);
			return;
		}
	}
	
	protected void informePrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminPrestamos?btnPrestamosImporte=1");
		rd.forward(request, response);
	}
	
	protected void buscarImporteTotal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminPrestamos?btnBuscarInformeImporteTotal=1");
		rd.forward(request, response);
	}
	
	protected void cargarInformes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminInformes?cargarInformes=1");
		rd.forward(request, response);
	}
	
	protected void editarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminCuentas?editarCuenta=1");
		rd.forward(request, response);
	}
	
	protected void btnEditarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminCuentas?btnEditarCuenta=1");
		rd.forward(request, response);
	}
	
	protected void btnEditarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminClientes?btnEditarCliente=1");
		rd.forward(request, response);
	}
	
	protected void btnEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminClientes?btnEditar=1");
		rd.forward(request, response);
	}
	
	protected void cargarPrestamosBuscador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminPrestamos?btnPrestamosImporte=1");
		rd.forward(request, response);
	}
	
	protected void cargarCuentasBuscador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminCuentas?btnBuscarCuenta=1");
		rd.forward(request, response);
	}
	
	protected void cargarClientesBuscador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminClientes?btnBuscarCliente=1");
		rd.forward(request, response);
	}

	protected void cambiarPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminClientes?btnCambiarPass=1");   
        rd.forward(request, response);
	}
	
	protected void bajaCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminCuentas?btnEliminarCuenta=1");
		rd.forward(request, response);
	}
	
	protected void altaCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminCuentas?btnAltaCuenta=1");
		rd.forward(request, response);
	}
	
	protected void bajaCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminClientes?btnEliminar=1");   
        rd.forward(request, response);
	}
	
	protected void altaCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminClientes?btnAlta=1");   
        rd.forward(request, response);
	}
	
	protected void rechazarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminPrestamos?btnRechazar=1");   
        rd.forward(request, response);
	}
	
	protected void aprobarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminPrestamos?btnAceptar=1");   
        rd.forward(request, response);
	}
	
	//
	// Do Get
	//
	protected void cargarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminPrestamos?cargarPrestamos=1");
		rd.forward(request, response);
	}

	protected void cargarSelects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminCuentas?cargarSelects=1");   
        rd.forward(request, response);
	}
	
	protected void cargarCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminCuentas?cargarCuentas=1");
		rd.forward(request, response);
	}
	
	protected void cargarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminClientes?cargarClientes=1");
		rd.forward(request, response);
	}
	
	protected void crearUsuarioAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminUsuario?btnCrearAdmin=1");   
        rd.forward(request, response);
	}
	
	protected void crearUsuarioCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminClientes?btnCrearCliente=1");   
        rd.forward(request, response);
	}
	
	protected void crearCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminCuentas?btnCrearCuenta=1");
		rd.forward(request, response);
	}
}
