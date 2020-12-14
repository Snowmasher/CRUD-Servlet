<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
<link rel="stylesheet" href="./style.css">
</head>
<body>
	<h1>Inicio</h1>
	<form action="/AltaConsulta/begin" method="get">
	
		<label for="alta">Alta de Alumno</label>
		<input type="radio" name="action" value="alta"><br>
		
		<label for="consulta">Consulta de Alumnos</label>
		<input type="radio" name="action" value="consulta"><br>
		
		<label for="consulta">Baja de Alumnos</label>
		<input type="radio" name="action" value="eliminar"><br><br>
		
		<input type="submit" value="Continuar">
		
	</form>
	
	<p>Contador: <c:out value="${contador}"/></p>
</body>
</html>