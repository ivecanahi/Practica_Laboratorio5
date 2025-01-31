/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.Biblioteca;
import controlador.Libro;
import javax.swing.*;

public class FrmBiblioteca extends JFrame {
    private Biblioteca biblioteca;
    private JTextField txtIdLibro;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtISBN;
    private JTextField txtNumeroPaginas;
    private JTextField txtPrecio;
    private JTextArea txtAreaLibros;
    private JButton btnAgregar;
    private JButton btnPrestar;
    private JButton btnDevolver;

    public FrmBiblioteca() {
        biblioteca = new Biblioteca();
        initComponents();
    }

    private void initComponents() {
        setTitle("Biblioteca");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Crear componentes
        JLabel lblIdLibro = new JLabel("ID del Libro:");
        txtIdLibro = new JTextField(15);
        
        JLabel lblTitulo = new JLabel("Título:");
        txtTitulo = new JTextField(15);
        
        JLabel lblAutor = new JLabel("Autor(es):");
        txtAutor = new JTextField(15);
        
        JLabel lblISBN = new JLabel("ISBN:");
        txtISBN = new JTextField(15);
        
        JLabel lblNumeroPaginas = new JLabel("Número de Páginas:");
        txtNumeroPaginas = new JTextField(5);
        
        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField(10);
        
        txtAreaLibros = new JTextArea(10, 50);
        txtAreaLibros.setEditable(false);
        
        btnAgregar = new JButton("Agregar Libro");
        btnPrestar = new JButton("Prestar Libro");
        btnDevolver = new JButton("Devolver Libro");
        
        // Agregar acción a los botones
        btnAgregar.addActionListener(e -> agregarLibro());
        btnPrestar.addActionListener(e -> prestarLibro());
        btnDevolver.addActionListener(e -> devolverLibro());

        // Crear panel y agregar componentes
        JPanel panel = new JPanel();
        panel.add(lblIdLibro);
        panel.add(txtIdLibro);
        panel.add(lblTitulo);
        panel.add(txtTitulo);
        panel.add(lblAutor);
        panel.add(txtAutor);
        panel.add(lblISBN);
        panel.add(txtISBN);
        panel.add(lblNumeroPaginas);
        panel.add(txtNumeroPaginas);
        panel.add(lblPrecio);
        panel.add(txtPrecio);
        panel.add(btnAgregar);
        panel.add(btnPrestar);
        panel.add(btnDevolver);
        panel.add(new JScrollPane(txtAreaLibros));

        add(panel);
    }

    private void agregarLibro() {
        String id = txtIdLibro.getText();
        String titulo = txtTitulo.getText();
        String autores = txtAutor.getText();
        String isbn = txtISBN.getText();
        int numeroPaginas = Integer.parseInt(txtNumeroPaginas.getText());
        double precio = Double.parseDouble(txtPrecio.getText());

        Libro libro = new Libro(id, autores, titulo, isbn, numeroPaginas, 1, "INV001", precio, 1);
        biblioteca.ingresarLibro(libro);
        mostrarLibros();
        limpiarCampos();
    }

    private void prestarLibro() {
        String id = txtIdLibro.getText();
        if (biblioteca.prestarLibro(id)) {
            JOptionPane.showMessageDialog(this, "Libro prestado con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al prestar el libro.");
        }
    }

    private void devolverLibro() {
        String id = txtIdLibro.getText();
        if (biblioteca.devolverLibro(id)) {
            JOptionPane.showMessageDialog(this, "Libro devuelto con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al devolver el libro.");
        }
    }

    private void mostrarLibros() {
        txtAreaLibros.setText(""); // Limpiar el área de texto
        for (Libro libro : biblioteca.getAlmacenLibros()) {
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

    private void limpiarCampos() {
        txtIdLibro.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtISBN.setText("");
        txtNumeroPaginas.setText("");
        txtPrecio.setText("");
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBiblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmBiblioteca().setVisible(true));
    }
}