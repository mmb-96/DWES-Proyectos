<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Empleado</title>
</head>
<body>
<form method ="post" action="ModificarDB">
	<input type="text" name="dni" required placeholder="DNI empleado" disabled="disabled">
	<input type="text" name="nombre" required placeholder="DNI empleado" disabled="disabled">  
	<input type="text" name="sexo" required placeholder="DNI empleado" disabled="disabled">  
	<input type="text" name="categoria" required placeholder="DNI empleado" disabled="disabled">  
	<input type="text" name="anyos" required placeholder="DNI empleado" disabled="disabled">     
    <button type="submit">Enviar</button>
</form>
<a href="index.html"><input type="button" value="Volver."></input></a><br>
</body>
</html>