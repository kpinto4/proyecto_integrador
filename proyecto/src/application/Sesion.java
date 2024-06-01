package application;


public class Sesion {//estaes para almacenar el cargo del usuario actual para su implementacion en el menu
    private static String nombreUsuario;
    private static String cargoUsuario;

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        Sesion.nombreUsuario = nombreUsuario;
    }

    public static String getCargoUsuario() {
        return cargoUsuario;
    }

    public static void setCargoUsuario(String cargoUsuario) {
        Sesion.cargoUsuario = cargoUsuario;
    }
}
