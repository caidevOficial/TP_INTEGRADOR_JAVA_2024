<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Movimiento"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>Mi Cuenta</title>
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
	ArrayList<Movimiento> movimientos = request.getSession().getAttribute("movimientos") != null
			? (ArrayList<Movimiento>) request.getSession().getAttribute("movimientos")
			: null;
	Integer selected = request.getSession().getAttribute("selected") != null
			? Integer.parseInt(request.getSession().getAttribute("selected").toString())
			: null;
	NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.ITALIAN);
	%>

	<div class="container-fluid px-5 my-3">
		<form action="servletCliente" method="get">
			<div class="row">
				<div class="col">
					<div class="input-group input-group-sm mb-3">
						<span class="input-group-text" id="inputGroup-sizing-sm">Seleccione
							una cuenta</span> <select class="form-select"
							aria-label="Default select example" name="ddlCuenta">
							<%
							if (cuentas != null)
								for (Cuenta cuenta : cuentas) {
							%>
							<option value="<%=cuenta.getId()%>"
								<%=selected != null && selected == cuenta.getId() ? "selected" : " "%>><%=cuenta.getNumeroCuenta()%></option>
							<%
							}
							%>
						</select>
					</div>
				</div>
				<div class="col">
					<button class="btn btn-outline-primary" type="submit"
						name="btnMostrarMovimientos">Mostrar Movimientos</button>
				</div>
			</div>
		</form>
		<div class="col">
			<div class="row">
				<h1>Movimientos</h1>
			</div>
			<div class="row">
				<div class="col">
					<div class="input-group mb-3">
						<%
							if (cuentas != null)
								for (Cuenta cuenta : cuentas) {
							%>
						<span class="input-group-text">CBU:</span><label type="text" class="form-control" name="txtCBU" id="txtCBU"
							placeholder="Ingrese el termino a buscar"
							aria-label="Ingrese el termino a buscar"><%= selected != null && selected == cuenta.getId() ? cuenta.getCbu() : " " %></label>
						<button class="btn btn-outline-secondary" type="submit" id="btnCopiarCBU"><img src="images/clipboard.svg" width="30px"><span id="copiado"></span></button>
						<% } %>
					</div>
				</div>
				<div class="col"></div>
				<div class="col"></div>
			</div>
			<div class="row">
				<form action="servletCliente" method="get">
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="txtBuscar"
							placeholder="Ingrese el termino a buscar"
							aria-label="Ingrese el termino a buscar">
						<button class="btn btn-outline-secondary" type="submit"
							name="btnBuscarMovimientos">Buscar</button>
					</div>
				</form>
			</div>
			<div class="row">
				<table class="display" id="myTable">
					<thead>
						<tr>
							<th scope="col">Tipo Movimiento</th>
							<th scope="col">Concepto</th>
							<th scope="col">Fecha</th>
							<th scope="col">Monto</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (movimientos != null)
							for (Movimiento movimiento : movimientos) {
						%>
						<tr>
							<th scope="row"><%=movimiento.getTipoMovimiento().getDescripcion()%></th>
							<td><%=movimiento.getConcepto().getDescripcion()%></td>
							<td><%=movimiento.getFechaMovimiento()%></td>
							<td>$<%=nf_in.format(movimiento.getMonto().intValue())%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<div class="row">
				<%
				if (movimientos != null) {
				%>
				<h4>
					Total en cuenta: $<%=nf_in.format(movimientos.get(0).getCuenta().getSaldo().intValue())%></h4>
				<%
				}
				%>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	document.getElementById("btnCopiarCBU").addEventListener("click", copy_password);

	function copy_password() {
	    var copyText = document.getElementById("txtCBU");
	    var textArea = document.createElement("textarea");
	    textArea.value = copyText.textContent;
	    document.body.appendChild(textArea);
	    textArea.select();
	    document.execCommand("Copy");
	    textArea.remove();
	    document.getElementById("copiado").innerText = "Copiado al portapapeles";
	    setTimeout(()=>{
	    	document.getElementById("copiado").innerText = " ";
		}, 2000)
	}
	</script>
	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>