package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBiblioteca {

	@Test
	public void testQueSePuedaAgregarUnLibro() {
		// Se prueba que se puedan agregar libros a la biblioteca siempre y cuando no tengan el mismo código
		Libro historia = new Historia(1, TipoDeLibro.MANUAL, "Historia para Todos", "Jorge M", 350.0);
		Libro quimica = new Quimica(2, TipoDeLibro.MANUAL, "Quimica Avanzada 2", "Alejandra V", 250.0);
		Libro programacion = new Programacion (2, TipoDeLibro.REVISTA, "Programacion Inicial", "Alejandro P", 200.0);
		Libro quimica2 = new Quimica (4, TipoDeLibro.FOTOCOPIA, "QuimicaX", "Luciana F", 140.0);
		Libro programacion2 = new Programacion (5, TipoDeLibro.REVISTA, "Programando con Fer", "Fernando T", 150.0);
		Libro historia2 = new Historia (1, TipoDeLibro.FOTOCOPIA, "Historiadores del Mundo", "Miguel Z", 200.0);
		
		Biblioteca unlam = new Biblioteca("UNLAM");
		
		unlam.agregarLibro(historia);
		unlam.agregarLibro(quimica);
		unlam.agregarLibro(programacion);
		unlam.agregarLibro(quimica2);
		unlam.agregarLibro(programacion2);
		unlam.agregarLibro(historia2);
		
		assertNotNull(unlam.getLibrosDisponibles().get(1));
		assertNotNull(unlam.getLibrosDisponibles().get(2));
		assertEquals(4, unlam.getLibrosDisponibles().size());
	}
	
	@Test
	public void testQueSePuedaPrestarUnLibro() throws MasDeDosLibros, LibroInexistente, TipoEquivocado {
		Libro programacion = new Programacion(5, TipoDeLibro.REVISTA, "Java", "Mariano T", 350.0);
		Biblioteca unlam = new Biblioteca("UNLAM");
		Persona claudia = new Persona ("Claudia", "Blair", 500.0);
		
		unlam.agregarLibro(programacion);
		unlam.prestarLibro(claudia, programacion);
		
		assertNull(unlam.getLibrosDisponibles().get(5));
		assertEquals((Integer)5, claudia.verLibro(5).getCodigo());
		
		
	}
	
	@Test
	public void testQueSePuedaComprarUnLibro() throws LibroInexistente, SinDinero, MasDeDosLibros {
		Libro quimica = new Quimica(3, TipoDeLibro.FOTOCOPIA, "Quimica 2021", "Luis G", 300.0);
		Biblioteca unlam = new Biblioteca("UNLAM");
		Persona maxi = new Persona ("Maximiliano", "Aurelio", 420.0);
		
		unlam.agregarLibro(quimica);
		unlam.venderLibro(quimica, maxi);
		
		assertEquals((Integer)3, maxi.verLibro(3).getCodigo());
		assertNull(unlam.getLibrosDisponibles().get(3));
	}
	
	@Test (expected = MasDeDosLibros.class)
	public void testQueNoSePuedanPrestarMasDeDosLibrosALaMismaPersona() throws LibroInexistente, MasDeDosLibros, TipoEquivocado {
		// Si se habla de prestar, no se pueden prestar mas de dos libros (manual y/o revista) a la misma persona
		Libro quimica = new Quimica (1, TipoDeLibro.MANUAL, "Quimica I", "Marta D", 200.0);
		Libro historia = new Historia (3, TipoDeLibro.REVISTA, "Historia Argentina", "Damian P", 350.0);
		Libro programacion = new Programacion (4, TipoDeLibro.MANUAL, "Programacion Basica I", "Leonardo M", 300.0);
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
	public void testQueNoSePuedaPrestarUnLibroQueNoExista() throws LibroInexistente, MasDeDosLibros, TipoEquivocado {
		Libro programacion = new Programacion(2, TipoDeLibro.MANUAL, "Programacion Avanzada en Java", "Horacio M", 500.0);
		Libro quimica = new Quimica(4, TipoDeLibro.MANUAL, "Quimica Organica", "Liliana L", 400.0);
		Biblioteca unlam = new Biblioteca("UNLAM");
		Persona sergio = new Persona("Sergio", "Mattera", 600.0);
		
		
		unlam.agregarLibro(programacion);
		unlam.prestarLibro(sergio, programacion);
		unlam.prestarLibro(sergio, quimica);
		
		assertNull(unlam.getLibrosDisponibles().get(programacion.getCodigo()));
	}
	
	@Test (expected = SinDinero.class)
	public void testQueNoSePuedaVenderUnLibroSiNoTieneDinero() throws SinDinero, LibroInexistente{
		Libro historia = new Historia(4, TipoDeLibro.MANUAL, "Historia Mundial", "Ernesto C", 350.0);
		Biblioteca unlam = new Biblioteca ("UNLAM");
		Persona amelia = new Persona("Amelia", "Pastora", 200.0);
		
		unlam.agregarLibro(historia);
		unlam.venderLibro(historia, amelia);
		
		assertNotNull(amelia.verLibro(4));
	}
	
	@Test (expected = TipoEquivocado.class)
	public void testQueNoSePuedaPrestarUnaFotocopia() throws MasDeDosLibros, LibroInexistente, TipoEquivocado {
		// Las fotocopias solo pueden comprarse, no prestarse
		Libro historia = new Historia (1, TipoDeLibro.REVISTA, "HISTORIADORES II", "Manuel D", 500.0);
		Libro quimica = new Quimica (2, TipoDeLibro.FOTOCOPIA, "QUI II", "Lorena P", 140.0);
		Biblioteca unlam = new Biblioteca ("UNLAM");
		Persona carolina = new Persona ("Carolina", "Melita", 400.0);
		
		unlam.agregarLibro(quimica);
		unlam.agregarLibro(historia);
		unlam.prestarLibro(carolina, historia);
		unlam.prestarLibro(carolina, quimica);
		
		assertNotNull(carolina.verLibro(quimica.getCodigo()));
	}
	
	@Test
	public void queSePuedanComprarMasDeTresLibros() throws LibroInexistente, SinDinero{
		Libro quimica = new Quimica (2, TipoDeLibro.MANUAL, "QUIMICA POR MI", "Tatiana L", 350.0);
		Libro historia = new Historia (1, TipoDeLibro.REVISTA, "HISTORIANDO JUNTOS", "Tomas T", 200.0);
		Libro historia2 = new Historia (24, TipoDeLibro.FOTOCOPIA, "La Historia del Mundo", "Ariel S", 100.0);
		Biblioteca unlam = new Biblioteca("UNLAM");
		Persona mariano = new Persona("Mariano", "Rivor", 700.0);
		
		unlam.agregarLibro(historia2);
		unlam.agregarLibro(historia);
		unlam.agregarLibro(quimica);
		unlam.venderLibro(historia2, mariano);
		unlam.venderLibro(historia, mariano);
		unlam.venderLibro(quimica, mariano);
		
		assertEquals(3, mariano.getLibrosEnPosesion().size());
	}
	
	@Test (expected = LibroInexistente.class)
	public void queNoSePuedaVenderUnLibroYaVendido() throws LibroInexistente, SinDinero{
		// Una vez vendido el libro, se retira de la biblioteca y ya no se puede volver a comprar
		Libro quimica = new Quimica (2, TipoDeLibro.REVISTA, "QUIMICA JUNTOS", "Ruben N", 250.0);
		Biblioteca unlam = new Biblioteca("UNLAM");
		Persona nahuel = new Persona ("Nahuel", "Agustin", 600.0);
		Persona mariano = new Persona ("Mariano", "Leonel", 400.0);
		
		unlam.agregarLibro(quimica);
		unlam.venderLibro(quimica, mariano);
		unlam.venderLibro(quimica, nahuel);
		
		assertNull(unlam.getLibrosDisponibles().get(quimica.getCodigo()));
		
	}
	
	

}
