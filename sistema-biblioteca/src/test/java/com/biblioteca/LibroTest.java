package com.biblioteca;

import org.junit.Test;
import static org.junit.Assert.*;

public class LibroTest {

    @Test
    public void testCrearLibroValido() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");

        assertEquals("978-3-16-148410-0", libro.getIsbn());
        assertEquals("Clean Code", libro.getTitulo());
        assertEquals("Robert C. Martin", libro.getAutor());
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }

    @Test
    public void testCambiarEstadoLibro() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        libro.setEstado(Estado.PRESTADO);

        assertEquals(Estado.PRESTADO, libro.getEstado());
    }
}
