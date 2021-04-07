<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Productos</title>

<style type="text/css">

.cabecera{
	font-size:1.2em;
	font-weight:bold;
	color:#FFFFFF;
	background-color:#08088A;
	text-align:cennter;	
}

.filas{
	text-align:center;
	backgroung-color:#5882FA;
}

/* Se flotea la tabla para que el boton que hicimos abajo aparezca antes de la tabla */
table{
	float:left;
}

/* Crear un selector de id que debe llamarse igal al nombre del botón */
#contenedorBotton{
	margin-left:1000px;		/* el botón se situará 1000 pixels a la derecha del maegen izquierdo de la pagina.*/
}

</style>

</head>

<body>
<h3><b></b>Listado de Productos</h1>
	
	<table border="1">
	<tr>
	<td class="cabecera">Código Artículo</td>
	<td class="cabecera">Sección</td>
	<td class="cabecera">Nombre Artículo</td>
	<td class="cabecera">Precio</td>
	<td class="cabecera">Fecha</td>
	<td class="cabecera">Importado</td>
	<td class="cabecera">País de Origen</td>
	<td class="cabecera">Acción</td>
	</tr>
	
	<c:forEach var="tempProd" items="${LISTAPRODUCTOS}">
	
	<!------------------------Link para cada producto con su campo clave para funcionalidad de actualizar------------------------------------->
	<!--Se crea una etiqueta de tipo link enlazada con el Controlador-->
	<c:url var="linkTempActualizar" value="ControladorProductos">
	
		<c:param name="instruccion" value="actualizar"></c:param>
		<c:param name="cArticulo" value="${tempProd.codigoArticulo}"></c:param>
		
	</c:url>
	<!----------------------------------------------------------------------------------------------------------------->
	
	<!------------------------Link para cada producto con su campo clave para funcionalidad de eliminar------------------------------------->
	<!--Se crea una etiqueta de tipo link enlazada con el Controlador-->
	<c:url var="linkTempEliminar" value="ControladorProductos">
	
		<c:param name="instruccion" value="eliminar"></c:param>
		<c:param name="cArticulo" value="${tempProd.codigoArticulo}"></c:param>
		
	</c:url>
	<!----------------------------------------------------------------------------------------------------------------->
	
	<tr>
	<td class="filas"> ${tempProd.codigoArticulo}</td>
	<td class="filas"> ${tempProd.seccion}</td>
	<td class="filas"> ${tempProd.nombreArticulo}</td>
	<td class="filas"> ${tempProd.precio}</td>
	<td class="filas"> ${tempProd.fecha}</td>
	<td class="filas"> ${tempProd.importado}</td>
	<td class="filas"> ${tempProd.paisOrigen}</td>	
	<td class="filas"> <a href="${linkTempActualizar}"> Actualizar </a> &nbsp; &nbsp; <a href="${linkTempEliminar}"> Eliminar </a></td>	
	</tr>
	</c:forEach>
	
	</table>
	
	<!-- Se crea un contenedor y dentro del mismo se crea el botón para abrir el formulario donde se introducirá la info para el registro a insertar -->
	<!-- A este botón se le agrega un evento de tipo click para que cuando pulsemos en el mismo abra el formulario Inserta_producto.jsp que es donde se insertarán los datos. -->
	
	<div id="contenedorBoton">
		
		<input type="button" value="Insertar Registro" onclick="window.location.href='Inserta_producto.jsp'"/>
	
	</div>
</body>
</html>