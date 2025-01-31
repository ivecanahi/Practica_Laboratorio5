package vista.modeloTablas;

import controlador.Libro;
import javax.swing.table.AbstractTableModel;

public class MTableLibros extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Autor(es)", "Título", "ISBN", "Número de Páginas", "Precio"};
    private final Libro[] libros;

    public MTableLibros(Libro[] libros) {
        this.libros = libros;
    }

    @Override
    public int getRowCount() {
        return libros.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (libros[rowIndex] == null) {
            return null;
        }
        Libro libro = libros[rowIndex];
        switch (columnIndex) {
            case 0: return libro.getId();
            case 1: return libro.getAutores();
            case 2: return libro.getTitulo();
            case 3: return libro.getIsbn();
            case 4: return libro.getNumeroPaginas();
            case 5: return libro.getPrecio();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}