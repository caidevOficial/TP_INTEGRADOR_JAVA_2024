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
 * Servlet implementation class servletClienteTransferencia
 */
@WebServlet("/servletClienteTransferencia")
public class servletClienteTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletClienteTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("transferir") != null) {
			cargarTranferir(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnTransferir") != null) {
			transferir(request, response);
			return;
		}
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
	
	protected void transferir(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		Cuenta cuenta = new Cuenta();
		cuenta.setId(Integer.parseInt(request.getParameter("ddlCuenta")));
		Cuenta cuentaATransferir = new Cuenta();
		cuentaATransferir.setCbu(request.getParameter("txtCBU"));
		cuentaATransferir = cuentaNegocio.obtenerCBU(cuentaATransferir);
		if (cuentaATransferir.getId() != 0) {
			try {
				boolean movimientoExitoso = false;
				cuentaNegocio.actualizarSaldoRestar(cuenta, BigDecimal.valueOf(Long.valueOf(request.getParameter("txtMonto"))));
				cuentaNegocio.actualizarSaldoSumar(cuentaATransferir, BigDecimal.valueOf(Long.valueOf(request.getParameter("txtMonto"))));
				
				MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
				
				Movimiento movimiento = new Movimiento();
				movimiento.setCuenta(cuenta);
				movimiento.setConcepto(new Tipo(Integer.parseInt(request.getParameter("ddlConcepto"))));
				movimiento.setTipoMovimiento(new Tipo(4));
				movimiento.setMonto(BigDecimal.valueOf(Long.valueOf(request.getParameter("txtMonto"))).negate());
				
				movimientoExitoso = movimientoNegocio.movimientoBanco(movimiento);
				
				movimiento.setCuenta(cuentaATransferir);
				movimiento.setConcepto(new Tipo(Integer.parseInt(request.getParameter("ddlConcepto"))));
				movimiento.setTipoMovimiento(new Tipo(4));
				movimiento.setMonto(BigDecimal.valueOf(Long.valueOf(request.getParameter("txtMonto"))));
				movimientoExitoso = movimientoNegocio.movimientoBanco(movimiento);
				request.setAttribute("insertado", movimientoExitoso);
			} catch (Exception e) {
				request.setAttribute("errorTransferir", e.getMessage());
			}
			
		} else {
			request.setAttribute("errorTransferir", "CBU no encontrado");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");   
        rd.forward(request, response);
	}

}
