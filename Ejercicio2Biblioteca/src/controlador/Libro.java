/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

public class Libro {
    private String id;
    private String autores;
    private String titulo;
    private String isbn;
    private int numeroPaginas;
    private int numeroDuplicado;
    private String nroInv;
    private double precio;
    private int totalDuplicados;

   
    public Libro(String id, String autores, String titulo, String isbn, int numeroPaginas, int numeroDuplicado, String nroInv, double precio, int totalDuplicados) {
        this.id = id;
        this.autores = autores;
        this.titulo = titulo;
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
        this.numeroDuplicado = numeroDuplicado;
        this.nroInv = nroInv;
        this.precio = precio;
        this.totalDuplicados = totalDuplicados;
    }

    
    public String getId() { return id; }
    public String getAutores() { return autores; }
    public String getTitulo() { return titulo; }
    public String getIsbn() { return isbn; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public int getNumeroDuplicado() { return numeroDuplicado; }
    public String getNroInv() { return nroInv; }
    public double getPrecio() { return precio; }
    public int getTotalDuplicados() { return totalDuplicados; }
}