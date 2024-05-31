package application;

public class Usuario {
    private String Cedula;
    private String Nombre;
    private String Direccion;
    private String Telefono;
    private String Usuario;
    private String Contrasena;
    private String Cargo;

    // Constructor adaptado para los datos combinados de empleado y usuario
    public Usuario(String Cedula, String Nombre, String Direccion, String Telefono,String Usuario, String Contrasena, String Cargo) {
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
        this.Cargo = Cargo;
    }

    

	// Getters y setters
    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	
    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }
}
