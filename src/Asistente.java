// Creado por Alejandro Paqui y Naomi Lizano
public class Asistente {

    private String pelicula;
    private int cantidad;

    public Asistente( int cantidad) {
        this.pelicula = pelicula;
        this.cantidad = cantidad;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Asistente a " + pelicula + '\t' +
                ", cantidad de entradas: " +  cantidad + '\n';
    }


}
