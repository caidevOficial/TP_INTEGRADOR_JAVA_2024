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
		<div class="container-fluid justify-content-between">
			<img class="mx-auto" src="logo_bank.gif" alt="Descripcion del GIF">
		</div>
	</nav>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand fa-solid fa-house" href="Inicio.jsp">UTN GAMING BANK</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-between" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link fa-solid fa-users" href="servletAdminstrador?cargarClientes=1">  Clientes</a> 
					<a class="nav-link fa-solid fa-piggy-bank" href="servletAdminstrador?cargarCuentas=1">  Cuentas</a> 
					<a class="nav-link fa-solid fa-hand-holding-dollar" href="servletAdminstrador?cargarPrestamos=1">  Prestamos</a>
					<a class="nav-link fa-solid fa-house-chimney-medical" href="servletAdminstrador?cargarSelects=1">  Crear Cuentas</a>
					<a class="nav-link fa-solid fa-chart-pie" href="servletAdminstrador?cargarInformes=1">  Informes</a> 
					<a class="nav-link fa-solid fa-user-xmark" href="servletLogin?btnCerrarSesion=1">  Cerrar Sesion</a>
				</div>
				<div class="navbar-nav text-end">
					<label class="fa-solid fa-user-shield" id="nombreUsuario">  Administrador: <b><%= ((Usuario)request.getSession().getAttribute("usuario")).getNombreUsuario().toUpperCase() %></b></label>
				</div>
			</div>
		</div>
	</nav>
</header>
</body>
</html>