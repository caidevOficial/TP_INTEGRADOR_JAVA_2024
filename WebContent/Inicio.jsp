<%@page import="entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>UTN GAMING BANK</title>
</head>
<body>
	<% Usuario usuario = (Usuario)request.getSession().getAttribute("usuario"); %>
	<% if(usuario != null) { 
			if(usuario.getTipoRol().getId() == 1){ %>
				<jsp:include page="MenuAdministrador.jsp"></jsp:include>
			<% } else { %>
				<jsp:include page="MenuUsuario.jsp"></jsp:include>
			<% } %>
	<% } else { %>
		<jsp:include page="MenuDefault.html"></jsp:include>
	<% } %>
	
<div class="bg-dark text-white">
	<h1>Bienvenido al UTN GAMING BANK</h1>
	
	<jsp:include page="LibreriasBody.html"></jsp:include>
	</div>
</body>
</html>