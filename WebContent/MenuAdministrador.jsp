<%@page import="entidades.Usuario"%>
<%@page import="entidades.Tipo"%>
<%@page import="entidades.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<header>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<img src="logo_bank.gif" alt="Descripcion del GIF">
		</div>
	</nav>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="Inicio.jsp">UTN GAMING BANK</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" href="servletAdminstrador?cargarClientes=1">Clientes</a> 
					<a class="nav-link" href="servletAdminstrador?cargarCuentas=1">Cuentas</a> 
					<a class="nav-link" href="servletAdminstrador?cargarPrestamos=1">Prestamos</a>
					<a class="nav-link" href="servletAdminstrador?cargarSelects=1">Crear Cuentas</a>
					<a class="nav-link" href="servletAdminstrador?cargarInformes=1">Informes</a> 
					<a class="nav-link" href="servletLogin?btnCerrarSesion=1">Cerrar Sesion</a>
					<label id="nombreUsuario"><%= ((Usuario)request.getSession().getAttribute("usuario")).getNombreUsuario().toUpperCase() %></label>
				</div>
			</div>
		</div>
	</nav>
</header>
</body>
</html>