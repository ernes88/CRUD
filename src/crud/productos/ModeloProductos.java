package crud.productos;
import java.sql.*;
import java.util.*;
import java.util.Date;		//Existe una clase Date tanto en el paquete java.util, como en el paquete java.sql. Si importamos con las dos lineas de arriba estos dos paquetes completamennte y no icluimos esta linea donde se hace el comentario, Ecipse no sabe a cual paquete pertenece la clase que estamos usando abajo en el codigo, por tanto hay que importar explisitamente esta clase del paquete java.util.
//import javax.annotation.Resource;
import javax.sql.DataSource;

public class ModeloProductos {
	
	//Crear una variable de tipo DataSource
	private DataSource objDataSource;
	
	public ModeloProductos(DataSource objDataSource){
		this.objDataSource=objDataSource;
	}
	
//--------------------------------------------Método getProductos()-----------------------------------------------------
	//método que se encargará de obtener los registros de la tabla, como pueden ser varios y no sabemos cuantos devolverá una List, y que será del tipo de dato Productos, que es nuestra clase qque modela esos datos parapoder manipularlos en el programa  
	public List<Productos> getProductos() throws Exception{
		List<Productos>listaProductos=new ArrayList<Productos>();
		Connection objConnection=null;
		Statement objStatement=null;
		ResultSet objResultSet=null;
		String consulta="SELECT * FROM PRODUCTOS";
		
		try{
			objConnection=objDataSource.getConnection();
			objStatement=objConnection.createStatement();
			objResultSet=objStatement.executeQuery(consulta);
			
			while(objResultSet.next()){
				
				//El ResultSet contendra una serie de filas donde cada una será una de las filas de la tablla de la base de datos, y cada una de estas filas se modela en la aplicación con un objeto Productos
				///capturo cada campo para cada fila en variables idependientes que coinciden con los tipos de datos de la tabla
				String codigoArticulo=objResultSet.getString("CODIGOARTICULO");
				String seccion=objResultSet.getString("SECCION");
				String nombreArticulo=objResultSet.getString("NOMBREARTICULO");
				double precio=objResultSet.getDouble("PRECIO");
				Date fecha=objResultSet.getDate("FECHA");
				String importado=objResultSet.getString("IMPORTADO");
				String paisOrigen=objResultSet.getString("PAISDEORIGEN");
				
				//utilizo el constructor de la clase Productos para construir el objeto Productos correspondiente a cad fila del ResultSet y lo guardo en la Lista
				listaProductos.add(new Productos(codigoArticulo,seccion,nombreArticulo,precio,fecha,importado,paisOrigen));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//Cerrar los recursos abiertos
			objConnection.close();
			objStatement.close();
			objResultSet.close();
		}
			
		return listaProductos;
	}
	
//-----------------------------------------Método agregarNuevoProducto()------------------------------------------------
	//Método que recibirá un objeto ModeloProductos desde el Controlador que contendrá los valores de los 7 campos insertados por el usuario en el formulario emergente InsertaProductos y los procese aca, para pposteniermente insertarlos en la base de datos.
	public void agregarNuevoProducto(Productos nuevoProducto) throws Exception{
		//Inicialización de las variables
		Connection objConnection=null;
		PreparedStatement objPreparedStatement=null;
		
		try{
			//Obtener la conexión con la base de datos
			objConnection=objDataSource.getConnection();
			
			//Construir la instrucción sql que inserte el nuevo registro en la base de datos
			String productoNuevo="INSERT INTO PRODUCTOS (CODIGOARTICULO,SECCION,NOMBREARTICULO,PRECIO,FECHA,IMPORTADO,PAISDEORIGEN) VALUES(?,?,?,?,?,?,?)";
			
			//Construir la consulta preparada
			objPreparedStatement=objConnection.prepareStatement(productoNuevo);
			
			//Establecer los parámetros para la consulta preparada
			objPreparedStatement.setString(1,nuevoProducto.getCodigoArticulo());
			objPreparedStatement.setString(2,nuevoProducto.getSeccion());
			objPreparedStatement.setString(3,nuevoProducto.getNombreArticulo());
			objPreparedStatement.setDouble(4,nuevoProducto.getPrecio());
			
			//En el caso del parámetro Date ocurre que el método getFecha() de la clase Productos, develve un objeto de tipo Date pero del paquete java.util.
			//y en el caso de setDate() espera que le pasen un parámetro de tipo date pero del paquete java.sql.
			//Solución:Convertir el Date que devuelve getFecha() que trabaja con el formato año-mes-dia-hora, al objeto Date que espera setDate() que trabaja con la fecha solamete y no con la hora
			
			java.util.Date utilDate=nuevoProducto.getFecha();						//con esta instrucción meto el objeto de tipo fecha en un objeto de tipo java.util.Date. 
			java.sql.Date fechaConvertida=new java.sql.Date(utilDate.getTime());	//se crea un objeto de tipo java.sql.Date y se le pasa como argumento la hora, de esta forma este objeto tendra innfo ahora de la hora.
			
			objPreparedStatement.setDate(5,fechaConvertida);						
			
			objPreparedStatement.setString(6,nuevoProducto.getImportado());
			objPreparedStatement.setString(7,nuevoProducto.getPaisOrigen());
			
			//Ejecutar consulta sql.
			objPreparedStatement.execute();		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//Cerrar los recursos abiertos
			objConnection.close();
			objPreparedStatement.close();
		}
	}

//----------------------------------------------Método getProducto()----------------------------------------------------
	public Productos getProducto(String cArt) throws Exception{
		//Inicialización de las variables
		Connection objConnection=null;
		PreparedStatement objPreparedStatement=null;
		ResultSet objResultSet=null;
		String codigoArticulo=null;
		String seccion=null;
		String nombreArticulo=null;
		double precio=0;
		Date fecha=null;
		String importado=null;
		String paisdeOrigen=null;
		Productos productoRecuperado=null;
		
		try{
			//Iniciar la conexión
			objConnection=objDataSource.getConnection();
			
			//Construir consulta preparada sql utilizando el código artículo que le lega como argumento
			String cargaRegistro="SELECT * FROM PRODUCTOS WHERE CODIGOARTICULO=?";
			
			//Creación de la consulta preparada
			objPreparedStatement=objConnection.prepareStatement(cargaRegistro);
			
			//Establecer parámetro de la consulta preparada
			objPreparedStatement.setString(1,cArt);
			
			//Ejecutar la consulta sql preparada y guardar info del registro en un Resultset
			objResultSet=objPreparedStatement.executeQuery();
			
			//Recorrer el ResultSet y extraer la información del registro que nos devolvió la consulta preparada en variables
			if(objResultSet.next()){		
				codigoArticulo=objResultSet.getString("CODIGOARTICULO");
				seccion=objResultSet.getString("SECCION");
				nombreArticulo=objResultSet.getString("NOMBREARTICULO");
				precio=objResultSet.getDouble("PRECIO");
				fecha=objResultSet.getDate("FECHA");
				importado=objResultSet.getString("IMPORTADO");
				paisdeOrigen=objResultSet.getString("PAISDEORIGEN");
			}
			else{
				throw new Exception ("No se ha encontrado un producto con codigoArticulo= "+codigoArticulo);
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//Cerrar los recursos abiertos
			objConnection.close();
			objPreparedStatement.close();
			objResultSet.close();
		}
		
		//Construir y retornar un objeto Productos con la info capturada de la base de datos para el registro en cuestion que nos interesa
		productoRecuperado=new Productos(codigoArticulo,seccion,nombreArticulo,precio,fecha,importado,paisdeOrigen);	
		
		return productoRecuperado;
	}

//-----------------------------------------Método actualizarProducto()--------------------------------------------------
	public void actualizarProducto(Productos productoActualizado) throws Exception{
	//Inicialización de las variables
		Connection objConnection=null;
		PreparedStatement objPreparedStatement=null;
				
		try{
			//Obtener la conexión con la base de datos
			objConnection=objDataSource.getConnection();
					
			//Construir la instrucción sql que inserte el nuevo registro en la base de datos
			String productoAActualizar="UPDATE PRODUCTOS SET SECCION=?,NOMBREARTICULO=?,PRECIO=?,FECHA=?,IMPORTADO=?,PAISDEORIGEN=? WHERE CODIGOARTICULO=?";
					
			//Construir la consulta preparada
			objPreparedStatement=objConnection.prepareStatement(productoAActualizar);
					
			//Establecer los parámetros para la consulta preparada
			objPreparedStatement.setString(1,productoActualizado.getSeccion());
			objPreparedStatement.setString(2,productoActualizado.getNombreArticulo());
			objPreparedStatement.setDouble(3,productoActualizado.getPrecio());
			
			//En el caso del parámetro Date ocurre que el método getFecha() de la clase Productos, develve un objeto de tipo Date pero del paquete java.util.
			//y en el caso de setDate() espera que le pasen un parámetro de tipo date pero del paquete java.sql.
			//Solución:Convertir el Date que devuelve getFecha() que trabaja con el formato año-mes-dia-hora, al objeto Date que espera setDate() que trabaja con la fecha solamete y no con la hora
					
			java.util.Date utilDate=productoActualizado.getFecha();						//con esta instrucción meto el objeto de tipo fecha en un objeto de tipo java.util.Date. 
			java.sql.Date fechaConvertida=new java.sql.Date(utilDate.getTime());		//se crea un objeto de tipo java.sql.Date y se le pasa como argumento la hora, de esta forma este objeto tendra innfo ahora de la hora.
					
			objPreparedStatement.setDate(4,fechaConvertida);						
					
			objPreparedStatement.setString(5,productoActualizado.getImportado());
			
			objPreparedStatement.setString(6,productoActualizado.getPaisOrigen());
			
			objPreparedStatement.setString(7,productoActualizado.getCodigoArticulo());
					
			//Ejecutar consulta sql.
			objPreparedStatement.execute();		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//Cerrar los recursos abiertos
			objConnection.close();
			objPreparedStatement.close();	
		}
	}
//-----------------------------------------Método eliminarProducto()----------------------------------------------------	
	//Método que se encargará de eliminar un registro de la tabla Productos de la base de datos.
	public void eliminarProductos(String cArt) throws Exception{
		
		//Inicialización de las variables
		Connection objConnection=null;
		PreparedStatement objPreparedStatement=null;
						
		try{
			//Obtener la conexión con la base de datos
			objConnection=objDataSource.getConnection();
		
			//Construir la instrucción sql que elimina el registro en la tabla
			String eliminarProducto="DELETE FROM PRODUCTOS WHERE CODIGOARTICULO=?";
			
			//Construir la consulta preparada
			objPreparedStatement=objConnection.prepareStatement(eliminarProducto);
			
			//Establecer el parámetro de la consulta preparada
			objPreparedStatement.setString(1,cArt);
			
			//Ejecutar consulta sql.
			objPreparedStatement.execute();		
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//Cerrar los recursos abiertos
			objConnection.close();
			objPreparedStatement.close();	
		}	
	}	
}
