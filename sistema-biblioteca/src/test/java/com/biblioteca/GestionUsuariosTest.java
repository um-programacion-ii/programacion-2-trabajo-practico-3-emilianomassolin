package com.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GestionUsuariosTest {

    @Mock
    private Catalogo catalogo;

    @Mock
    private SistemaPrestamos sistemaPrestamos;

    @InjectMocks
    private GestionUsuarios gestionUsuarios;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gestionUsuarios = new GestionUsuarios(catalogo, sistemaPrestamos);
        gestionUsuarios.registrarUsuario("usuario1");
    }

    @Test
    void testRegistrarPrestamo() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        Prestamo prestamo = new Prestamo(libro);

        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);
        when(sistemaPrestamos.prestarLibro("978-3-16-148410-0")).thenReturn(prestamo);

        gestionUsuarios.registrarPrestamo("usuario1", "978-3-16-148410-0");

        verify(sistemaPrestamos).prestarLibro("978-3-16-148410-0");
        Usuario usuario = gestionUsuarios.obtenerUsuario("usuario1");
        assertEquals(1, usuario.getHistorialPrestamos().size());
    }

    @Test
    void testRegistrarPrestamoUsuarioInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            gestionUsuarios.registrarPrestamo("inexistente", "978-3-16-148410-0");
        });
    }

    @Test
    void testRegistrarUsuarioYaExistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            gestionUsuarios.registrarUsuario("usuario1");
        });
    }
}
