<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista empleados - Nomina</title>
</head>
<body>
<table>
	<thead>
    	<tr>
        	<th>Nombre</th>
            <th>DNI</th>
            <th>Sexo</th>                
            <th>Categoría</th>
            <th>Años trabajados</th>
		</tr>
	</thead>
    <tbody>
    <c:forEach items="${empleados}" var="empleado">
    	<tr>
        	<td>${empleado.nombre}</td>
            <td>${empleado.dni}</td>
            <td>${empleado.sexo}</td>
            <td>${empleado.categoria}</td>
            <td>${empleado.anyos}</td> 
       </tr>
	</c:forEach>                      
	</tbody>
</table>
<br>
<br>
<a href="index.html"><input type="button" value="Volver."></input></a><br>
</body>
</html>