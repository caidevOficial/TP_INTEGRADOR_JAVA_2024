package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocioimpl.TipoNegocioImpl;

/**
 * Servlet implementation class servletAdminInformes
 */
@WebServlet("/servletAdminInformes")
public class servletAdminInformes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAdminInformes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("cargarInformes") != null) {
			cargarInformes(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void cargarInformes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TipoNegocioImpl tipoNegocio = new TipoNegocioImpl();
		request.getSession().setAttribute("tipoMovimiento", tipoNegocio.getTipos("TipoMovimiento"));
		RequestDispatcher rd = request.getRequestDispatcher("Informes.jsp");
		rd.forward(request, response);
	}

}
