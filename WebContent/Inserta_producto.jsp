<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insertar Producto</title>
</head>
<body>

<h1 style="text-align:center">  Insertar Registros </h1>
<form name="form1" method="get" action="ControladorProductos">

<!-- Se crea un campo oculto y se le da un nombre y un valor. Esto se pasará al Controlador, el cual lo usará para controlar si inserta los valores de los campos del formulario o si no hace nada -->
<input type="hidden" name="instruccion" value="insertar">
	<table width="50%" border="0">
		<tr>
			<td width="27%">Código Artículo</td>
			<td width="73%"><label for="cArt"></label>
			<input type="text" name="cArt" id="cArt"> </td>
		</tr>
		<tr>
			<td width="27%">Sección</td>
			<td width="73%"><label for="seccion"></label>
			<input type="text" name="seccion" id="seccion"> </td>
		</tr>
		<tr>
			<td width="27%">Nombre Artículo</td>
			<td width="73%"><label for="nArt"></label>
			<input type="text" name="nArt" id="nArt"> </td>
		</tr>
		<tr>
			<td width="27%">Fecha</td>
			<td width="73%"><label for="fecha"></label>
			<input type="text" name="fecha" id="fecha"> </td>
		</tr>
		<tr>
			<td width="27%">Precio</td>
			<td width="73%"><label for="precio"></label>
			<input type="text" name="precio" id="precio"> </td>
		</tr>
		<tr>
			<td width="27%">Importado</td>
			<td width="73%"><label for="importado"></label>
			<input type="text" name="importado" id="importado"> </td>
		</tr>
		<tr>
			<td width="27%">País de Origen</td>
			<td width="73%"><label for="pOrigen"></label>
			<input type="text" name="pOrigen" id="pOrigen"> </td>
		</tr>
		<tr>
			<td><input type="submit" name="envio" id="envio" value="Enviar"></td>
			<td><input type="reset" name="borrar" id="borrar" value="Restablecer"></td>
		</tr>	
	</table>
</form>
</body>
</html>