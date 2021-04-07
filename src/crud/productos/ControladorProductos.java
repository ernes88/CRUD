package crud.productos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//creación de una variable encapsulada de tipo ModeloProductos
	private ModeloProductos modeloProductos;
	
	//Directiva Resource
	@Resource(name="jdbc/Productos")
		
	//Crear una variable de tipo DataSource
	private DataSource objDataSource;
	
	//Crear el método init()
	@Override
	public void init() throws ServletException {
		super.init();
		
		try{
			//Conectar con el modelo, para lo cual iniciamos el objeto ModeloProductos.
			modeloProductos=new ModeloProductos(objDataSource);
		}
		catch(Exception e){
			throw new ServletException(e);
		}			
	}
	
//-------------------------------------------------Método doGet()-------------------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Leer el parámetro que le llega del formulario InsertaProducto
		String elComando=request.getParameter("instruccion");
		
		//Si no se envia el parámetro listar productos
		if(elComando==null){
			elComando="listar";
		}
		
		//En función de si ha recibido el parámetro o no, redirigir el flujo de ejecución al método adecuado, ya sea obtenerProdctos() o insertarProductos()
		switch(elComando){
		
		case "listar":
			try{
				obtenerProductos(request,response);
			}	
			catch(Exception e){
				System.out.println("Excepción en obtenerProductos()");
				e.printStackTrace();
			}
			break;
				
		case "insertar":
			try{
				agregarProductos(request,response);	
			}
			catch (Exception e) {
				System.out.println("Excepción en método agregarProductos");
				e.printStackTrace();		
			}
			break;
			
		case "actualizar":
			try {
				cargaProductoAActualizar(request,response);
			} 
			catch (Exception e) {
				System.out.println("Excepción en método cargaProductoAActualizar");
				e.printStackTrace();		
			}
			break;
			
		case "actualizarProducto":
			try {
				actualizaProductoEnBD(request,response);
			} 
			catch (Exception e) {
				System.out.println("Excepción en método actualizaProductoEnBD");
				e.printStackTrace();
			}
			break;	
		
		case "eliminar":
			try{
				eliminarProducto(request,response);
			}
			catch(Exception e){
				System.out.println("Excepción en método eliminarProducto");
				e.printStackTrace();
			}
			break;
		
		default:
			try{
				obtenerProductos(request,response);
			}	
			catch(Exception e){
				System.out.println("Excepción en obtenerProductos()");
				e.printStackTrace();
			}
		}
	}

//--------------------------------------------Metodo eliminarProducto---------------------------------------------------	
	private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Leer la información del codigo del articulo a eliminar, que le viene del formulario ListaProductosJSPTags.jsp 
		String codArt=request.getParameter("cArticulo");
		
		//Llamar a un método de ModeloProductos que se encargará de eliminar de la base de datos el registro cuyo codigo articulo sea el que se le pasa como argumento.
		modeloProductos.eliminarProductos(codArt);
		
		//Volver a listar la tabla de productos para comprobar que ese objeto ha sido insertado
		obtenerProductos(request,response);	
	}
	
//--------------------------------------------Metodo actualizaProductoEnBD----------------------------------------------
	private void actualizaProductoEnBD(HttpServletRequest request, HttpServletResponse response) throws Exception{
	//Este código es el mismo que el del método agregarProductos(). Lo que si va a diferir en la llamada a un nuevo método que será del modelo y se encargará  de actualizar la tabla.
	//Leer la información que le viene del formulario Inserta_producto
		String cArt=request.getParameter("cArt");
		String seccion=request.getParameter("seccion");
		String nArt=request.getParameter("nArt");
		String importado=request.getParameter("importado");
		String paisOrigen=request.getParameter("pOrigen");
				
		//para la obtención de una variable double a partir de un String 
		double precio=Double.parseDouble(request.getParameter("precio"));
				
		//obtención de un objeto Date a partir de un String.
		SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
		Date fecha=null;
		
		try {
			fecha=formatoFecha.parse(request.getParameter("fecha"));
		} 
			catch (ParseException e) {	
			e.printStackTrace();
		}
				
		//Crear un objeto de tipo Productos con esta info.
		Productos productoActualizado=new Productos(cArt,seccion,nArt,precio,fecha,importado,paisOrigen);
				
		//Enviar el objeto al Modelo y actualizar el objeto Producto en la base de datos.
		modeloProductos.actualizarProducto(productoActualizado);
					
		//Volver a listar la tabla de productos para comprobar que ese objeto ha sido insertado
		obtenerProductos(request,response);		
	}

