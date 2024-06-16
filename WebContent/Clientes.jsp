<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Tipo"%>
<%@page import="entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>Clientes</title>
</head>
<body>
	<%
	if (request.getSession().getAttribute("usuario") != null) {
		if (((Usuario) request.getSession().getAttribute("usuario")).getTipoRol().getId() != 1) {
			response.sendRedirect("Inicio.jsp");
		}
	} else {
		response.sendRedirect("Inicio.jsp");
	}

	ArrayList<Cliente> clientes = request.getSession().getAttribute("clientes") != null
			? (ArrayList<Cliente>) request.getSession().getAttribute("clientes")
			: null;	
	%>
	<jsp:include page="MenuAdministrador.html"></jsp:include>

	<div class="container-fluid px-5">
		<div class="row">
			<h1>Clientes</h1>
		</div>
		<div class="row">
			<form action="servletAdminstrador" method="post">
				<div class="input-group mb-3">
					<input type="text" class="form-control" name="txtBuscarCliente"
						placeholder="Ingrese el termino a buscar"
						aria-label="Ingrese el termino a buscar"
						aria-describedby="button-addon2">
					<button class="btn btn-outline-secondary" type="submit" name="btnBuscarCliente">Buscar</button>
				</div>
			</form>
		</div>
		<div class="row">
			<table class="display" id="myTable">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Dni</th>
						<th scope="col">Nombre</th>
						<th scope="col">Apellido</th>
						<th scope="col">Telefono</th>
						<th scope="col">Direccion</th>
						<th scope="col">Localidad</th>
						<th scope="col">Provincia</th>
						<th scope="col">Nacionalidad</th>
						<th scope="col">Genero</th>
						<th scope="col">Fecha de nacimiento</th>
						<th scope="col">Eliminado</th>
						<th scope="col"></th>
						<th scope="col"></th>
						<th scope="col"></th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<%
					if (clientes != null)
						for (Cliente cliente : clientes) {
					%>
					<tr>
						<th scope="row"><%=cliente.getId()%></th>
						<td><%=cliente.getDni()%></td>
						<td><%=cliente.getNombre()%></td>
						<td><%=cliente.getApellido()%></td>
						<td><%=cliente.getTelefono()%></td>
						<td><%=cliente.getDireccion()%></td>
						<td><%=cliente.getLocalidad().getDescripcion()%></td>
						<td><%=cliente.getProvincia().getDescripcion()%></td>
						<td><%=cliente.getNacionalidad().getDescripcion()%></td>
						<td><%=cliente.getGenero().getDescripcion()%></td>
						<td><%=cliente.getFechaNacimiento()%></td>
						<td><%=cliente.getEliminado() ? "Deshabilitado" : "Habilitado"%></td>
						<td><input type="submit" value="Eliminar" name="btnEliminar"
							onclick="return btnConfirmEliminar('servletAdminstrador', 'btnEliminar', 'txtDni=<%=cliente.getDni()%>', 'txtIdUsuario=<%=cliente.getUsuario().getId()%>');" /></td>
						<td><input type="submit" value="Alta" name="btnAlta" onclick="return btnConfirmAlta('servletAdminstrador', 'btnAlta=1', 'txtDni=<%=cliente.getDni()%>', 'txtIdUsuario=<%=cliente.getUsuario().getId()%>');"/></td>
						<td><input type="submit" value="Cambiar Contrasña"
							name="btnCambiarPass"
							onclick="return btnCambiarContraseña('CambiarPass.jsp', 'btnCambiarPass=1', 'txtIdUsuario=<%=cliente.getUsuario().getId()%>');" /></td>
						<td><input type="submit" value="Editar"
							name="btnEditar"
							onclick="return btnEditar('servletAdminstrador', 'btnEditar=1', 'txtId=<%=cliente.getId()%>');" /></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	function btnConfirmEliminar(servlet, btn, txtDni, txtIdUsuario) {
		if (confirm("Estas seguro que desea eliminar el usuario?")) {
			window.location.href = servlet + "?" + btn + "&" + txtDni + "&" + txtIdUsuario;
		}
		return false;
	}
	function btnConfirmAlta(servlet, btnAlta, txtDni, txtIdUsuario) {
		if (confirm("Estas seguro que desea dar de alta el usuario?")) {
			window.location.href = servlet + "?" + btnAlta + "&" + txtDni + "&" + txtIdUsuario;
		}
		return false;
	}
	function btnCambiarContraseña(servlet, btnCambiarPass, txtId) {
		if (confirm("Estas seguro que desea cambiar la contraseña?")) {
			window.location.href = servlet + "?" + btnCambiarPass + "&" + txtId;
		}
		return false;
	}
	function btnEditar(servlet, btnEditar, txtId) {
		if (confirm("Estas seguro que desea editar el cliente?")) {
			window.location.href = servlet + "?" + btnEditar + "&" + txtId;
		}
		return false;
	}
	</script>
	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>