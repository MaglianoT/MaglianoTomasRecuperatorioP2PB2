package ar.edu.unlam.pb2;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
	
	private Map <Integer, Libro> librosDisponibles;

	public Biblioteca(String string) {
		// TODO Auto-generated constructor stub
		this.librosDisponibles = new HashMap <Integer, Libro> ();
	}

	public void agregarLibro(Libro libro) {
		// TODO Auto-generated method stub
		
	}

	public Map <Integer, Libro> getLibrosDisponibles() {
		return librosDisponibles;
	}

	public void prestarLibro(Persona claudia, int i) {
		// TODO Auto-generated method stub
		
	}

	public void venderLibro(int i, Persona maxi) {
		// TODO Auto-generated method stub
		
	}

}
