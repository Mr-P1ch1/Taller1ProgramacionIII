// Creado por Alejandro Paqui y Naomi Lizano
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FormularioCine {
    private JPanel principal;
    private JComboBox cboPelicula;
    private JComboBox cboCantidad;
    private JButton cboComprar;
    private JTextArea txtEntradas;
    private JButton btnPelicula1;
    private JButton btnPelicula2;
    private JLabel txtAutor;

    private Cine cine = new Cine(); // Se crea un objeto de la clase Cine
    final String pelicula1 = "XMEN"; // Se define el nombre de la película
    final String pelicula2 = "MARIO"; // Se define el nombre de la película
    public FormularioCine() {
        try {
        String a="",b=""; // Se inicializan las variables
        do {
            b= JOptionPane.showInputDialog("Ingrese su número de cédula real"); // Se solicita la cédula
            a = JOptionPane.showInputDialog("Ingrese su nombre real"); // Se solicita el nombre
        }while(b.length()<10); // Se verifica que la cédula tenga 10 dígitos
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Taller1ProgramacionIII.dat")); // Se crea un archivo
            o.writeObject(a+b); // Se escribe el nombre y la cédula en el archivo
            o.close();
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage()); // Se muestra un mensaje de error

        }
        // Configurando el botón de comprar
        cboComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprarEntradas(); // Se llama al método para comprar entradas
            }
        });

        btnPelicula1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Se configura el botón de la película 1
                int disponibles = cine.entradasDisponibles(pelicula1); // Se obtiene la cantidad de entradas disponibles
                JOptionPane.showMessageDialog(principal, "Espacios disponibles para " + pelicula1 + ": " + disponibles);    // Se muestra un mensaje con la cantidad de entradas disponibles
            }
        });

        btnPelicula2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Se configura el botón de la película 2
                int disponibles = cine.entradasDisponibles(pelicula2); // Se obtiene la cantidad de entradas disponibles
                JOptionPane.showMessageDialog(principal, "Espacios disponibles para " + pelicula2 + ": " + disponibles); // Se muestra un mensaje con la cantidad de entradas disponibles
            }
        });

    }

    private void comprarEntradas() {
        String peliculaSeleccionada = (String) cboPelicula.getSelectedItem(); // Se obtiene la película seleccionada
        int cantidadSeleccionada;// Se inicializa la cantidad seleccionada

       try {
            cantidadSeleccionada = Integer.parseInt((String) cboCantidad.getSelectedItem()); // Se obtiene la cantidad seleccionada
            Asistente nuevoAsistente = new Asistente(cantidadSeleccionada); // Se crea un objeto de la clase Asistente
            nuevoAsistente.setPelicula(peliculaSeleccionada); // Configurar la película usando el setter de la clase Asistente sin modificar asistente.java
            cine.encolar(peliculaSeleccionada, nuevoAsistente); // Se encola el asistente
            actualizarTextArea(peliculaSeleccionada); // Se actualiza el textArea
        } catch (IllegalStateException ex) {
            // Mostrar un mensaje de error si se intenta comprar más entradas de las permitidas
            JOptionPane.showMessageDialog(principal, ex.getMessage(), "Error al Comprar Entradas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarEspaciosDisponibles(String pelicula) { // Se muestra la cantidad de espacios disponibles
        int disponibles = cine.entradasDisponibles(pelicula); // Se obtiene la cantidad de entradas disponibles
        JOptionPane.showMessageDialog(principal, "Espacios disponibles para " + pelicula + ": " + disponibles); // Se muestra un mensaje con la cantidad de entradas disponibles
    }

    private void actualizarTextArea(String peliculaSeleccionada) { // Se actualiza el textArea
        txtEntradas.setText("Entradas para " + peliculaSeleccionada + ":\n" + cine.obtenerListadoEntradas(peliculaSeleccionada)); // Se muestra el listado de entradas
        txtEntradas.append("\nEspacios disponibles: " + cine.entradasDisponibles(peliculaSeleccionada)); // Se muestra la cantidad de entradas disponibles
    }


    public static void main ( String[] args ) {
        JFrame frame = new JFrame ( "FormularioCine" );
        frame.setContentPane ( new FormularioCine ( ).principal );
        frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        frame.pack();
        frame.setSize(500,500);
        frame.setVisible ( true );
    }

}

