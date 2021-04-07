package crud.productos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ServletPruebas
 */
@WebServlet("/ServletPruebas")
public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    //Directiva Resource
	@Resource(name="jdbc/Productos")
	
	//Crear una variable de tipo DataSource
	private DataSource objDataSource;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPruebas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter objPrintWriter=response.getWriter();
		response.setContentType("text/Plain");
		
		//Crear conexion con la base de datos
		Connection objConnection=null;
		Statement objStatement=null;
		ResultSet objResultSet=null;
		String consulta="SELECT * FROM PRODUCTOS";
		
		try{
			objConnection=objDataSource.getConnection();
			objStatement=objConnection.createStatement();
			objResultSet=objStatement.executeQuery(consulta);
			
			while(objResultSet.next()){
				String nombre_articulo=objResultSet.getString(3);
				objPrintWriter.println(nombre_articulo);
			}
		}
		catch(Exception e){
			System.out.println("Excepción");
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
