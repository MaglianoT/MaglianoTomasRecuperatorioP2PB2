package ar.edu.unlam.pb2;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
	
	private String nombre;
	
	private Map <Integer, Libro> librosDisponibles;

	public Biblioteca(String nombre) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.librosDisponibles = new HashMap <Integer, Libro> ();
	}

	public void agregarLibro(Libro libro) {
		this.librosDisponibles.put(libro.getCodigo(), libro);
		
	}
	
	public Boolean libroExiste(Integer codigo) {
		if(this.librosDisponibles.containsKey(codigo)) {
			return true;
		}
		
		return false;
	}

	public Map <Integer, Libro> getLibrosDisponibles() {
		return librosDisponibles;
	}

	public void prestarLibro(Persona persona, Libro libro) throws MasDeDosLibros, LibroInexistente {
		if(libroExiste(libro.getCodigo())) {
			if(persona.getLibrosEnPosesion().size()<2) {
				if(this.librosDisponibles.containsKey(libro.getCodigo())) {
					persona.getLibrosEnPosesion().add(libro);
					this.librosDisponibles.remove(libro.getCodigo());
				}
			} else {
				throw new MasDeDosLibros();
			}
		} else {
			throw new LibroInexistente();
		}

	}

	public void venderLibro(Libro libro, Persona persona) throws LibroInexistente, SinDinero {
		if(libroExiste(libro.getCodigo())) {
			if(persona.getDinero()>=libro.getPrecio()) {
				persona.setDinero(persona.getDinero()-libro.getPrecio());
				persona.getLibrosEnPosesion().add(libro);
				this.librosDisponibles.remove(libro.getCodigo());
			} else {
				throw new SinDinero();
			}
		} else {
			throw new LibroInexistente();
		}

	}

	public String getNombre() {
		return nombre;
	}

}
