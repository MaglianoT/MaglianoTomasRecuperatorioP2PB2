package ar.edu.unlam.pb2;

import java.util.HashSet;
import java.util.Set;

public class Persona {

	private Double dinero;
	private String nombre;
	private String apellido;
	private Set <Libro> librosEnPosesion;

	public Persona(String nombre, String apellido, Double dinero) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.apellido = apellido;
		this.dinero = dinero;
		this.librosEnPosesion = new HashSet <Libro> ();
	}

	public Libro verLibro(Integer codigo) {
		for(Libro actual: librosEnPosesion) {
			if(actual.getCodigo().equals(codigo)) {
				return actual;
			}
		}
		
		return null;
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Set <Libro> getLibrosEnPosesion() {
		return librosEnPosesion;
	}

}
