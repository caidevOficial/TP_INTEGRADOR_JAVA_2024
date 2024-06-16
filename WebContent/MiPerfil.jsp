<%@page import="entidad.Cliente"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>Mi Cuenta</title>
</head>
<body>
	<jsp:include page="MenuUsuario.html"></jsp:include>
	<%
	if (request.getSession().getAttribute("usuario") != null) {
		if (((Usuario) request.getSession().getAttribute("usuario")).getTipoRol().getId() != 2) {
			response.sendRedirect("Inicio.jsp");
		}
	} else {
		response.sendRedirect("Inicio.jsp");
	}
	Cliente cliente = request.getSession().getAttribute("cliente") != null ? (Cliente)request.getSession().getAttribute("cliente") : null;
	%>


	<div class="container-fluid w-50 my-5">
		<h1>Informacion Personal</h1>
		<% if(cliente != null) {%>
			<p>Nombre: <%= cliente.getNombre() %></p>
			<p>Apellido: <%= cliente.getApellido() %></p>
			<p>DNI: <%= cliente.getDni() %></p>
			<p>CUIL: <%= cliente.getCuil() %></p>
			<p>Telefono: <%= cliente.getTelefono() %></p>
			<p>Genero: <%= cliente.getGenero().getDescripcion() %></p>
			<p>Direccion: <%= cliente.getDireccion() %></p>
			<p>Localidad: <%= cliente.getLocalidad().getDescripcion() %></p>
			<p>Provincia: <%= cliente.getProvincia().getDescripcion() %></p>
			<p>Nacionalidad: <%= cliente.getLocalidad().getDescripcion() %></p>
		<% } %>
	</div>

	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>