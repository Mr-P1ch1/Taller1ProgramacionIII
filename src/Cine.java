// Creado por Alejandro Paqui y Naomi Lizano
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Cine {
    private Map<String, Queue<Asistente>> salasPorPelicula;
    private static final int CAPACIDAD_SALA = 23;
    private static final int MAX_ENTRADAS_POR_CLIENTE = 3;

    // En el diseño del Formulario existe un label actualice a su Nombre y Apellido
    // No borre ninguna sección del codigo proporcionado,
    // Si borra tendrá la nota mínima 1.0
    // Se necesita completar las clases Cine y Formulario
    // Con los algoritmos y métodos necesarios de la estructura FIFO
    // Enunciado: Cada sala de cine tiene una capacidad de 23 espacios, solo se
    // pueden comprar como máximo 3 entradas por cliente a una pelicula, VALIDAR Y GARANTIZAR QUE NO SE PUEDAN VENDER MÁS VOLETOS DE LOS DISPONIBLES
    // Desplegar cada una de las compras en el textArea.
    // Se necesita consultar cuántos espacios quedan disponibles por cada una
    // de las peliculas. DESPLEGAR DESPUES DE CADA COMPRA
    // Rubrica de calificación:
    // Completar la clase Cine 4 Puntos.
    // Agregar elemento a la Cola y mostrar en el textArea 2 Puntos.
    // Programar los botones para conocer la cantidad de espacios disponibles 4 Puntos.
    // Suba su programa a la plataforma hasta que finalice la hora.

    public Cine() {
        this.salasPorPelicula = new HashMap<>(); // Se crea un mapa vacío
    }

    public boolean esVacia(String pelicula) {
        return !salasPorPelicula.containsKey(pelicula) || salasPorPelicula.get(pelicula).isEmpty(); // Se verifica si la cola está vacía
    }

    public int tamanio(String pelicula) {
        return salasPorPelicula.getOrDefault(pelicula, new LinkedList<>()).size(); // Se obtiene el tamaño de la cola
    }

    public void encolar(String pelicula, Asistente asistente) {
        if (asistente.getCantidad() > MAX_ENTRADAS_POR_CLIENTE) {  // Se verifica si se intenta comprar más entradas de las permitidas
            throw new IllegalStateException("No se pueden comprar más de " + MAX_ENTRADAS_POR_CLIENTE + " entradas por cliente."); // Se lanza una excepción
        }

        Queue<Asistente> cola = salasPorPelicula.computeIfAbsent(pelicula, k -> new LinkedList<>()); // Se obtiene la cola de la película

        int entradasVendidas = cola.stream().mapToInt(Asistente::getCantidad).sum(); // Se obtiene la cantidad de entradas vendidas
        if ((entradasVendidas + asistente.getCantidad()) > CAPACIDAD_SALA) { // Se verifica si hay suficientes entradas disponibles
            throw new IllegalStateException("No hay suficientes entradas disponibles para " + pelicula + "."); // Se lanza una excepción
        }

        cola.add(asistente); // Se añade el asistente a la cola
        System.out.println("Después de encolar, entradas disponibles para " + pelicula + ": " + entradasDisponibles(pelicula)); // Se imprime la cantidad de entradas disponibles en consola

    }

    public Asistente desencolar(String pelicula) { // Se desencola un elemento de la cola

        // funcion contains key -> funciona para saber si existe una llave en el mapa o no
        // la llave es el nombre de la pelicula

        if (salasPorPelicula.containsKey(pelicula) && !salasPorPelicula.get(pelicula).isEmpty()) { // Se verifica si la cola no está vacía y si la película existe en el mapa
            return salasPorPelicula.get(pelicula).poll();
        }
        return null;
    }

    public String obtenerListadoEntradas(String pelicula) { // Se obtiene un listado de las entradas vendidas
        Queue<Asistente> cola = salasPorPelicula.get(pelicula); // Se obtiene la cola de la película
        if (cola == null || cola.isEmpty()) { // Se verifica si la cola está vacía
            return "No hay entradas vendidas para " + pelicula + "."; // Se retorna un mensaje
        }

        StringBuilder listado = new StringBuilder(); // Se crea un StringBuilder -> clase que permite manipular cadenas de texto
        for (Asistente asistente : cola) { // Se recorre la cola
            listado.append(asistente.toString()).append("\n"); // Se añade el asistente al listado
        }
        return listado.toString(); // Se retorna el listado
    }

    public int entradasDisponibles(String pelicula) {
         return CAPACIDAD_SALA - salasPorPelicula.getOrDefault(pelicula, new LinkedList<>()).stream().mapToInt(Asistente::getCantidad).sum(); // Se obtiene la cantidad de entradas disponibles
    }
}