//-----------------------------------Método cargaProductoAActualizar---------------------------------------------------- 
	private void cargaProductoAActualizar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Leer el codigo del articulo que le viene del formulario ListaProductosJSPTags
		String codigoArticulo=request.getParameter("cArticulo");
		
		//Comunicar con el modelo utilizando un método para que este realice una consulta a la base de datos utilizando como criterio el cArt que acabamos arriba de capturar, de modo que obtengamos el registro asociado a ese cArt, o sea que devuelva un obeto de tipo Productos, el cual capturamos en una objeto Productos
		Productos objProductos=modeloProductos.getProducto(codigoArticulo);
	
		//Establecer el atributo en el objeto request que contendrá al objeto Productos.Acá va info relacionada con el registro para el campo clave
		request.setAttribute("productoAActualizar", objProductos);
		
		//Enviar producto al formulario de actualizarProducto.jsp para lo cual se crea el objeto RequestDispatcher para poder comunicar con el JSP		
		RequestDispatcher objRequestDispatcher=request.getRequestDispatcher("/ActualizarProducto.jsp");
						
		//enviar la información al JSP utilizando el método forward().
		objRequestDispatcher.forward(request, response);	
	}

//------------------------------------Método agregarProductos()---------------------------------------------------------
	//Método que agrega un nuevo producto a la tabla productos de la base de datos
	private void agregarProductos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Leer la información que le viene del formulario Inserta_producto
		String cArt=request.getParameter("cArt");
		String seccion=request.getParameter("seccion");
		String nArt=request.getParameter("nArt");
		String importado=request.getParameter("importado");
		String paisOrigen=request.getParameter("pOrigen");
		
		//para la obtención de una variable double a partir de un String 
		double precio=Double.parseDouble(request.getParameter("precio"));
		
		//obtención de un objeto Date a partir de un String.
		SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
		Date fecha=null;
		
		try {
			fecha=formatoFecha.parse(request.getParameter("fecha"));
		} 
		catch (ParseException e) {	
			e.printStackTrace();
		}
		
		//Crear un objeto de tipo Productos con esta info.
		Productos nuevoProducto=new Productos(cArt,seccion,nArt,precio,fecha,importado,paisOrigen);
		
		//Enviar el objeto al Modelo e insertar el objeto Producto en la base de datos.
		modeloProductos.agregarNuevoProducto(nuevoProducto);
			
		//Volver a listar la tabla de productos para comprobar que ese objeto ha sido insertado
		obtenerProductos(request,response);
	}

//---------------------------------Método obtenerProductos()------------------------------------------------------------
	//Método que lista los productos que hay en la tabla de productos de la base de datos
	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) throws Exception{
	//Me creo una lista para contener los elements de la listaProductos del modelo. Utilizo el objeto modeloProductos que me he creado aca en el controlador para poder llamar a este método del modelo.
		List<Productos>productos;		
	
	//Obtener la lista de productos del modelo
		
		productos=modeloProductos.getProductos();
			
	//Agregar la lista de producto al Request
		//se almacena en el objeto request la información del ArrayList productos.
		request.setAttribute("LISTAPRODUCTOS",productos);
			
	//Enviar el Request a la página JSP		
		//creación del objeto RequestDispatcher para poder comunicar con el JSP
		RequestDispatcher objRequestDispatcher=request.getRequestDispatcher("/ListaProductosJSPTags.jsp");
				
	//enviar la información al JSP utilizando el método forward().
		objRequestDispatcher.forward(request, response);		
	}
}
