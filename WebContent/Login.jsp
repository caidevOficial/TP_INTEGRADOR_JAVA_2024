<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="LibreriasHead.html"></jsp:include>
<title>Login</title>
</head>
<body>
	<jsp:include page="MenuDefault.html"></jsp:include>

	<form action="servletLogin" method="post">
		<div class="container my-5">
			<div class="row">
				<div class="col"></div>
				<div class="col">
					<div class="input-group input-group-sm mb-3">
						<span class="input-group-text" id="inputGroup-sizing-sm">Mail</span>
						<input type="text" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm" name="mail"
							placeholder="Ingrese correo electronico"
							pattern="^((?!\.)[\w\-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$"
							title="Ingrese un correo valido">
					</div>
					<div class="input-group input-group-sm mb-3">
						<span class="input-group-text" id="inputGroup-sizing-sm">Contraseña</span>
						<input type="password" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-sm" name="password"
							placeholder="Ingrese su contraseña">
					</div>
					<div>
						<button class="btn btn-primary" type="submit" name="btnIngresar">Ingresar</button>
						<%
						if (request.getAttribute("errorLogin") != null) {
						%>
						<span><%=request.getAttribute("errorLogin").toString()%></span>
						<%
						}
						%>
					</div>
				</div>
				<div class="col"></div>
			</div>
		</div>
	</form>

	<jsp:include page="LibreriasBody.html"></jsp:include>
</body>
</html>