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
 * Servlet implementation class servletAdminUsuario
 */
@WebServlet("/servletAdminUsuario")
public class servletAdminUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAdminUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnCrearAdmin") != null) {
			crearUsuarioAdministrador(request, response);
			return;
		}
	}
	
	protected void crearUsuarioAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
		Usuario usuario = new Usuario();
		Tipo tipoRol = new Tipo();
		tipoRol.setId(1);
		usuario.setTipoRol(tipoRol);
		usuario.setNombreUsuario(request.getParameter("txtNombreUsuario"));
		usuario.setEmail(request.getParameter("txtEmail"));
		usuario.setPassword(request.getParameter("txtPassword"));
		try {
			request.setAttribute("usuarioInsertado", usuarioNegocio.crearUsuario(usuario));
		} catch (SQLException e) {
			request.setAttribute("usuarioError", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/CrearCuentas.jsp");   
        rd.forward(request, response);
	}

}
