<%@page import="entidades.Usuario"%>
<%@page import="entidades.Tipo"%>
<%@page import="entidades.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
			<a class="navbar-brand fa-solid fa-house" href="Inicio.jsp">  UTN GAMING BANK</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-between" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link fa-solid fa-address-card" href="servletCliente?cargarSelects=1">  Cuenta</a> 
					<a class="nav-link fa-solid fa-user" href="servletCliente?cargarPerfil=1">  Mi Perfil</a>
					<a class="nav-link fa-solid fa-hand-holding-dollar" href="servletCliente?cargarSelectsPrestamo=1">  Pedir Prestamo</a>
					<a class="nav-link fa-solid fa-money-bill-trend-up"	href="servletCliente?cargarSelectsPrestamoPagar=1">  Pagar Prestamo</a>
					<a class="nav-link fa-solid fa-money-bill-transfer" href="servletCliente?transferir=1">  Transferir</a>
					<a class="nav-link fa-solid fa-user-xmark" href="servletLogin?btnCerrarSesion=1">  Cerrar Sesion</a>
				</div>
				<div class="navbar-nav text-end">
					<label class="fa-solid fa-user" id="nombreUsuario">  Usuario: <b><%= ((Usuario)request.getSession().getAttribute("usuario")).getNombreUsuario().toUpperCase() %></b></label>
				</div>
			</div>
		</div>
	</nav>
</header>
</body>
</html>