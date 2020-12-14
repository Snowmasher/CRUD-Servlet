<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Alumnos</title>
        <link rel="stylesheet" href="./style.css">
    </head>
    <body>
		<h1>Lista de alumnos</h1>
		<table>
			<tr>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Edad</th>
				<th>Grupo</th>
			</tr>
   			<c:forEach var="alum" items="${alumnos}">
   				<tr>
        			<td><c:out value="${alum.name}"/></td>
        			<td><c:out value="${alum.lastName}"/></td>
        			<td><c:out value="${alum.age}"/></td>
        			<td><c:out value="${alum.group}"/></td>
        		</tr>
   			</c:forEach>
   		</table>
   		<button onclick="window.location.href='/AltaConsulta/begin'">Atras</button>
   		<p>Contador: <c:out value="${contador}"/></p>
  </body><br></html>