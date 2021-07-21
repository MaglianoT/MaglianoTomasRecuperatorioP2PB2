package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBiblioteca {

	@Test
	public void testQueSePuedaAgregarUnLibro() {
		Libro historia = new Historia(1, "Historia para Todos", "Jorge M", 350.0);
		Libro quimica = new Quimica(2, "Quimica Avanzada 2", "Alejandra V", 250.0);
		Biblioteca unlam = new Biblioteca("UNLAM");
		
		unlam.agregarLibro(historia);
		unlam.agregarLibro(quimica);
		
		assertNotNull(unlam.getLibrosDisponibles().get(1));
		assertNotNull(unlam.getLibrosDisponibles().get(2));
	}
	
	@Test
	public void testQueSePuedaPrestarUnLibro() throws MasDeDosLibros, LibroInexistente{
		Libro programacion = new Programacion(5, "Java", "Mariano T", 350.0);
		Biblioteca unlam = new Biblioteca("UNLAM");
		Persona claudia = new Persona ("Claudia", "Blair", 500.0);
		
		unlam.agregarLibro(programacion);
		unlam.prestarLibro(claudia, programacion);
		
		assertNull(unlam.getLibrosDisponibles().get(5));
		assertEquals((Integer)5, claudia.verLibro(5).getCodigo());
		
		
	}
	
	@Test
	public void testQueSePuedaComprarUnLibro() throws LibroInexistente, SinDinero, MasDeDosLibros {
		Libro quimica = new Quimica(3, "Quimica 2021", "Luis G", 300.0);
		Biblioteca unlam = new Biblioteca("UNLAM");
		Persona maxi = new Persona ("Maximiliano", "Aurelio", 420.0);
		
		unlam.agregarLibro(quimica);
		unlam.venderLibro(quimica, maxi);
		
		assertEquals((Integer)3, maxi.verLibro(3).getCodigo());
		assertNull(unlam.getLibrosDisponibles().get(3));
	}
	
	@Test (expected = MasDeDosLibros.class)
	public void testQueNoSePuedanPrestarMasDeDosLibrosALaMismaPersona() throws LibroInexistente, MasDeDosLibros {
		Libro quimica = new Quimica (1, "Quimica I", "Marta D", 200.0);
		Libro historia = new Historia (3, "Historia Argentina", "Damian P", 350.0);
		Libro programacion = new Programacion (4, "Programacion Basica I", "Leonardo M", 300.0);
		Biblioteca unlam = new Biblioteca("UNLAM");
		Persona nahuel = new Persona("Nahuel", "Mazzeo", 500.0);
		
		unlam.agregarLibro(quimica);
		unlam.agregarLibro(historia);
		unlam.agregarLibro(programacion);
		unlam.prestarLibro(nahuel, quimica);
		unlam.prestarLibro(nahuel, historia);
		unlam.prestarLibro(nahuel, programacion);
		
		assertEquals(3, nahuel.getLibrosEnPosesion().size());
		assertNull(unlam.getLibrosDisponibles().get(1));
		assertNull(unlam.getLibrosDisponibles().get(3));
		assertNull(unlam.getLibrosDisponibles().get(4));
	}
	
	@Test (expected = LibroInexistente.class)
	public void testQueNoSePuedaPrestarUnLibroQueNoExista() throws LibroInexistente, MasDeDosLibros {
		Libro programacion = new Programacion(2, "Programacion Avanzada en Java", "Horacio M", 500.0);
		Libro quimica = new Quimica(4, "Quimica Organica", "Liliana L", 400.0);
		Biblioteca unlam = new Biblioteca("UNLAM");
		Persona sergio = new Persona("Sergio", "Mattera", 600.0);
		
		
		unlam.agregarLibro(programacion);
		unlam.prestarLibro(sergio, programacion);
		unlam.prestarLibro(sergio, quimica);
	}
	
	@Test (expected = SinDinero.class)
	public void testQueNoSePuedaVenderUnLibroSiNoTieneDinero() throws SinDinero, LibroInexistente{
		Libro historia = new Historia(4, "Historia Mundial", "Ernesto C", 350.0);
		Biblioteca unlam = new Biblioteca ("UNLAM");
		Persona amelia = new Persona("Amelia", "Pastora", 200.0);
		
		unlam.agregarLibro(historia);
		unlam.venderLibro(historia, amelia);
		
		assertNotNull(amelia.verLibro(4));
	}
	

}
