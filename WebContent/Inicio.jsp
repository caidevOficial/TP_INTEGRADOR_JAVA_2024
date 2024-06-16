<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>Banco Nombre Banco</title>
</head>
<body>
	<% Usuario usuario = (Usuario)request.getSession().getAttribute("usuario"); %>
	<% if(usuario != null) { 
			if(usuario.getTipoRol().getId() == 1){ %>
				<jsp:include page="MenuAdministrador.html"></jsp:include>
			<% } else { %>
				<jsp:include page="MenuUsuario.html"></jsp:include>
			<% } %>
	<% } else { %>
		<jsp:include page="MenuDefault.html"></jsp:include>
	<% } %>
	
	<h1>Bienvenido al Banco [Nombre Banco]</h1>
	
	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>