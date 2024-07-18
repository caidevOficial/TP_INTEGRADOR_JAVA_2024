<%@page import="entidades.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>Prestamos</title>
</head>
<body>
	<jsp:include page="MenuUsuario.jsp"></jsp:include>

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
	%>

	<div class="container-fluid w-50 my-5 bg-dark text-white">
		<h1>Pedir Prestamo</h1>
		<form action="servletCliente" method="get" onsubmit="return validation();">
			<div class="mb-3">
			<div class="input-group input-group-sm">
			<span class="input-group-text" id="inputGroup-sizing-sm">Monto</span>
			<input type="number" class="form-control" required id="txtMonto"
					title="Ingrese el monto a solicitar por favor, tiene que ser un numero"
					aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-sm" name="txtMonto">
			</div>
			<span id="numeroInvalido"></span>
			</div>
			<div class="input-group input-group-sm mb-3">
			<span class="input-group-text" id="inputGroup-sizing-sm">Cuotas</span>
			<select class="form-select" aria-label="Default select example"
					name="ddlCuotas">
					<option value="3">3</option>
					<option value="6">6</option>
					<option value="12">12</option>
					<option value="18">18</option>
					<option value="24">24</option>
					<option value="30">30</option>
					<option value="36">36</option>
					<option value="42">42</option>
					<option value="48">48</option>
					<option value="54">54</option>
					<option value="60">60</option>
				</select>
			</div>
			<div class="input-group input-group-sm mb-3">
			<span class="input-group-text" id="inputGroup-sizing-sm">Cuenta
					a depositar</span> 
					<select class="form-select"
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
			<div class="mb-3">
				<button class="btn btn-primary" type="submit"
					name="btnPedirPrestamo">Pedir</button>
				<%
				if (request.getAttribute("insertado") != null) {
				%>
				<span style="color: green;"><%=request.getAttribute("insertado").toString() == "1" ? "Se pidio correctamente el prestamo" : ""%></span>
				<%
				}
				%>
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
	function validation() {
		let txtMonto = document.getElementById("txtMonto")
		let txtNumeroInvalido = document.getElementById("numeroInvalido");
		let regextMonto = /^([1-9]\d{0,8}|0)$/i;
		if (!regextMonto.test(txtMonto.value)) {
			txtNumeroInvalido.innerText = "El numero ingresado no es valido, los prestamos se pueden pedir de hasta un monton maximo de $100.000.000 pesos"
			txtNumeroInvalido.style.color = "red";
			return false;
		}
		if(confirm("Estas seguro de pedir un prestamo de " + txtMonto.value + "?")){
			return true;
		}
		return false;
	}
	</script>

	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>