<%-- index.jsp (proyecto PasoDeCadena) --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="daw1.Gato"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="estilos.css" />
<title>Varios Ejemplos</title>
</head>
<body>
	<h1>Pasando una cadena de caracteres</h1>
	<form method="post" action="frase.jsp">
		Introduzca el nombre de una fruta: <input type="text" name="cadenaIntro">
		<input type="submit" value="OK">
	</form>
	<h1>Incremento en 5</h1>
	<form method="get" action="incrementa5.jsp">
		Introduzca un número (puede tener decimales): <input type="text" name="numeroIntro">
		<input type="submit" value="OK">
	</form>
	<h1>Supercalculadora</h1>
	<form method="get" action="resultado.jsp">
		x <input type="text" name="x" /></br> 
		y <input type="text" name="y" /></br>
		<input type="submit">
	</form>
	<h1>Animales</h1>
	<form method="post" action="animales.jsp">
		Seleccione animal a visualizar
		<select name="animal">
			<option>Gato</option>
			<option>Caracol</option>
		</select>
		</br>
		Número de animales <input type="text" name="numero" size="3">
		</br>
	<input type="submit">
	</form>
	<hr>
	<h1>Gatos con clase</h1>
	<hr>
	<%
		Gato g1 = new Gato("Pepe", "gato.jpg");
		Gato g2 = new Gato("Garfield", "garfield.jpg");
		Gato g3 = new Gato("Tom", "tom.png");
		out.println(g1);
		out.println(g2);
		out.println(g3);
		out.println(g1.maulla());
	    out.println(g2.come("sardinas"));
	%>
</body>
</html>