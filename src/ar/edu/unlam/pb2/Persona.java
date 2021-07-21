package ar.edu.unlam.pb2;

import java.util.HashSet;
import java.util.Set;

public class Persona {

	private double Dinero;
	public Set <Libro> librosEnPosesion;

	

	public Persona(String string, String string2, double d) {
		// TODO Auto-generated constructor stub
		this.librosEnPosesion = new HashSet <Libro> ();
	}

	public Libro verLibro(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getDinero() {
		return Dinero;
	}

	public void setDinero(double dinero) {
		Dinero = dinero;
	}

}
