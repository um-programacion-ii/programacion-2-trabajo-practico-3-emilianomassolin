package com.biblioteca;

public class SistemaPrestamos {

    private Catalogo catalogo;

    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Prestamo prestarLibro(String isbn) {
        Libro libro = catalogo.buscarPorIsbn(isbn);
        if (libro != null && libro.getEstado() == Estado.DISPONIBLE) {
            libro.setEstado(Estado.PRESTADO);
            return new Prestamo(libro);
        }
        return null;
    }
}
