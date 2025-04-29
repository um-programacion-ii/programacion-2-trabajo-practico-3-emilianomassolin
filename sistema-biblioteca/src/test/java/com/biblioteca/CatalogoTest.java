package com.biblioteca;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class CatalogoTest {

    private Catalogo catalogo;
    private Libro libro1;
    private Libro libro2;

    @Before
    public void setUp() {
        catalogo = new Catalogo();
        libro1 = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        libro2 = new Libro("978-0-13-235088-4", "Clean Architecture", "Robert C. Martin");

        catalogo.agregarLibro(libro1);
        catalogo.agregarLibro(libro2);
    }

    @Test
    public void testBuscarPorIsbnExistente() {
        Libro libro = catalogo.buscarPorIsbn("978-3-16-148410-0");
        assertNotNull(libro);
        assertEquals("Clean Code", libro.getTitulo());
    }

    @Test
    public void testBuscarPorIsbnInexistente() {
        Libro libro = catalogo.buscarPorIsbn("000-0-00-000000-0");
        assertNull(libro);
    }

    @Test
    public void testObtenerLibrosDisponibles() {
        libro2.setEstado(Estado.PRESTADO);
        List<Libro> disponibles = catalogo.obtenerLibrosDisponibles();
        assertEquals(1, disponibles.size());
        assertTrue(disponibles.contains(libro1));
        assertFalse(disponibles.contains(libro2));
    }

    @Test
    public void testAgregarLibro() {
        Libro nuevo = new Libro("111-1-11-111111-1", "Software Craftsmanship", "Sandro Mancuso");
        catalogo.agregarLibro(nuevo);
        assertEquals(3, catalogo.getTodosLosLibros().size());
    }
}