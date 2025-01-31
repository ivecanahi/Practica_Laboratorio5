package controlador;

import javax.swing.*;
import java.util.ArrayList;

public class E_Libros {
    private ArrayList<Libro> listaLibros;

    public E_Libros() {
        listaLibros = new ArrayList<>();
    }

    
    public void agregarLibro(String id, String autores, String titulo, String isbn, int numeroPaginas, double precio) {
        Libro libro = new Libro(id, autores, titulo, isbn, numeroPaginas, 1, "INV001", precio, 1);
        listaLibros.add(libro);
    }

    
    public boolean prestarLibro(String id) {
        for (Libro libro : listaLibros) {
            if (libro.getId().equals(id)) {
                return true; 
            }
        }
        return false; 
    }

    
    public boolean devolverLibro(String id) {
        for (Libro libro : listaLibros) {
            if (libro.getId().equals(id)) {
                
                return true; 
            }
        }
        return false; 
    }

    
    public ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }

   
    public void mostrarLibros(JTextArea txtAreaLibros) {
        txtAreaLibros.setText(""); 
        for (Libro libro : listaLibros) {
            if (libro != null) {
                txtAreaLibros.append("ID: " + libro.getId() + "\n");
                txtAreaLibros.append("Autor(es): " + libro.getAutores() + "\n");
                txtAreaLibros.append("Título: " + libro.getTitulo() + "\n");
                txtAreaLibros.append("ISBN: " + libro.getIsbn() + "\n");
                txtAreaLibros.append("Número de Páginas: " + libro.getNumeroPaginas() + "\n");
                txtAreaLibros.append("Precio: " + libro.getPrecio() + "\n");
                txtAreaLibros.append("-----------------------------\n");
            }
        }
    }
}