<%@page import="entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>Cambiar Contraseña</title>
</head>
<body>
<%
if(request.getSession().getAttribute("usuario") != null){
	if(((Usuario)request.getSession().getAttribute("usuario")).getTipoRol().getId() != 1){
		response.sendRedirect("Inicio.jsp");
	}
} else {
	response.sendRedirect("Inicio.jsp");
}
%>
<jsp:include page="MenuAdministrador.jsp"></jsp:include>
			<%
			if(request.getParameter("txtIdUsuario") != null){
				request.getSession().setAttribute("idUsuario", Integer.parseInt(request.getParameter("txtIdUsuario")));
			}
			%>

<div class="container w-50 my-5 bg-dark text-white">	
		<div class="row">
			<h1>Cambiar Contraseña</h1>
			<form action="servletAdminstrador" method="post" onsubmit="return validate();">
				<div class="mb-3">
				<div class="input-group input-group-sm ">
					<span class="input-group-text" id="inputGroup-sizing-sm">Nueva Contraseña:</span>
					<input type="password" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required
						name="txtPassword" placeholder="Ingrese una clave" id="txtPassword"
						>
				</div>
				</div>
				<div class="mb-3">
				<div class="input-group input-group-sm ">
					<span class="input-group-text" id="inputGroup-sizing-sm">Ingrese de nuevo la Contraseña:</span>
					<input type="password" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required
						placeholder="Ingrese una clave" id="txtRepetirPassword"
						>
				</div>
				<span id="invalido" style="color: red;"></span>
				</div>
				<div>
					<button class="btn btn-primary" type="submit"
						name="btnCambiarPass">Cambiar Contraseña</button>
					<%if(request.getAttribute("passActualizada") != null){ %>
						<span style="color: green;"><%= (Boolean)request.getAttribute("passActualizada") ? "Se actualizo correctamente la contraseña en la base de datos" : " No se pudo actualizar la contraseña" %></span>
					<% } %>				
				</div>
			</form>
		</div>
		<div class="row my-3">
		<a class="btn btn-primary" href="servletAdminstrador?cargarClientes">Volver a Clientes</a></div>
		</div>
		<script type="text/javascript">
	function validate() {
		let txtContraseña = document.getElementById("txtPassword");
		let txtRepetirContraseña = document.getElementById("txtRepetirPassword");
		let invalido = document.getElementById("invalido");
		const pattern = /^((?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9]).{6,8})\S$/;
		if (!pattern.test(txtContraseña.value)) {
			invalido.innerText = "Ingresa una contraseña valida tiene que tener un mínimo de 6 caracteres y maximo 8, al menos 1 letra mayúscula, 1 letra minúscula y 1 número sin espacios"
			return false;
		}
		if(txtContraseña.value != txtRepetirContraseña.value){
			invalido.innerText = "Las contraseñas no coinciden";
			return false;
		}
		invalido.innerText = " ";
		return true;
	}
	</script>

<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>