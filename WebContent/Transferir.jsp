<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Tipo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>Transferir</title>
</head>
<body>
	<jsp:include page="MenuUsuario.html"></jsp:include>

	<%
	if (request.getSession().getAttribute("usuario") != null) {
		if (((Usuario) request.getSession().getAttribute("usuario")).getTipoRol().getId() != 2) {
			response.sendRedirect("Inicio.jsp");
		}
	} else {
		response.sendRedirect("Inicio.jsp");
	}
	ArrayList<Cuenta> cuentas = request.getSession().getAttribute("cuentas") != null
			? (ArrayList<Cuenta>) request.getSession().getAttribute("cuentas")
			: null;

	ArrayList<Tipo> conceptos = request.getSession().getAttribute("concepto") != null
			? (ArrayList<Tipo>) request.getSession().getAttribute("concepto")
			: null;
	%>

	<div class="container-fluid w-50 my-5">
		<h1>Transferir</h1>
		<form action="servletCliente" method="post"
			onsubmit="return validation();">
			<div class="mb-3">
				<div class="input-group input-group-sm">
					<span class="input-group-text" id="inputGroup-sizing-sm">Monto</span>
					<input type="text" class="form-control" id="txtMonto"
						aria-label="Sizing example input" name="txtMonto"
						aria-describedby="inputGroup-sizing-sm">
				</div>
				<span id="montoInvalido"></span>
			</div>

			<div class="mb-3">
				<div class="input-group input-group-sm">
					<span class="input-group-text" id="inputGroup-sizing-sm">CBU</span>
					<input type="text" class="form-control" name="txtCBU"
						aria-label="Sizing example input" id="txtCBU"
						aria-describedby="inputGroup-sizing-sm">
				</div>
				<span id="cbuInvalido"></span>
			</div>

			<div class="input-group input-group-sm mb-3">
				<span class="input-group-text" id="inputGroup-sizing-sm">Cuenta
					desde la cual transferir</span> <select class="form-select"
					aria-label="Default select example" name="ddlCuenta">
					<%
					if (cuentas != null)
						for (Cuenta cuenta : cuentas) {
					%>
					<option value="<%=cuenta.getId()%>"><%=cuenta.getNumeroCuenta()%></option>
					<%
					}
					%>
				</select>
			</div>

			<div class="input-group input-group-sm mb-3">
				<span class="input-group-text" id="inputGroup-sizing-sm">Concepto</span>
				<select class="form-select" aria-label="Default select example"
					name="ddlConcepto">
					<%
					if (conceptos != null)
						for (Tipo concepto : conceptos) {
					%>
					<option value="<%=concepto.getId()%>"><%=concepto.getDescripcion()%></option>
					<%
					}
					%>
				</select>
			</div>

			<div>
				<button class="btn btn-primary" type="submit" name="btnTransferir">Transferir</button>
				<%if (request.getAttribute("insertado") != null) {%>
				<span style="color: green;"><%=(Boolean) request.getAttribute("insertado") ? "Se realizo correctamnete la transferencia" : ""%></span>
				<%
				}
				%>
				<%
				if (request.getAttribute("errorTransferir") != null) {
				%>
				<span style="color: red;"><%=request.getAttribute("errorTransferir").toString()%></span>
				<%}%>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		function validation() {
			let txtMonto = document.getElementById("txtMonto")
			let txtCBU = document.getElementById("txtCBU");
			let txtMontoInvalido = document.getElementById("montoInvalido");
			let txtCBUInvalido = document.getElementById("cbuInvalido");
			let regextMonto = /^([1-9]\d{0,8})$/i;
			let regextCBU = /^([1-9]\d{21}|0)$/i;
			if (!regextMonto.test(txtMonto.value)) {
				txtMontoInvalido.innerText = "El numero ingresado no es valido, recorda que las transferencias pueden ser de hasta un monton maximo de $100.000.000 pesos y el monto ingresado debe ser mayor a 0"
				txtMontoInvalido.style.color = "red";
				return false;
			}
			txtMontoInvalido.innerText = " "
			if (!regextCBU.test(txtCBU.value)) {
				txtCBUInvalido.innerText = "El CBU debe tener al menos 22 numeros"
				txtCBUInvalido.style.color = "red";
				return false;
			}
			txtCBUInvalido.innerText = " "
			if (confirm("Esta seguro que desea realizar la transferencia")) {
				return true;
			}
			return false;
		}
	</script>

	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>