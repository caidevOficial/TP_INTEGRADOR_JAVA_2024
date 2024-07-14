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
import entidades.Tipo;
import negocioimpl.ClienteNegocioImpl;
import negocioimpl.CuentaNegocioImpl;
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
		if (request.getParameter("cargarSelects") != null) {
			cargarSelects(request, response);
			return;
		}
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
	
	protected void altaCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		cuentaNegocio.altaCuenta(Integer.parseInt(request.getParameter("txtId")));
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminstrador?cargarCuentas=1");   
        rd.forward(request, response);
	}
	
	protected void bajaCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		cuentaNegocio.bajaCuenta(Integer.parseInt(request.getParameter("txtId")));
		RequestDispatcher rd = request.getRequestDispatcher("/servletAdminstrador?cargarCuentas=1");   
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

	//DO POST
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
			cuenta.setId(IdCuenta);
		} catch (SQLException e) {
			request.setAttribute("cuentaError", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/CrearCuentas.jsp");   
        rd.forward(request, response);
	}

	protected void cargarCuentasBuscador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		request.getSession().setAttribute("cuentas", cuentaNegocio.obtenerCuentas(request.getParameter("txtBuscarCuenta")));
		RequestDispatcher rd = request.getRequestDispatcher("Cuentas.jsp");
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
	
	private static String generateNumeroCuenta() {
    	char digits[] = {'0','1','2','3','4','5','6','7','8','9'};
        StringBuilder result = new StringBuilder();
        for(int i=0; i<13; i++) {
            result.append(digits[(int)Math.floor(Math.random() * 10)]);
        }
        return result.toString();
    }
    
	private static String generateCBU() {
    	char digits[] = {'0','1','2','3','4','5','6','7','8','9'};
        StringBuilder result = new StringBuilder();
        for(int i=0; i<22; i++) {
            result.append(digits[(int)Math.floor(Math.random() * 10)]);
        }
        return result.toString();
    }
}
