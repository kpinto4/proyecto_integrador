package application;

/*import datos.MyLogin;
import datos.Session;

public class Sesion {
	private static LoginController username;
	private static LoginController cargoUsuario;
	public static LoginController getUsername() {
		return username;
	}
	public static void setUsername(LoginController username) {
		Sesion.username = username;
	}
	public static LoginController getCargoUser() {
		return cargoUsuario;
	}
	public static void setCargoUser(LoginController cargoUser) {
		Sesion.cargoUsuario = cargoUser;
	}
	
	public static void setcargoUsuario( cargoUsuario) {
		Sesion.cargoUsuario = cargoUsuario;
	}

}*/




public class Sesion {
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
