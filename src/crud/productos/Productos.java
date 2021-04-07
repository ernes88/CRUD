package crud.productos;

import java.util.*;

public class Productos {

	//campos de clase encapsulados
	private String codigoArticulo;
	private String seccion;
	private String nombreArticulo;
	private double precio;
	private Date fecha;
	private String importado;
	private String paisOrigen;
		
	//constructor con campo clave
	public Productos(String codigoArticulo, String seccion, String nombreArticulo, double precio, Date fecha,
			String importado, String paisOrigen) {
		this.codigoArticulo = codigoArticulo;
		this.seccion = seccion;
		this.nombreArticulo = nombreArticulo;
		this.precio = precio;
		this.fecha = fecha;
		this.importado = importado;
		this.paisOrigen = paisOrigen;
	}
	
	//constructor sin campo clave
	public Productos(String seccion, String nombreArticulo, double precio, Date fecha, String importado,
			String paisOrigen) {
		this.seccion = seccion;
		this.nombreArticulo = nombreArticulo;
		this.precio = precio;
		this.fecha = fecha;
		this.importado = importado;
		this.paisOrigen = paisOrigen;
	}

	//Getters y Setters
	public String getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getImportado() {
		return importado;
	}

	public void setImportado(String importado) {
		this.importado = importado;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	@Override
	public String toString() {
		return "Productos [codigoArticulo=" + codigoArticulo + ", seccion=" + seccion + ", nombreArticulo="
				+ nombreArticulo + ", precio=" + precio + ", fecha=" + fecha + ", importado=" + importado
				+ ", paisOrigen=" + paisOrigen + "]";
	}
}
