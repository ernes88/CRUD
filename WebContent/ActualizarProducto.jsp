<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Actualizar Producto</title>
</head>
<body>

<h1 style="text-align:center">  Actualizar Producto </h1>
<form name="form1" method="get" action="ControladorProductos">

<!-- Se crea un campo oculto y se le da un nombre y un valor. Esto se pasar� al Controlador, el cual lo usar� para controlar si actualiza alg�n campo del formulario o si hace alguna otra tarea -->
<input type="hidden" name="instruccion" value="actualizarProducto">

<!-- Se crea otro campo oculto correspondiente con el c�digo del art�culo que el usuario escogio en ListaProductosJSPTags cuando puls� en uno de los enlaces. Eto se hace pues es necesario conocer aca que codigoArticulo fue pulsado porque eventualmente la info que se modifique ac� ser� en viada al modelo.-->
<input type="hidden" name="cArt" value="${productoAActualizar.codigoArticulo}">		<!-- como se ve el atributo que haya sido establecido con el setAttribute(), o sea productoAActualizar,puede acceder a las propiedades del objeto Productos directamente. -->
	
	<table width="50%" border="0">
	<!-- En el caso de la funci�n de actualizar no se deja al usuario manipular el campo clave -->
	<!-- En el caso de este formulario, y a diferencia del formulario de inserta_producto.jsp, ac� deben aparecer los cuadros de texto llenos -->
		<tr>
			<td width="27%">Secci�n</td>
			<td width="73%"><label for="seccion"></label>
			<input type="text" name="seccion" id="seccion" value="${productoAActualizar.seccion}"> </td>
		</tr>
		<tr>
			<td width="27%">Nombre Art�culo</td>
			<td width="73%"><label for="nArt"></label>
			<input type="text" name="nArt" id="nArt" value="${productoAActualizar.nombreArticulo}"> </td>
		</tr>
		<tr>
			<td width="27%">Fecha</td>
			<td width="73%"><label for="fecha"></label>
			<input type="text" name="fecha" id="fecha" value="${productoAActualizar.fecha}"> </td>
		</tr>
		<tr>
			<td width="27%">Precio</td>
			<td width="73%"><label for="precio"></label>
			<input type="text" name="precio" id="precio" value="${productoAActualizar.precio}"> </td>
		</tr>
		<tr>
			<td width="27%">Importado</td>
			<td width="73%"><label for="importado"></label>
			<input type="text" name="importado" id="importado" value="${productoAActualizar.importado}"> </td>
		</tr>
		<tr>
			<td width="27%">Pa�s de Origen</td>
			<td width="73%"><label for="pOrigen"></label>
			<input type="text" name="pOrigen" id="pOrigen" value="${productoAActualizar.paisOrigen}"> </td>
		</tr>
		<tr>
			<td><input type="submit" name="envio" id="envio" value="Actualizar"></td>
			<td><input type="reset" name="borrar" id="borrar" value="Restablecer"></td>
		</tr>	
	</table>
</form>
</body>
</html>