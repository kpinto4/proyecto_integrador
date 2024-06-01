package application;

/**
 * La clase MetodoPago representa un método de pago utilizado en el sistema.
 * @author Kevin Santiago
 *
 */
public class MetodoPago {
    private int id;
    private String nombre;

    /**
     * Constructor de la clase MetodoPago.
     * Crea un nuevo objeto MetodoPago con el identificador y nombre proporcionados.
     * @param id El identificador único del método de pago.
     * @param nombre El nombre descriptivo del método de pago.
     */
    public MetodoPago(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador único del método de pago.
     * @return El identificador único del método de pago.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre descriptivo del método de pago.
     * @return El nombre descriptivo del método de pago.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene una representación de cadena del método de pago.
     * Esta representación consiste en el nombre descriptivo del método de pago.
     * @return Una representación de cadena del método de pago.
     */
    @Override
    public String toString() {
        return nombre;
    }
}