<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buscar empleado para modificar - Nomina</title>
</head>
<body>
<form method ="post" action="ModificarEmpleado.html">
	<input type="text" name="dni" required placeholder="DNI empleado">
	<input value="Recuperar empleados" name="action" style="visibility: hidden; display: none;">    
    <button type="submit">Buscar</button>
</form>
<a href="index.html"><input type="button" value="Volver."></input></a><br>
</body>
</html>