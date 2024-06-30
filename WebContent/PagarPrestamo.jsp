<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="entidades.Cuota"%>
<%@page import="entidades.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>Pagar Prestamo</title>
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

		ArrayList<Prestamo> prestamos = request.getSession().getAttribute("prestamos") != null
		? (ArrayList<Prestamo>) request.getSession().getAttribute("prestamos")
		: null;
		Integer selected = request.getSession().getAttribute("selected") != null
		? Integer.parseInt(request.getSession().getAttribute("selected").toString())
		: null;
		Prestamo prestamoPagar = request.getSession().getAttribute("prestamo") != null
		? (Prestamo)request.getSession().getAttribute("prestamo")
		: null;
		ArrayList<Cuota> coutas = request.getSession().getAttribute("coutas") != null
		? (ArrayList<Cuota>) request.getSession().getAttribute("coutas")
		: null;
		NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.ITALIAN);
	%>

	<div class="container-fluid w-50 my-5">
		<h1>Pagar Prestamo</h1>
		<%
			if(prestamos != null) if(!prestamos.isEmpty()) {
		%>
		<form action="servletCliente" method="get">
		<div class="input-group input-group-sm mb-3">
			<span class="input-group-text" id="inputGroup-sizing-sm">Prestamo
				a pagar</span> <select class="form-select" name="ddlPrestamos"
				aria-label="Default select example">
				<%
					if (prestamos != null) for (Prestamo prestamo : prestamos) {
				%>
				<option value="<%=prestamo.getId()%>"<%=selected != null && selected == prestamo.getId() ? "selected" : " "%>>$<%=prestamo.getMontoSolicitado().intValueExact()%></option>
				<%
					}
				%>
			</select>
		</div>
		<div class="mb-3">
			<button class="btn btn-primary" type="submit" name="btnSeleccionarCuenta">Seleccionar
				Prestamo</button>
		</div>
		</form>
		<%
			if(coutas != null) if(!coutas.isEmpty()) {
		%>
		<form action="servletCliente" method="post" onsubmit="return validation();">
		<div class="input-group input-group-sm mb-3">
			<span class="input-group-text" id="inputGroup-sizing-sm">Cuotas
				a Pagar</span> <select class="form-select" name="ddlCoutas"
				aria-label="Default select example">
				<%
					if (coutas != null) for (Cuota couta : coutas) {
				%>
				<option value="<%=couta.getId()%>">Couta <%=couta.getNumeroCouta()%> - $<%=nf_in.format(prestamoPagar.getMontoCuota())%></option>
				<% } %>
			</select>
		</div>
		<div>
			<button class="btn btn-primary" type="submit" name="btnPagar">Pagar</button>
		</div>
		</form>
		<% } 
		} else { %>
		<p>No tenes prestamos aprobados</p>
		<% } %>
		<%if(request.getAttribute("coutaPaga") != null){ %>
						<span style="color: green;"><%= Boolean.parseBoolean(request.getAttribute("coutaPaga").toString()) ? "El pago de la couta se realizo correctamente" : "" %></span>
					<% } %>
			<%if (request.getAttribute("errorPago") != null){	%>
						<span style="color: red;"><%= request.getAttribute("errorPago").toString()%></span>
					<%}%>
	</div>
	
	<script type="text/javascript">
	function validation() {
		if(confirm("Esta seguro que desea pagar prestamo")){
			return true;
		}
		return false;
	}
	</script>

	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>