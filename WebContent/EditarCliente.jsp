<%@page import="entidades.Cliente"%>
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
<title>Editar Cliente</title>
</head>
<body>
<jsp:include page="MenuAdministrador.jsp"></jsp:include>
<%
if(request.getSession().getAttribute("usuario") != null){
	if(((Usuario)request.getSession().getAttribute("usuario")).getTipoRol().getId() != 1){
		response.sendRedirect("Inicio.jsp");
	}
} else {
	response.sendRedirect("Inicio.jsp");
}
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
Cliente cliente = request.getSession().getAttribute("cliente") != null ? (Cliente)request.getSession().getAttribute("cliente") : null;
%>
	<div class="container w-50 my-5 bg-dark text-white">
		<div class="row">
			<form action="servletAdminstrador" method="post" onsubmit="return validate();">
				<h1>Editar Cliente</h1>
				<div class="mb-3">
				<div class="input-group input-group-sm">
					<span class="input-group-text" id="inputGroup-sizing-sm">Email:</span>
					<input type="email" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" required name="txtEmail"
						placeholder="Ingrese correo electronico"
						id="txtEmail"
						value="<%= cliente.getUsuario().getEmail() %>">
				</div>
				<span style="color: red;" id="txtMailInvalido"></span>
			</div>
				<div class="mb-3">
				<div class="input-group input-group-sm">
					<span class="input-group-text" id="inputGroup-sizing-sm">Nombre</span>
					<input type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" name="txtNombre" id="txtNombre" required
						placeholder="Ingrese Nombre del usuario" value="<%= cliente.getNombre() %>">
				</div>
				<span style="color: red;" id="txtNombreInvalido"></span>
			</div>
				<div class="mb-3">
				<div class="input-group input-group-sm">
					<span class="input-group-text" id="inputGroup-sizing-sm">Apellido</span>
					<input type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" name="txtApellido" id="txtApellido"
						required placeholder="Ingrese apellido del usuario"
						value="<%= cliente.getApellido() %>">
				</div>
				<span style="color: red;" id="txtApellidoInvalido"></span>
			</div>
				<div class="mb-3">
				<div class="input-group input-group-sm">
					<span class="input-group-text" id="inputGroup-sizing-sm">DNI</span>
					<input type="number" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" name="txtDni" required id="txtDni"
						placeholder="Ingrese DNI del usuario"
						value="<%= cliente.getDni() %>">
				</div>
				<span style="color: red;" id="txtDniInvalido"></span>
			</div>
				<div class="mb-3">
				<div class="input-group input-group-sm">
					<span class="input-group-text" id="inputGroup-sizing-sm">CUIL</span>
					<input type="number" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-sm" name="txtCuil" required id="txtCuil"
						placeholder="Ingrese CUIL del usuario" value="<%= cliente.getCuil() %>"
						>
				</div>
				<span style="color: red;" id="txtCuilInvalido"></span>
			</div>
				<div class="mb-3">
				<div class="input-group input-group-sm">
					<span class="input-group-text" id="inputGroup-sizing-sm">Direccion</span>
					<input type="text" class="form-control"
						aria-label="Sizing example input" value="<%= cliente.getDireccion() %>"
						aria-describedby="inputGroup-sizing-sm" name="txtDireccion" id="txtDireccion"
						required placeholder="Ingrese direccion del usuario"
						>
				</div>
				<span style="color: red;" id="txtDireccionInvalido"></span>
			</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Localidad</span>
					<select class="form-select" aria-label="Default select example"
						name="ddlLocalidad" required>
						<%
						if (localidades != null)
							for (Tipo tipo : localidades) {
						%>
						<option value="<%=tipo.getId()%>" <%= cliente.getLocalidad().getId() == tipo.getId() ? "selected" : " " %>><%=tipo.getDescripcion()%></option>
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
						<option value="<%=tipo.getId()%>" <%= cliente.getProvincia().getId() == tipo.getId() ? "selected" : " " %>><%=tipo.getDescripcion()%></option>
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
						<option value="<%=tipo.getId()%>" <%= cliente.getNacionalidad().getId() == tipo.getId() ? "selected" : " " %>><%=tipo.getDescripcion()%></option>
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
						<option value="<%=tipo.getId()%>" <%= cliente.getGenero().getId() == tipo.getId() ? "selected" : " " %>><%=tipo.getDescripcion()%></option>
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
						placeholder="Ingrese fecha de nacimiento del usuario" value="<%= cliente.getFechaNacimiento() %>">
				</div>
				<div class="mb-3">
					<div class="input-group input-group-sm">
						<span class="input-group-text" id="inputGroup-sizing-sm">Telefono</span>
						<input type="text" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm" required id="txtTelefono"
							name="txtTelefono" placeholder="Ingrese telefono del usuario"
							value="<%= cliente.getTelefono() %>">
					</div>
					<span style="color: red;" id="txtTelefonoInvalido"></span>
				</div>
				
				<div>
					<button class="btn btn-primary" type="submit"
						name="btnEditarCliente">Editar Cliente</button>
					<%
					if (request.getAttribute("clienteEditado") != null) {
					%>
					<span style="color: green;"><%=request.getAttribute("clienteEditado").toString() == "1"
		? "Se edito correctamente el cliente"
		: ""%></span>
					<%
					} %>
				</div>
			</form>
		</div>
		<div class="row my-3">
		<a class="btn btn-primary" href="servletAdminstrador?cargarClientes">Volver a Clientes</a>
		</div>
	</div>
	<script type="text/javascript" >
		function validate() {
			let txtNombre = document.getElementById("txtNombre");
			let txtNombreInvalido = document.getElementById("txtNombreInvalido")
			let txtApellido = document.getElementById("txtApellido");
			let txtApellidoInvalido = document.getElementById("txtApellidoInvalido")
			let txtEmail = document.getElementById("txtEmail");
			let txtEmailInvalido = document.getElementById("txtMailInvalido")
			let txtDni = document.getElementById("txtDni");
			let txtDniInvalido = document.getElementById("txtDniInvalido")
			let txtCuil = document.getElementById("txtCuil");
			let txtCuilInvalido = document.getElementById("txtCuilInvalido")
			let txtDireccion = document.getElementById("txtDireccion");
			let txtDireccionInvalido = document.getElementById("txtDireccionInvalido")
			let txtTelefono = document.getElementById("txtTelefono");
			let txtTelefonoInvalido = document.getElementById("txtTelefonoInvalido")

			const patterNombre = /^[a-zA-Z]{2,50}$/;
			const patterMail = /^((?!\.)[\w\-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$/;
			const patterApellido = /^[a-zA-Z]{2,50}$/;
			const patterDni = /^\d{7,8}(?:[-\s]\d{4})?$/;
			const patterCuil = /^(20|2[3-7]|30|3[3-4])(\d{8})(\d)$/;
			const patterDireccion = /^[a-zA-Z0-9 ]{1,}$/;
			const patterTelefono = /^[0-9]*$/;
			if(!patterMail.test(txtEmail.value)){
				txtEmailInvalido.innerText = "Ingrese un correo valido";
				return false;
			}
			txtEmailInvalido.innerText = " ";
			if(!patterNombre.test(txtNombre.value)){
				txtNombreInvalido.innerText = "Ingrese un nombre valido puede contener solo letras";
				return false;
			}
			txtNombreInvalido.innerText = " ";
			if(!patterApellido.test(txtApellido.value)){
				txtApellidoInvalido.innerText = "Ingrese un apellido valido puede contener solo letras";
				return false;
			}
			txtApellidoInvalido.innerText = " ";
			if(!patterDni.test(txtDni.value)){
				txtDniInvalido.innerText = "Ingrese un dni valido puede contener solo numeros";
				return false;
			}
			txtDniInvalido.innerText = " ";
			if(!patterCuil.test(txtCuil.value)){
				txtCuilInvalido.innerText = "Ingrese un cuil valido no puede contener las barras separadoras";
				return false;
			}
			txtCuilInvalido.innerText = " ";
			if(!patterDireccion.test(txtDireccion.value)){
				txtDireccionInvalido.innerText = "Ingrese una direccion valida puede contener solo letras";
				return false;
			}
			txtDireccionInvalido.innerText = " ";
			if(!patterTelefono.test(txtTelefono.value)){
				txtTelefonoInvalido.innerText = "Ingrese un telefono valido solo puede contener numeros sin barras separadoras";
				return false;
			}
			txtTelefonoInvalido.innerText = " ";
			return true;
		}

	</script>
	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>