<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Baja de Alumno</title>
</head>
<link rel="stylesheet" href="./style.css">
<body>
	<h1>Formulario de baja de alumnado</h1>
	<form action="/AltaConsulta/begin" method="get">
		<label for="dname">Nombre:</label>
		<input type="text" name="dname"><br>
		<label for="dlastName">Apellido:</label>
		<input type="text"
			name="dlastName"><br> <label for="dgroup">Grupo:</label>
		<select id="group" name="dgroup">
			<option value="1DAW">1º de DAW</option>
			<option value="1ASIR">1º de ASIR</option>
			<option value="2DAW">2º de DAW</option>
			<option value="2ASIR">2º de ASIR</option>
		</select> <input type="submit" value="Enviar">
	</form>
	<button onclick="window.location.href='/AltaConsulta/begin'">Atras</button>
	<p>
		Contador:
		<c:out value="${contador}" />
	</p>
</body>
</html>