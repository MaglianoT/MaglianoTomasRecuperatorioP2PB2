package ar.edu.unlam.pb2;

public class Quimica extends Libro implements Vendible  {
	
	public Quimica(Integer codigo, TipoDeLibro tipo, String nombre, String autor, Double precio) {
		super(codigo, tipo, nombre, autor, precio);
	}

}
