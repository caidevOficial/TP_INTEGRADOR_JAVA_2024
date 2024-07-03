package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import negocioimpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class servletLogin
 */
@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnCerrarSesion") != null) {
			request.getSession().removeAttribute("usuario");
			RequestDispatcher rd = request.getRequestDispatcher("/Inicio.jsp");   
	        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnIngresar") != null) {
			UsuarioNegocioImpl usuarioNegocio = new UsuarioNegocioImpl();
			String mail = request.getParameter("mail").toString();
			String password = request.getParameter("password").toString();
			System.out.println(String.format("%s - %s", mail, password));
			if(mail != null && !mail.isEmpty() && password != null && !password.isEmpty()) {
				Usuario usuario = new Usuario(mail, password);
				usuario = usuarioNegocio.login(usuario);
				
				if(usuario != null) {
					HttpSession session = request.getSession(true);
					session.setAttribute("userName", usuario.getNombreUsuario());
					request.getSession().setAttribute("usuario", usuario);
					RequestDispatcher rd = request.getRequestDispatcher("/Inicio.jsp");   
			        rd.forward(request, response);
				} else {
					request.setAttribute("errorLogin", "Email o contrase�a invalidos");
					RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");   
			        rd.forward(request, response);
				}
			}
			else {
				request.setAttribute("errorLogin", "Por favor completa el email y la contraseña");
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");   
		        rd.forward(request, response);
			}	
		}
		
		if (request.getParameter("btnCerrarSesion") != null) {
			System.out.println("Dentro del boton cerrar");
			HttpSession session = request.getSession(false);
			//session.getAttribute("userName");
		    if (session != null) {
		        session.invalidate();
		        System.out.println("Sesion Cerrada");
		    }
		}
	}
}
