<%@page import="entidades.Cuenta"%>
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
<jsp:include page="MenuAdministrador.html"></jsp:include>
<%
if(request.getSession().getAttribute("usuario") != null){
	if(((Usuario)request.getSession().getAttribute("usuario")).getTipoRol().getId() != 1){
		response.sendRedirect("Inicio.jsp");
	}
} else {
	response.sendRedirect("Inicio.jsp");
}
ArrayList<Tipo> tipoCuenta = (ArrayList<Tipo>) request.getSession().getAttribute("selectTipoCuenta") != null
? (ArrayList<Tipo>) request.getSession().getAttribute("selectTipoCuenta")
: null;

Cuenta cuenta = request.getSession().getAttribute("cuenta") != null ? (Cuenta)request.getSession().getAttribute("cuenta") : null; 

%>
	<div class="container w-50 my-5">
		<div class="row">
			<form action="servletAdminstrador" method="post" onsubmit="return validate();">
				<h1>Editar Cuenta</h1>
				<div class="mb-3">
				<div class="input-group input-group-sm">
					<span class="input-group-text" id="inputGroup-sizing-sm">CBU</span>
					<input type="number" class="form-control" required name="txtCBU"
						placeholder="Ingrese un CBU" id="txtCBU" value="<%= cuenta.getCbu() %>">
				</div>
				<span style="color: red;" id="txtCBUInvalido"></span>
			</div>
				<div class="mb-3">
				<div class="input-group input-group-sm">
					<span class="input-group-text" id="inputGroup-sizing-sm">Numero Cuenta</span>
					<input type="number" class="form-control"
						name="txtNumeroCuenta" id="txtNumeroCuenta" required
						placeholder="Ingrese Nombre del usuario" value="<%= cuenta.getNumeroCuenta() %>">
				</div>
				<span style="color: red;" id="txtNumeroCuentaInvalido"></span>
			</div>
				<div class="mb-3">
				<div class="input-group input-group-sm">
					<span class="input-group-text" id="inputGroup-sizing-sm">Saldo</span>
					<input type="number" class="form-control"
						name="txtSaldo" id="txtSaldo"
						required placeholder="Ingrese apellido del usuario"
						value="<%= cuenta.getSaldo().intValue() %>">
				</div>
				<span style="color: red;" id="txtSaldoInvalido"></span>
			</div>
				<div class="input-group input-group-sm mb-3">
					<span class="input-group-text" id="inputGroup-sizing-sm">Tipo
						de Cuenta</span> <select class="form-select"
						aria-label="Default select example" required name="ddlTipoCuenta">
						<%
						if (tipoCuenta != null)
							for (Tipo tipo : tipoCuenta) {
						%>
						<option value="<%=tipo.getId()%>" <%= cuenta.getTipoCuenta().getId() == tipo.getId() ? "selected" : " " %> ><%=tipo.getDescripcion()%></option>
						<%
						}
						%>
					</select>
				</div>
				
				<div>
					<button class="btn btn-primary" type="submit"
						name="editarCuenta">Editar Cliente</button>
					<%
					if (request.getAttribute("cuentaEditada") != null) {
					%>
					<span style="color: green;"><%=request.getAttribute("cuentaEditada").toString() == "1"
		? "Se edito correctamente la cuenta"
		: ""%></span>
					<%
					} %>
					<%
					if (request.getAttribute("cuentaError") != null) {
					%>
					<span style="color: red;"><%=request.getAttribute("cuentaError").toString()%></span>
					<%
					} %>
				</div>
			</form>
		</div>
		<div class="row my-3">
		<a class="btn btn-primary" href="servletAdminstrador?cargarCuentas">Volver a Cuentas</a>
		</div>
	</div>
	<script type="text/javascript" >
		function validate() {
			let txtNumeroCuenta = document.getElementById("txtNumeroCuenta");
			let txtNumeroCuentaInvalido = document.getElementById("txtNumeroCuentaInvalido")
			let txtSaldo = document.getElementById("txtSaldo");
			let txtSaldoInvalido = document.getElementById("txtSaldoInvalido")
			let txtCBU = document.getElementById("txtCBU");
			let txtCBUInvalido = document.getElementById("txtCBUInvalido")
			
			const patterCBU = /^([1-9]\d{21}|0)$/;
			const patterNumeroCuenta = /^([1-9]\d{12}|0)$/;
			const patterSaldo = /^([1-9]\d{0,8})$/;

			if(!patterCBU.test(txtCBU.value)){
				txtCBUInvalido.innerText = "Ingrese un cbu valido";
				return false;
			}
			txtCBUInvalido.innerText = " ";
			if(!patterNumeroCuenta.test(txtNumeroCuenta.value)){
				txtNumeroCuentaInvalido.innerText = "Ingrese un numero de cuenta valido";
				return false;
			}
			txtNumeroCuentaInvalido.innerText = " ";
			if(!patterSaldo.test(txtSaldo.value)){
				txtSaldoInvalido.innerText = "Ingrese un saldo valido";
				return false;
			}
			txtSaldoInvalido.innerText = " ";
			return true;
		}

	</script>
	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>