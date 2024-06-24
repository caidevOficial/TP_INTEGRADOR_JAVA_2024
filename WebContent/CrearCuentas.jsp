<%@page import="entidades.Usuario"%>
<%@page import="entidades.Tipo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>Register</title>
</head>
<body>
	<jsp:include page="MenuAdministrador.html"></jsp:include>
	<%
	if(request.getSession().getAttribute("usuario") != null){
		if(((Usuario)request.getSession().getAttribute("usuario")).getTipoRol().getId() != 1){
			response.sendRedirect("Inicio.jsp");
		}
	} else {
		response.sendRedirect("Inicio.jsp");
	}
	
	String idUsuario = request.getAttribute("IdUsuario") != null ? request.getAttribute("IdUsuario").toString() : null;
	ArrayList<Tipo> provincias = (ArrayList<Tipo>) request.getSession().getAttribute("selectProvincias") != null
			? (ArrayList<Tipo>) request.getSession().getAttribute("selectProvincias")
			: null;
	ArrayList<Tipo> nacionalidades = (ArrayList<Tipo>) request.getSession().getAttribute("selectNacionalidades") != null
			? (ArrayList<Tipo>) request.getSession().getAttribute("selectNacionalidades")
			: null;
	ArrayList<Tipo> localidades = (ArrayList<Tipo>) request.getSession().getAttribute("selectLocalidades") != null
			? (ArrayList<Tipo>) request.getSession().getAttribute("selectLocalidades")
			: null;
	ArrayList<Tipo> generos = (ArrayList<Tipo>) request.getSession().getAttribute("selectGeneros") != null
			? (ArrayList<Tipo>) request.getSession().getAttribute("selectGeneros")
			: null;
	ArrayList<Tipo> tipoCuenta = (ArrayList<Tipo>) request.getSession().getAttribute("selectTipoCuenta") != null
			? (ArrayList<Tipo>) request.getSession().getAttribute("selectTipoCuenta")
			: null;
	%>

	<div class="container w-50 my-5">
		<div class="row">
			<h1>Crear Administrador</h1>
			<form action="servletAdminstrador" method="post">
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Nombre Usuario:</span>
					<input type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required name="txtNombreUsuario"
						placeholder="Ingrese el nombre de usuario" pattern="^[a-zA-Z0-9]{1,20}$" title="Ingrese un nombre de usuario, maximo 20 caracteres">
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Email:</span>
					<input type="email" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required name="txtEmail"
						placeholder="Ingrese correo electronico" pattern="^((?!\.)[\w\-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$" title="Ingrese un correo valido">
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Password:</span>
					<input type="password" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required
						name="txtPassword" placeholder="Ingrese una clave" pattern="^((?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9]).{5,8})\S$" title="Ingresa una contraseña valida tiene que tener un mínimo de 6 caracteres y maximo 8, al menos 1 letra mayúscula, 1 letra minúscula y 1 número sin espacios"
						>
				</div>
				<div>
					<button class="btn btn-primary" type="submit"
						name="btnCrearAdmin">Crear Administrador</button>
					<%if(request.getAttribute("usuarioInsertado") != null){ %>
						<span><%= request.getAttribute("usuarioInsertado").toString() == "1" ? "Se agrego correctamente el usuario a la base de datos" : "" %></span>
					<% }
					if (request.getAttribute("usuarioError") != null){	%>
						<span><%= request.getAttribute("usuarioError").toString()%></span>
					<%}%>					
				</div>
			</form>
		</div>
		<hr>
		<div class="row">
		<form action="servletAdminstrador" method="post">
				<h1>Crear Cliente</h1>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Nombre Usuario:</span>
					<input type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required name="txtNombreUsuario"
						placeholder="Ingrese el nombre de usuario" pattern="^[a-zA-Z0-9]{1,20}$" title="Ingrese un nombre de usuario, maximo 20 caracteres">
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Email:</span>
					<input type="email" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required name="txtEmail"
						placeholder="Ingrese correo electronico" pattern="^((?!\.)[\w\-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$" title="Ingrese un correo valido">
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Contraseña:</span>
					<input type="password" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required
						name="txtPassword" placeholder="Ingrese su clave" pattern="^((?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9]).{6,8})\S$" title="Ingresa una contraseña valida tiene que tener un mínimo de 6 caracteres y maximo 8, al menos 1 letra mayúscula, 1 letra minúscula y 1 número sin espacios"
						>
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Nombre</span>
					<input type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" name="txtNombre" required
						placeholder="Ingrese Nombre del usuario"
						pattern="[a-zA-Z]{1,50}" title="Ingrese un nombre valido puede contener solo letras">
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Apellido</span>
					<input type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" name="txtApellido"
						required placeholder="Ingrese apellido del usuario"
						pattern="[a-zA-Z]{1,50}" title="Ingrese un apellido valido puede contener solo letras"
						>
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">DNI</span>
					<input type="number" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" name="txtDni" required
						placeholder="Ingrese DNI del usuario"
						pattern="^\d{7,8}(?:[-\s]\d{4})?$" title="Ingrese un dni valido puede contener solo numeros">
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">CUIL</span>
					<input type="number" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" name="txtCuil" required
						placeholder="Ingrese CUIL del usuario"
						pattern="^(20|2[3-7]|30|3[3-4])(\d{8})(\d)$" title="Ingrese un cuil valido no puede contener las barras separadoras">
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Direccion</span>
					<input type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" name="txtDireccion"
						required placeholder="Ingrese direccion del usuario"
						pattern="[a-zA-Z0-9 ]{1,}" title="Ingrese una direccion valida puede contener solo letras">
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Localidad</span>
					<select class="form-select" aria-label="Default select example"
						name="ddlLocalidad" required>
						<%
						if (localidades != null)
							for (Tipo tipo : localidades) {
						%>
						<option value="<%=tipo.getId()%>"><%=tipo.getDescripcion()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Provincia</span>
					<select class="form-select" aria-label="Default select example"
						name="ddlProvincia" required>
						<%
						if (provincias != null)
							for (Tipo tipo : provincias) {
						%>
						<option value="<%=tipo.getId()%>"><%=tipo.getDescripcion()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Nacionalidad</span>
					<select class="form-select" aria-label="Default select example"
						name="ddlNacionalidad" required>
						<%
						if (nacionalidades != null)
							for (Tipo tipo : nacionalidades) {
						%>
						<option value="<%=tipo.getId()%>"><%=tipo.getDescripcion()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Genero</span>
					<select class="form-select" aria-label="Default select example"
						name="ddlGenero" required>
						<%
						if (generos != null)
							for (Tipo tipo : generos) {
						%>
						<option value="<%=tipo.getId()%>"><%=tipo.getDescripcion()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Fecha
						de Nacimiento</span> <input type="date" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required
						name="txtFechaNacimiento"
						placeholder="Ingrese fecha de nacimiento del usuario">
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Telefono</span>
					<input type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required
						name="txtTelefono" placeholder="Ingrese telefono del usuario"
						pattern="[0-9]{1-}" title="Ingrese un telefono valido solo puede contener numeros sin barras separadoras">
				</div>
				<div>
					<button class="btn btn-primary" type="submit"
						name="btnCrearCliente">Crear Cliente</button>
						<%if(request.getAttribute("clienteInsertado") != null){ %>
						<span><%= request.getAttribute("clienteInsertado").toString() == "1" ? "Se agrego correctamente el cliente a la base de datos" : "" %></span>
					<% }
					if (request.getAttribute("clienteError") != null){	%>
						<span><%= request.getAttribute("clienteError").toString()%></span>
					<%}%>
					
					<%if(request.getAttribute("usuarioClienteInsertado") != null){ %>
						<span><%= request.getAttribute("usuarioClienteInsertado").toString() == "1" ? "Se agrego correctamente el usuario a la base de datos" : "" %></span>
					<% }
					if (request.getAttribute("usuarioClienteError") != null){	%>
						<span><%= request.getAttribute("usuarioClienteError").toString()%></span>
					<%}%>	
				</div>
		</form>
		</div>
		<hr>
		<div class="row">
			<h1>Crear Cuenta</h1>
			<form action="servletAdminstrador" method="post">
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">DNI
						Cliente</span> <input type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required 
						pattern="\d{7,8}(?:[-\s]\d{4})?$" title="Ingrese un dni valido tiene que ser un numero"
						name="txtDniCliente" placeholder="Ingrese dni del cliente">
				</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Tipo
						de Cuenta</span> <select class="form-select"
						aria-label="Default select example" required name="ddlTipoCuenta">
						<%
						if (tipoCuenta != null)
							for (Tipo tipo : tipoCuenta) {
						%>
						<option value="<%=tipo.getId()%>"><%=tipo.getDescripcion()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div>
					<button class="btn btn-primary" type="submit" name="btnCrearCuenta">Crear
						Cuenta</button>
						<%if(request.getAttribute("cuentaInsertada") != null){ %>
						<span><%= request.getAttribute("cuentaInsertada").toString() == "1" ? "Se agrego correctamente la cuenta a la base de datos" : "" %></span>
					<% }
					if (request.getAttribute("cuentaError") != null){	%>
						<span><%= request.getAttribute("cuentaError").toString()%></span>
					<%}%>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>