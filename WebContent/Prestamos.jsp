<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Prestamo"%>
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
    <jsp:include page="MenuAdministrador.html"></jsp:include>
    
    <%
    if(request.getSession().getAttribute("usuario") != null){
		if(((Usuario)request.getSession().getAttribute("usuario")).getTipoRol().getId() != 1){
			response.sendRedirect("Inicio.jsp");
		}
	} else {
		response.sendRedirect("Inicio.jsp");
	}
    
    ArrayList<Prestamo> prestamos = request.getSession().getAttribute("prestamos") != null ? (ArrayList<Prestamo>)request.getSession().getAttribute("prestamos") : null;
    NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.ITALIAN);
    %>

	<div class="container-fluid px-5">
		<div class="row"><h1>Prestamos</h1></div>
		<div class="row">
		<form action="servletAdminstrador" method="post">
			<div class="input-group mb-3">
				<input type="text" class="form-control" name="txtBuscarPrestamo"
					placeholder="Ingrese el termino a buscar"
					aria-label="Ingrese el termino a buscar"
					aria-describedby="button-addon2">
				<button class="btn btn-outline-secondary" type="submit" name="btnBuscarPrestamo"
					id="button-addon2">Buscar</button>
			</div>
		</form>
		</div>
		<div class="row">
		<table class="display" id="myTable">
			<thead>
			  <tr>
				<th scope="col">Id Prestamo</th>
				<th scope="col">Numero Cuenta</th>
				<th scope="col">CBU</th>
				<th scope="col">FechaPedido</th>
				<th scope="col">Monto Solicitado</th>
				<th scope="col">Monto Cuota</th>
				<th scope="col">Cantidad Cuotas</th>
				<th scope="col">Autorizar</th>
				<th scope="col">Rechazar</th>
			  </tr>
			</thead>
			<tbody>
			  <% if(prestamos != null) for(Prestamo prestamo : prestamos) {%>
			  	<tr>
					<th scope="row"><%= prestamo.getId() %></th>
					<td><%= prestamo.getCuenta().getNumeroCuenta() %></td>
					<td><%= prestamo.getCuenta().getCbu() %></td>
					<td><%= prestamo.getFechaPedido() %></td>
					<td>$<%= nf_in.format(prestamo.getMontoSolicitado().longValue())%></td>
					<td>$<%= nf_in.format(prestamo.getMontoCuota().longValue()) %></td>
					<td><%= prestamo.getCantidadCuotas() %></td>
					<td><input type="submit" value="Rechazar" name="btnRechzar" onclick="return btnConfirmRechazar('servletAdminstrador', 'btnRechazar=1', 'txtId=<%=prestamo.getId()%>');"/></td>
					<td><input type="submit" value="Aprobar" name="btnAlta" onclick="return btnConfirmAprobar('servletAdminstrador', 'btnAceptar=1', 'txtId=<%=prestamo.getId()%>', 'txtIdCuenta=<%= prestamo.getCuenta().getId()%>', 'txtMonto=<%=prestamo.getMontoSolicitado().intValue()%>');"/></td>	
			  	</tr>
			  <% } %>
			</tbody>
		  </table>
		  </div>
	</div>

	<script type="text/javascript">
		function btnConfirmRechazar(servlet, btnRechazar, txtId) {
			if (confirm("Estas seguro que desea rechazar el prestamo")) {
				window.location.href = servlet + "?" + btnRechazar + "&" + txtId;
			}
			return false;
		}
		function btnConfirmAprobar(servlet, btnAlta, txtId, txtIdCuenta, txtMonto) {
			if (confirm("Estas seguro que desea aprobar el prestamo")) {
				window.location.href = servlet + "?" + btnAlta + "&" + txtId + "&" + txtIdCuenta + "&" + txtMonto;
			}
			return false;
		}
		</script>

	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>