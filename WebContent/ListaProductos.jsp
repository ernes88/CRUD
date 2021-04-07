<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,crud.productos.* " %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">

.cabecera{
	border-bottom:solid #F00 1px
}

</style>

</head>
<%
//Obtener en la página JSP la lista de productos que envia el controlador en LISTAPRODUCTOS. 
//El controlador uso setAttribute para crear LISTAPRODUCTOS, y ahora se usa getAttribute para obtener una estructura de datos List 
//Hay que hacer un down Casting pues getAttribute develve un objeto 
List<Productos>listaP=(List<Productos>)request.getAttribute("LISTAPRODUCTOS");
%>

<body>
	<table>

	<tr>

	<td class="cabecera">Código Artículo</td>
	<td class="cabecera">Sección</td>
	<td class="cabecera">Nombre Artículo</td>
	<td class="cabecera">Fecha</td>
	<td class="cabecera">Precio</td>
	<td class="cabecera">Importado</td>
	<td class="cabecera">País de Origen</td>

	</tr>

	<%	for(Productos tempProd:listaP){	%>

	<tr>
	<td> <%= tempProd.getCodigoArticulo() %></td>
	<td> <%= tempProd.getSeccion() %></td>
	<td> <%= tempProd.getNombreArticulo() %></td>
	<td> <%= tempProd.getFecha() %></td>
	<td> <%= tempProd.getPrecio() %></td>
	<td> <%= tempProd.getImportado() %></td>
	<td> <%= tempProd.getPaisOrigen() %></td>

	</tr>
		
	<% } %>

	</table>

</body>
</html>