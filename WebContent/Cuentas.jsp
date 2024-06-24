<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Tipo"%>
<%@page import="entidades.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>Cuentas</title>
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

	ArrayList<Cuenta> cuentas = request.getSession().getAttribute("cuentas") != null
			? (ArrayList<Cuenta>) request.getSession().getAttribute("cuentas")
			: null;
	
	NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.ITALIAN);
	%>
	<jsp:include page="MenuAdministrador.html"></jsp:include>

	<div class="container-fluid px-5">
		<div class="row">
			<h1>Cuentas</h1>
		</div>
		<div class="row">
			<form action="servletAdminstrador" method="post">
				<div class="input-group mb-3">
					<input type="text" class="form-control" name="txtBuscarCuenta"
						placeholder="Ingrese el termino a buscar"
						aria-label="Ingrese el termino a buscar"
						aria-describedby="button-addon2">
					<button class="btn btn-outline-secondary" type="submit" name="btnBuscarCuenta"
						id="button-addon2">Buscar</button>
				</div>
			</form>
		</div>
		<div class="row">
			<table class="display" id="myTable">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Nombre</th>
						<th scope="col">Apellido</th>
						<th scope="col">DNI</th>
						<th scope="col">Fecha Creacion</th>
						<th scope="col">Nro. Cuenta</th>
						<th scope="col">CBU</th>
						<th scope="col">Saldo</th>
						<th scope="col">Tipo Cuenta</th>
						<th scope="col">Habilitada</th>
						<th scope="col"></th>
						<th scope="col"></th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<%
					if (cuentas != null)
						for (Cuenta cuenta : cuentas) {
					%>
					<tr>
						<th><%=cuenta.getId()%></th>
						<td><%=cuenta.getCliente().getNombre()%></td>
						<td><%=cuenta.getCliente().getApellido()%></td>
						<td><%=cuenta.getCliente().getDni()%></td>
						<td><%=cuenta.getFechaCreacion()%></td>
						<td><%=cuenta.getNumeroCuenta()%></td>
						<td><%=cuenta.getCbu()%></td>
						<td><%=nf_in.format(cuenta.getSaldo().longValue())%></td>
						<td><%=cuenta.getTipoCuenta().getDescripcion()%></td>
						<td><%=cuenta.getEliminado() ? "Deshabilitado" : "Habilitado"%></td>
						<td><input type="submit" value="Eliminar" name="btnEliminar"
							onclick="return btnConfirmEliminar('servletAdminstrador', 'btnEliminarCuenta=1', 'txtId=<%=cuenta.getId()%>');" /></td>
						<td><input type="submit" value="Alta" name="btnAlta"
							onclick="return btnConfirmAlta('servletAdminstrador', 'btnAltaCuenta=1', 'txtId=<%=cuenta.getId()%>');" /></td>
						<td><input type="submit" value="Editar" name="btnEditar"
							onclick="return btnEditar('servletAdminstrador', 'btnEditarCuenta=1', 'txtCBU=<%=cuenta.getCbu()%>');" /></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		function btnConfirmEliminar(servlet, btnEliminarCuenta, txtId) {
			if (confirm("Estas seguro que desea eliminar el cliente")) {
				window.location.href = servlet + "?" + btnEliminarCuenta + "&" + txtId;
			}
			return false;
		}
		function btnConfirmAlta(servlet, btnAltaCuenta, txtId) {
			if (confirm("Estas seguro que desea dar de alta el cliente")) {
				window.location.href = servlet + "?" + btnAltaCuenta + "&" + txtId;
			}
			return false;
		}
		function btnEditar(servlet, btnEditar, txtCBU) {
			if (confirm("Estas seguro que desea editar el cliente")) {
				window.location.href = servlet + "?" + btnEditar + "&" + txtCBU;
			}
			return false;
		}
		</script>

	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>