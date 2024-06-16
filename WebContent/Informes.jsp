<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
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
<title>Informes</title>
</head>
<body>
	<jsp:include page="MenuAdministrador.html"></jsp:include>

	<%
	if (request.getSession().getAttribute("usuario") != null) {
		if (((Usuario) request.getSession().getAttribute("usuario")).getTipoRol().getId() != 1) {
			response.sendRedirect("Inicio.jsp");
		}
	} else {
		response.sendRedirect("Inicio.jsp");
	}
	
	ArrayList<Tipo> tipoMovimientos = request.getSession().getAttribute("tipoMovimiento") != null ? (ArrayList<Tipo>)request.getSession().getAttribute("tipoMovimiento") : null;
	int selected = request.getAttribute("selected") != null ? Integer.parseInt(request.getAttribute("selected").toString()) : 0;
	int[] info = request.getAttribute("informe") != null ? (int[]) request.getAttribute("informe") : null;
	NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.ITALIAN);
	%>

	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Importe por Tipos de Movimientos</h1>
			</div>
			<hr>
			<form action="servletAdminstrador" method="post">
				<div class="col">
					<div class="row">
						<div class="col">
							<label for="TipoMovimiento" class="form-label">Tipo
								Movimiento</label> <select class="form-select" id="inputGroupSelect04" name="ddlTipoMovimiento"
								aria-label="Example select with button addon">
								<% if(tipoMovimientos != null) for(Tipo tipoMovimiento : tipoMovimientos) { %>
								<option value="<%= tipoMovimiento.getId() %>" <%= selected != 0 && tipoMovimiento.getId() == selected ? "selected" : " " %>><%= tipoMovimiento.getDescripcion() %></option>
								<% } %>
							</select>
						</div>
						<div class="col">
							<label for="Mes" class="form-label">Mes</label> 
							<select class="form-select" id="ddlMes" name="ddlMes"
								aria-label="Example select with button addon">
								<option value="1">Enero</option>
								<option value="2">Febrero</option>
								<option value="3">Marzo</option>
								<option value="4">Abril</option>
								<option value="5">Mayo</option>
								<option value="6">Junio</option>
								<option value="7">Julio</option>
								<option value="8">Agosto</option>
								<option value="9">Septiembre</option>
								<option value="10">Octubre</option>
								<option value="11">Noviembre</option>
								<option value="12">Diciembre</option>
							</select>
						</div>
						<div class="col mt-4">
							<button class="btn btn-outline-primary" type="submit" name="btnBuscarInformeImporteTotal">Buscar</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<hr>
		<div class="col">
			<div class="row">
				<div class="col">
					<h4>Importe Total</h4>
					<p>$<%= request.getAttribute("importeTotal") != null ? nf_in.format(request.getAttribute("importeTotal")) : " " %></p>
				</div>
				<div class="col">
				</div>
			</div>
			<br>
		</div>
		<div class="row">
			<div class="col">
				<h1> Prestamos entregados, Importe Total y Intereses Ganados </h1>
			</div>
			<hr>
			<form action="servletAdminstrador" method="post">
				<div class="col">
					<div class="row">
						<div class="col">
							<label for="Mes" class="form-label">Mes</label> 
							<select class="form-select" id="ddlMes" name="ddlMes"
								aria-label="Example select with button addon">
								<option value="1">Enero</option>
								<option value="2">Febrero</option>
								<option value="3">Marzo</option>
								<option value="4">Abril</option>
								<option value="5">Mayo</option>
								<option value="6">Junio</option>
								<option value="7">Julio</option>
								<option value="8">Agosto</option>
								<option value="9">Septiembre</option>
								<option value="10">Octubre</option>
								<option value="11">Noviembre</option>
								<option value="12">Diciembre</option>
							</select>
						</div>
						<div class="col mt-4">
							<button class="btn btn-outline-primary" type="submit" name="btnPrestamosImporte">Buscar</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<hr>
		<div class="col">
			<div class="row">
				<div class="col">
					<h4>Cantidad Prestamos Entregados</h4>
					<p><%= info != null ? info[0] : " " %></p>
				</div>
				<div class="col">
					<h4>Importe total</h4>
					<p>$<%=info != null ? nf_in.format(info[1]) : " " %></p>
				</div>
				<div class="col">
					<h4>Intereses</h4>
					<p>$<%= info != null ?  nf_in.format(info[2]) : " "%></p>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#datepicker").datepicker({
			format : "mm-yyyy",
			startView : "months",
			minViewMode : "months"
		});
	</script>
	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>