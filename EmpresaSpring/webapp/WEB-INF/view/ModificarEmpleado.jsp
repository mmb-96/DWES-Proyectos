<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Empleado</title>
</head>
<body>
<form method ="post" action="EmpleadoModificado.html">
	<input type="text" name="dni" required placeholder="DNI empleado" readonly value="${empleado.dni}">
	<input type="text" name="nombre" required placeholder="Nombre empleado" value="${empleado.nombre}">  
	<input type="text" name="sexo" required placeholder="Sexo empleado" value="${empleado.sexo}">  
	<input type="text" name="categoria" required placeholder="Categoria empleado" value="${empleado.categoria}">  
	<input type="text" name="anyos" required placeholder="Años empleado" value="${empleado.anyos}">
	<input value="Modificar empleados" name="action" style="visibility: hidden; display: none;">    
    <button type="submit">Enviar</button>
</form>
<a href="index.html"><input type="button" value="Volver."></input></a><br>
</body>
</html>