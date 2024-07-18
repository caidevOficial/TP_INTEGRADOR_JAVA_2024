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
			<img class="mx-auto" src="logo_bank.gif" alt="Descripcion del GIF">
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
			<div class="collapse navbar-collapse justify-content-between" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" href="servletCliente?cargarSelects=1">Cuenta</a> 
					<a class="nav-link" href="servletCliente?cargarPerfil=1">Mi Perfil</a>
					<a class="nav-link" href="servletCliente?cargarSelectsPrestamo=1">Pedir Prestamo</a>
					<a class="nav-link"	href="servletCliente?cargarSelectsPrestamoPagar=1">Pagar Prestamo</a>
					<a class="nav-link" href="servletCliente?transferir=1">Transferir</a>
					<a class="nav-link" href="servletLogin?btnCerrarSesion=1">Cerrar Sesion</a>
				</div>
				<div class="navbar-nav text-end">
					<label id="nombreUsuario">Usuario: <b><%= ((Usuario)request.getSession().getAttribute("usuario")).getNombreUsuario().toUpperCase() %></b></label>
				</div>
			</div>
		</div>
	</nav>
</header>
</body>
</html>