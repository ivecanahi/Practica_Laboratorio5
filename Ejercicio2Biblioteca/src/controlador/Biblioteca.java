package controlador;

import java.util.ArrayList;

public class Biblioteca {
    private Libro[] almacenLibros;
    private ArrayList<Libro> repositorioPrestamos;

    public Biblioteca() {
        almacenLibros = new Libro[10]; 
        repositorioPrestamos = new ArrayList<>(); 
        ingresarLibros(); 
    }

    
    public void ingresarLibros() {
        almacenLibros[0] = new Libro("1", "Autor A", "Título A", "ISBN001", 300, 1, "INV001", 15.99, 1);
        almacenLibros[1] = new Libro("2", "Autor B", "Título B", "ISBN002", 250, 1, "INV002", 12.50, 1);
        
    }

    
    public boolean prestarLibro(String id) {
        for (Libro libro : almacenLibros) {
            if (libro != null && libro.getId().equals(id)) {
                repositorioPrestamos.add(libro); 
                return true; 
            }
        }
        return false; 
    }

    
    public boolean devolverLibro(String id) {
        for (Libro libro : repositorioPrestamos) {
            if (libro.getId().equals(id)) {
                repositorioPrestamos.remove(libro); 
                return true; 
            }
        }
        return false; 
    }

    
    public void mostrarLibros() {
        for (Libro libro : almacenLibros) {
            if (libro != null) {
                System.out.println("ID: " + libro.getId());
                System.out.println("Autor(es): " + libro.getAutores());
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("ISBN: " + libro.getIsbn());
                System.out.println("Número de Páginas: " + libro.getNumeroPaginas());
                System.out.println("Número de Duplicado: " + libro.getNumeroDuplicado());
                System.out.println("Número de Inventario: " + libro.getNroInv());
                System.out.println("Precio: " + libro.getPrecio());
                System.out.println("Total Duplicados: " + libro.getTotalDuplicados());
                System.out.println("-----------------------------");
            }
        }
    }

    
    public Libro[] getAlmacenLibros() {
        return almacenLibros;
    }

    
    public void ingresarLibro(Libro libro) {
        for (int i = 0; i < almacenLibros.length; i++) {
            if (almacenLibros[i] == null) { 
                almacenLibros[i] = libro; 
                return; 
            }
        }
        System.out.println("No se puede agregar más libros, el almacén está lleno.");
    }
}