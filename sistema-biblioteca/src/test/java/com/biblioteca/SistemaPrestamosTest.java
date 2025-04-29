package com.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SistemaPrestamosTest {

    @Mock
    private Catalogo catalogo;

    @InjectMocks
    private SistemaPrestamos sistemaPrestamos;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrestarLibroExitoso() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0");

        assertNotNull(prestamo);
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }

    @Test
    void testPrestarLibroInexistente() {
        when(catalogo.buscarPorIsbn("000-0-00-000000-0")).thenReturn(null);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("000-0-00-000000-0");

        assertNull(prestamo);
        verify(catalogo).buscarPorIsbn("000-0-00-000000-0");
    }

    @Test
    void testPrestarLibroNoDisponible() {
        Libro libro = new Libro("123", "Libro Prestado", "Autor");
        libro.setEstado(Estado.PRESTADO);
        when(catalogo.buscarPorIsbn("123")).thenReturn(libro);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("123");

        assertNull(prestamo);
        verify(catalogo).buscarPorIsbn("123");
    }
}
