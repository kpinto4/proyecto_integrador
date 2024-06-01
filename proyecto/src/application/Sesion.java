package application;

/**
 * La clase Sesion proporciona un mecanismo para almacenar el nombre y el cargo del usuario actual.
 * Esta cumple con almacenar el cargo del usuario actual para su implementacion en el menu
 * @author Kevin Santiago
 *
 */
public class Sesion {//
    private static String nombreUsuario;
    private static String cargoUsuario;

    /**
     * Este metodo obtiene el nombre del usuario actual.
     * @return El nombre del usuario actual.
     */
    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * El metodo establece el nombre del usuario actual.
     * @param nombreUsuario El nombre del usuario actual.
     */
    public static void setNombreUsuario(String nombreUsuario) {
        Sesion.nombreUsuario = nombreUsuario;
    }

    /**
     * Este metodo obtiene el cargo del usuario actual.
     * @return El cargo del usuario actual.
     */
    public static String getCargoUsuario() {
        return cargoUsuario;
    }

    /**
     * El metodo establece el cargo del usuario actual.
     * @param cargoUsuario El cargo del usuario actual.
     */
    public static void setCargoUsuario(String cargoUsuario) {
        Sesion.cargoUsuario = cargoUsuario;
    }
}
